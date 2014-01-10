package shop.artikelverwaltung.domain;

import static shop.artikelverwaltung.domain.AbstractArtikel.ERSATZTEIL;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@DiscriminatorValue(ERSATZTEIL)
public class Ersatzteil extends AbstractArtikel {
	private static final long serialVersionUID = 4379667842641098264L;

	@XmlTransient
	@ManyToOne
	@JoinColumn(name = "rad_fk")
	private Rad kompartibel;

	public Rad getKompartibel() {
		return kompartibel;
	}

	public void setKompartibel(Rad kompartibel) {
		this.kompartibel = kompartibel;
	}

	@Override
	public String toString() {
		return "Ersatzteil [kompartibel=" + kompartibel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + kompartibel.hashCode();
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
		Ersatzteil other = (Ersatzteil) obj;
		if (kompartibel != other.kompartibel)
			return false;
		return true;
	}

}
