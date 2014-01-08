package shop.artikelverwaltung.domain;

import static shop.artikelverwaltung.domain.AbstractArtikel.RAD;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@DiscriminatorValue(RAD)
public class Rad extends AbstractArtikel {
	private static final long serialVersionUID = -583656323460519925L;

	@Min(12)
	@Max(28)
	@NotNull(message = "{rad.zoll.notNull}")
	private int zoll;

	private String baujahr;

	public int getZoll() {
		return zoll;
	}

	public void setZoll(int zoll) {
		this.zoll = zoll;
	}

	public String getBaujahr() {
		return baujahr;
	}

	public void setBaujahr(String baujahr) {
		this.baujahr = baujahr;
	}

	@Override
	public String toString() {
		return "Rad [zoll=" + zoll + ", baujahr=" + baujahr + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + zoll;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Rad other = (Rad) obj;
		if (zoll != other.zoll)
			return false;
		return true;
	}

}
