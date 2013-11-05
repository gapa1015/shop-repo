package shop.artikelverwaltung.domain;

import java.util.List;

public class Ersatzteil extends Artikel {
	private List<Rad> kompartibel;
	
		public List<Rad> getKompartibel() {
		return kompartibel;
	}

	public void setKompartibel(List<Rad> kompartibel) {
		this.kompartibel = kompartibel;
	}

	
}
