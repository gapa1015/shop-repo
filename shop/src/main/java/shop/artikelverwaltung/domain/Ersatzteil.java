package shop.artikelverwaltung.domain;

import static shop.artikelverwaltung.domain.AbstractArtikel.ERSATZTEIL;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@DiscriminatorValue(ERSATZTEIL)
public class Ersatzteil extends AbstractArtikel {
	private static final long serialVersionUID = 4379667842641098264L;
	
	@XmlTransient
	@OneToMany
	@JoinColumn(name = "rad_fk", nullable = false)
	@OrderColumn(name = "idx")
	private List<Rad> kompartibel;

	public List<Rad> getKompartibel() {
		return kompartibel;
	}

	public void setKompartibel(List<Rad> kompartibel) {
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
		result = prime * result
				+ ((kompartibel == null) ? 0 : kompartibel.hashCode());
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
		final Ersatzteil other = (Ersatzteil) obj;
		if (kompartibel == null) {
			if (other.kompartibel != null)
				return false;
		}
		else if (!kompartibel.equals(other.kompartibel))
			return false;
		return true;
	}

}
