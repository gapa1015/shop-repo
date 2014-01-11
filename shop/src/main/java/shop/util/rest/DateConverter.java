package shop.util.rest;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import shop.util.interceptor.Log;

/**
 * Bei @QueryParam oder @PathParam z.B. "2001-10-31" statt Default-Format "31 Oct 2001"
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 *
 */
@Provider
@ApplicationScoped
@Log
public class DateConverter implements ParamConverter<Date>, ParamConverterProvider {
	private static final String FORMAT = "yyyy-MM-dd";
	private static final String ERROR_KEY = "invalidDate";
	
	@Inject
	private Messages messages;
	
	@Override
	public Date fromString(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		
		final SimpleDateFormat formatter = new SimpleDateFormat(FORMAT, Locale.getDefault());
		try {
			return formatter.parse(dateStr);
		}
		catch (ParseException e) {
			final String msg = messages.getMessage(null, ERROR_KEY, dateStr);
			final Response response = Response.status(BAD_REQUEST)
					                          .entity(msg)
					                          .build();
			// die Original-Exception wird bewusst nicht uebernommen, weil die neue
			// Exception zum HTTP-Response fuer den aufrufenden Client wird
			throw new WebApplicationException(response);
		}
	}

	@Override
	public String toString(Date date) {
		if (date == null) {
			return null;
		}
		
		final SimpleDateFormat formatter = new SimpleDateFormat(FORMAT, Locale.getDefault());
		return formatter.format(date);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType == Date.class) {
			return (ParamConverter<T>) this;
		}
		return null;
	}
}
