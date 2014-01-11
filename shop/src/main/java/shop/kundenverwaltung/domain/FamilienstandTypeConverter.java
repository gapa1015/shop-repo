package shop.kundenverwaltung.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Converter(autoApply = true)
public class FamilienstandTypeConverter implements AttributeConverter<FamilienstandType, String> {
	@Override
	public String convertToDatabaseColumn(FamilienstandType familienstandType) {
		if (familienstandType == null) {
			return null;
		}
		return familienstandType.getInternal();
	}

	@Override
	public FamilienstandType convertToEntityAttribute(String internal) {
		return FamilienstandType.build(internal);
	}
}
