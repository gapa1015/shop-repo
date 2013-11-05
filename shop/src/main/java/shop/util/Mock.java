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

	final Hersteller ahersteller = new Hersteller();

	
	final static Lieferant alieferant = new Lieferant();
	alieferant01.setId(002);
	alieferant01.setName("Heinz");
	
	
	
	public static Rad findRadById(final Long id) {
	final Rad arad = new Rad();
	arad.setId(id);
	arad.setName("Name von"+id);
	arad.setPreis(5000);
	arad.setBaujahr(1990);
	arad.setZoll(28);
	arad.setHersteller(ahersteller);
	arad.setLieferant(alieferant);
	
	}
	
	
}
