package shop.artikelverwaltung.domain;

public class Rad extends Artikel {

	private int zoll;
	private int baujahr;

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

	@Override
	public String toString() {
		return "Rad [zoll=" + zoll + ", baujahr=" + baujahr + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + baujahr;
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
		Rad other = (Rad) obj;
		if (baujahr != other.baujahr)
			return false;
		if (zoll != other.zoll)
			return false;
		return true;
	}

}
