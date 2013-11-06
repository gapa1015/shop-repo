package shop.util;

import java.util.Date;

import shop.artikelverwaltung.domain.Hersteller;
import shop.artikelverwaltung.domain.Lieferant;
import shop.artikelverwaltung.domain.Rad;
import shop.bestellverwaltung.domain.Bestellung;
import shop.kundenverwaltung.domain.Adresse;
import shop.kundenverwaltung.domain.Bankdaten;
import shop.kundenverwaltung.domain.Kunde;

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

		bestellung.setId((long) 10);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(null);
		bestellung.setBestelldatum(date);

		return bestellung;
	}

	public static void updateBestellung(Bestellung bestellung) {
		System.out.println("Aktualisierte Bestellung: " + bestellung);
	}

	public static void deleteBestellung(Long id) {
		System.out.println("Bestellung mit ID=" + id + " geloescht");
	}

	public static Rad findRadById(Long id) {
		final Lieferant lieferant = new Lieferant();
		lieferant.setId(id + 1);
		lieferant.setName("Franz");

		final Hersteller hersteller = new Hersteller();
		hersteller.setId(id + 2);
		hersteller.setName("Velo GmbH");
		
		final Rad rad = new Rad();
		rad.setId(id);
		rad.setName("City-Bike");
		rad.setPreis(150);
		rad.setBaujahr(2013);
		rad.setZoll(28);
		rad.setHersteller(null);
		rad.setLieferant(null);

		return rad;
	}

	public static Rad createRad(Rad rad) {
		final Rad radx = new Rad();
		radx.setId((long) 10);
		radx.setName(rad.getName());
		radx.setPreis(rad.getPreis());
		radx.setZoll(rad.getZoll());
		radx.setBaujahr(rad.getBaujahr());
		radx.setHersteller(rad.getHersteller());
		radx.setLieferant(rad.getLieferant());

		System.out.println("Das folgende Rad wurde erstellt" + rad);

		return rad;
	}

	public static void updateRad(Rad rad) {
		System.out.println("Rad mit ID = " + rad + "wurde aktualliert");
	}

	public static void deleteRad(Long id) {
		System.out.println("Rad mit ID = " + id + " geloescht");
	}

	public static void deleteKunde(Long id) {

		System.out.println("Kunde mit ID = " + id + "wurde geloescht");

	}
								
	public static Kunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		final Kunde kunde = new Kunde(); 
		kunde.setId(id); kunde.setVorname("Andreas"); 
		kunde.setNachname("Jankowoi"); 
		kunde.setEmail("jaan1011@hs-karlsruhe.de"); 
		Date geburt = new Date(); 
		kunde.setGeburtstag(geburt); 
		kunde.setTelefon("00234234994");

		final Adresse adress = new Adresse(); 
		adress.setStrasse("Kaiserstrasse"); 
		adress.setHausnummer("12"); 
		adress.setStadt("Karlsruhe"); 
		adress.setPlz("76100"); 
		kunde.setAdresse(adress);

		final Bankdaten bank = new Bankdaten(); 
		bank.setBankname("Sparkasse Ettlingen"); 
		bank.setKontonummer(83747446); 
		bank.setBlz(325443567); 
		kunde.setBankdaten(bank);

		return kunde;
	}
	public static Kunde findKundeByVorname(String vorname) {
		if (vorname == null) {
			return null;
		}
		final Kunde kunde = new Kunde(); 
		kunde.setId((long)98); 
		kunde.setVorname(vorname); 
		kunde.setNachname("Jankowoi"); 
		kunde.setEmail("jaan1011@hs-karlsruhe.de"); 
		Date geburt = new Date(); 
		kunde.setGeburtstag(geburt); 
		kunde.setTelefon("00234234994");

		final Adresse adress = new Adresse(); 
		adress.setStrasse("Kaiserstrasse"); 
		adress.setHausnummer("12"); 
		adress.setStadt("Karlsruhe"); 
		adress.setPlz("76100"); 
		kunde.setAdresse(adress);

		final Bankdaten bank = new Bankdaten(); 
		bank.setBankname("Sparkasse Ettlingen"); 
		bank.setKontonummer(83747446); 
		bank.setBlz(325443567); 
		kunde.setBankdaten(bank);

		return kunde;
	}
	
	public static Kunde findKundeByNachname(String nachname) {
		if (nachname == null) {
			return null;
		}
		final Kunde kunde = new Kunde(); 
		kunde.setId((long)98); 
		kunde.setVorname("Andreas"); 
		kunde.setNachname(nachname); 
		kunde.setEmail("jaan1011@hs-karlsruhe.de"); 
		Date geburt = new Date(); 
		kunde.setGeburtstag(geburt); 
		kunde.setTelefon("00234234994");

		final Adresse adress = new Adresse(); 
		adress.setStrasse("Kaiserstrasse"); 
		adress.setHausnummer("12"); 
		adress.setStadt("Karlsruhe"); 
		adress.setPlz("76100"); 
		kunde.setAdresse(adress);

		final Bankdaten bank = new Bankdaten(); 
		bank.setBankname("Sparkasse Ettlingen"); 
		bank.setKontonummer(83747446); 
		bank.setBlz(325443567); 
		kunde.setBankdaten(bank);

		return kunde;
	}
	
	

	public static Kunde createKunde(Kunde kunde) {

		final Kunde kund = new Kunde();
		kund.setId(kunde.getId());
		kund.setVorname(kunde.getVorname());
		kund.setNachname(kunde.getNachname());
		kund.setEmail(kunde.getEmail());
		Date geburt = new Date();
		kund.setGeburtstag(geburt);
		kund.setTelefon(kunde.getTelefon());

		final Adresse adress = new Adresse();
		adress.setStrasse(kunde.getAdresse().getStrasse());
		adress.setHausnummer(kunde.getAdresse().getHausnummer());
		adress.setStadt(kunde.getAdresse().getStadt());
		adress.setPlz(kunde.getAdresse().getPlz());
		kund.setAdresse(adress);

		final Bankdaten bank = new Bankdaten();
		bank.setBankname(kunde.getBankdaten().getBankname());
		bank.setKontonummer(kunde.getBankdaten().getKontonummer());
		bank.setBlz(kunde.getBankdaten().getBlz());
		kund.setBankdaten(bank);

		System.out.println("Der folge Kunde wurde erstellt" + kund);
		return kund;
	}

	public static void updateKunde(Kunde kunde) {
		System.out.println("Kunde mit ID = " + kunde + "wurde aktualliert");
	}
}
