package shop.bestellverwaltung.domain;

import java.net.URI;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




import shop.kundenverwaltung.domain.AbstractKunde;

@XmlRootElement
public class Bestellung {
	private Long id;
	private boolean ausgeliefert;
	private Date bestelldatum;
	
	@XmlTransient
	private AbstractKunde kunde;
	
	private URI kundeURI;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAusgeliefert() {
		return ausgeliefert;
	}

	public void setAusgeliefert(boolean ausgeliefert) {
		this.ausgeliefert = ausgeliefert;
	}

	public Date getBestelldatum() {
		return bestelldatum;
	}

	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}

	public AbstractKunde getKunde() {
		return kunde;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}

	public URI getKundeURI() {
		return kundeURI;
	}

	public void setKundeURI(URI kundeURI) {
		this.kundeURI = kundeURI;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestelldatum == null) ? 0 : bestelldatum.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Bestellung other = (Bestellung) obj;
		if (bestelldatum == null) {
			if (other.bestelldatum != null)
				return false;
		} 
		else if (!bestelldatum.equals(other.bestelldatum))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", ausgeliefert=" + ausgeliefert
				+ ", bestelldatum=" + bestelldatum + ", kunde=" + kunde
				+ ", kundeURI=" + kundeURI + "]";
	}
}
