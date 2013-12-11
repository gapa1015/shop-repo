package shop.artikelverwaltung.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ersatzteil extends Artikel {
	private static final long serialVersionUID = 4379667842641098264L;
	
	@NotNull (message = "{ersatzteil.kompartibel.notNull}")
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
