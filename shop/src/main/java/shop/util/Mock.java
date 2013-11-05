package shop.util;

import java.util.Date;

import shop.artikelverwaltung.domain.Hersteller;
import shop.artikelverwaltung.domain.Lieferant;
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
	
	public static Bestellung createBestellung(Bestellung bestellung) {
		final Date date = new Date();
		
		bestellung.setId((long)10);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(null);
		bestellung.setBestelldatum(date);
		
		return bestellung;
	}	
	
	public static Rad findeRadById(Long id) {
		final Lieferant lieferant = new Lieferant();
		lieferant.setId(id+1);
		lieferant.setNmae("Test");
		
		final Hersteller hersteller = new Hersteller();
		hersteller.setId(id+2);
		hersteller.setName("TestTest");
		
		final Rad arad = new Rad();
		arad.setId(id);
		arad.setName("Name von"+id);
		arad.setPreis(5000);
		arad.setBaujahr(1990);
		arad.setZoll(28);
		arad.setHersteller(hersteller);
		arad.setLieferant(lieferant);
		
		return arad;
	}
}
