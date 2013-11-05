package shop.util;

import java.util.Date;

import shop.artikelverwaltung.domain.Rad;
import shop.bestellverwaltung.domain.Bestellung;

public class Mock {
	private static final int MAX_ID = 99;
	
	public static Bestellung findeBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Date date = new Date();
		
		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(null);
		bestellung.setBestelldatum(date);
		
		return bestellung;
	}

	public static Rad findRadById(Long id) {
		final Rad rad = new Rad();
		rad.setId(id);
		return rad;
	}
}
