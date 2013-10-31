package shop.artikelverwaltung.domain;

public class Rad {
	private String name;
	private Hersteller hersteller;
	private Lieferant lieferant;
	private int zoll;
	private int baujahr;
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

	public int getZoll() {
		return zoll;
	}

	public void setZoll(int zoll) {
		this.zoll = zoll;
	}

	public int getBaujahr() {
		return baujahr;
	}

	public void setBaujahr(int baujahr) {
		this.baujahr = baujahr;
	}

	public Lieferant getLieferant() {
		return lieferant;
	}

	public void setLieferant(Lieferant lieferant) {
		this.lieferant = lieferant;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	@Override
	public String toString() {
		return "Rad [name=" + name + ", hersteller=" + hersteller
				+ ", lieferant=" + lieferant + ", zoll=" + zoll + ", baujahr="
				+ baujahr + ", preis=" + preis + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baujahr;
		result = prime * result
				+ ((hersteller == null) ? 0 : hersteller.hashCode());
		result = prime * result
				+ ((lieferant == null) ? 0 : lieferant.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + preis;
		result = prime * result + zoll;
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
		Rad other = (Rad) obj;
		if (baujahr != other.baujahr)
			return false;
		if (hersteller == null) {
			if (other.hersteller != null)
				return false;
		} else if (!hersteller.equals(other.hersteller))
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
		if (zoll != other.zoll)
			return false;
		return true;
	}

}
