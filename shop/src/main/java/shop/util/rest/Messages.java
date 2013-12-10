package shop.util.rest;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.HttpHeaders;

import org.jboss.logging.Logger;

import shop.util.interceptor.Log;

import com.google.common.base.Splitter;

@ApplicationScoped
@Log
public class Messages {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String APPLICATION_MESSAGES = "/ApplicationMessages";
	private static final List<Locale> LOCALES_DEFAULT = Arrays.asList(Locale.ENGLISH);
	
	@Resource(name = "locales")
	private String locales;
	
	private ResourceBundle defaultBundle;
	
	private Map<Locale, ResourceBundle> bundles;
	
	private Map<String, ResourceBundle> bundlesLanguageStr;
	
	@PostConstruct
	private void postConstruct() {
		List<Locale> localesList;
		if (locales == null)
			localesList = LOCALES_DEFAULT;
		else {
			localesList = new ArrayList<>();
			final Iterable<String> localesIter = Splitter.on(',')
													.trimResults()
													.omitEmptyStrings()
													.split(locales);
			final Locale.Builder localeBuilder = new Locale.Builder();
			for (String localeStr : localesIter) {
				try {
					localeBuilder.setLanguage(localeStr);
				}
				catch (IllformedLocaleException e) {
					LOGGER.warnf("web.xml: %s ist kein gueltiger Sprachcode", localeStr);
					continue;
				}
				localesList.add(localeBuilder.build());
			}
		}
		LOGGER.infof("Locales fuer REST: %s", localesList);
		
		bundles = new HashMap<>();
		bundlesLanguageStr = new HashMap<>();
		final Set<String> languages = new HashSet<>();
		for (Locale locale : localesList) {
			final ResourceBundle bundle = ResourceBundle.getBundle(APPLICATION_MESSAGES, locale);
			bundles.put(locale, bundle);
			
			String localeStr = locale.toString();
			if (localeStr.length() > 2) {
				localeStr = localeStr.substring(0, 2);
				if (!languages.contains(localeStr)) {
					bundlesLanguageStr.put(localeStr, bundle);
					languages.add(localeStr);
				}
			}
		}
		defaultBundle = bundles.get(localesList.get(0));
	}
	
	public String getMessage(HttpHeaders headers, String key, Object... args) {
		final List<Locale> locales = headers.getAcceptableLanguages();
		final ResourceBundle bundle = getBundle(locales);
		
		final String pattern = bundle.getString(key);
		final MessageFormat messageFormat = new MessageFormat(pattern, locales.get(0));
		return messageFormat.format(args);
		
	}
	
	private ResourceBundle getBundle(List<Locale> locales) {
		ResourceBundle bundle = null;
		
		for (Locale locale : locales) {
			bundle = bundles.get(locale);
			if (bundle != null)
				break;
			String localeStr = locale.toString();
			if (localeStr.length() > 2) {
				localeStr = localeStr.substring(0, 2);
				bundle = bundlesLanguageStr.get(localeStr);
				if (bundle != null)
					break;
			}
		}
		return bundle == null ? defaultBundle : bundle;
	}
}
