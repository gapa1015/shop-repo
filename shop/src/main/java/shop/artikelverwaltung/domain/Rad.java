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

	
}
