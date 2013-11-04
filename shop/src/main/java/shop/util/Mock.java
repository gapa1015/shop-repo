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

	public static Rad findeRadById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Bestellung createBestellung(Bestellung bestellung) {
		final Date date = new Date();
		
		bestellung.setId((long)10);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(null);
		bestellung.setBestelldatum(date);
		
		return bestellung;
	}
}
