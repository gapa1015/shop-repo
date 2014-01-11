package shop.kundenverwaltung.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Converter(autoApply = true)
public class HobbyTypeConverter implements AttributeConverter<HobbyType, String> {
	@Override
	public String convertToDatabaseColumn(HobbyType hobbyType) {
		if (hobbyType == null) {
			return null;
		}
		return hobbyType.getInternal();
	}

	@Override
	public HobbyType convertToEntityAttribute(String internal) {
		return HobbyType.build(internal);
	}
}
