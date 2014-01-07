package shop.kundenverwaltung.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GeschlechtTypeConverter implements
		AttributeConverter<GeschlechtType, String> {

	@Override
	public String convertToDatabaseColumn(GeschlechtType geschlechtType) {
		if (geschlechtType == null)
			return null;
		return geschlechtType.getInternal();
	}

	@Override
	public GeschlechtType convertToEntityAttribute(String internal) {
		return GeschlechtType.build(internal);
	}

}
