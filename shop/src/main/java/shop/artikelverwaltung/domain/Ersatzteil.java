package shop.artikelverwaltung.domain;

import java.util.List;

public class Ersatzteil {
	private String name;
	private Hersteller hersteller;
	private Lieferant lieferant;
	private List<Rad> kompartibel;
	private int preis;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hersteller getHersteller() {
		return hersteller;
	}

	public void setHersteller(Hersteller hersteller) {
		this.hersteller = hersteller;
	}

	public Lieferant getLieferant() {
		return lieferant;
	}

	public void setLieferant(Lieferant lieferant) {
		this.lieferant = lieferant;
	}

	public List<Rad> getKompartibel() {
		return kompartibel;
	}

	public void setKompartibel(List<Rad> kompartibel) {
		this.kompartibel = kompartibel;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	@Override
	public String toString() {
		return "Ersatzteile [name=" + name + ", hersteller=" + hersteller
				+ ", lieferant=" + lieferant + ", kompartibel=" + kompartibel
				+ ", preis=" + preis + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hersteller == null) ? 0 : hersteller.hashCode());
		result = prime * result
				+ ((kompartibel == null) ? 0 : kompartibel.hashCode());
		result = prime * result
				+ ((lieferant == null) ? 0 : lieferant.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + preis;
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
		Ersatzteil other = (Ersatzteil) obj;
		if (hersteller == null) {
			if (other.hersteller != null)
				return false;
		} else if (!hersteller.equals(other.hersteller))
			return false;
		if (kompartibel == null) {
			if (other.kompartibel != null)
				return false;
		} else if (!kompartibel.equals(other.kompartibel))
			return false;
		if (lieferant == null) {
			if (other.lieferant != null)
				return false;
		} else if (!lieferant.equals(other.lieferant))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (preis != other.preis)
			return false;
		return true;
	}
}
