package shop.util;

import java.util.Date;

import shop.artikelverwaltung.domain.Hersteller;
import shop.artikelverwaltung.domain.Lieferant;
import shop.artikelverwaltung.domain.Rad;
import shop.bestellverwaltung.domain.Bestellung;
import shop.kundenverwaltung.domain.Adresse;
import shop.kundenverwaltung.domain.Bankdaten;
import shop.kundenverwaltung.domain.AbstractKunde;

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

	public static Rad findeRadById(Long id) {
//		final Adresse adress = new Adresse();
//		adress.setStrasse("Moltestr");
//		adress.setHausnummer("30");
//		adress.setStadt("Karlsruhe");
//		adress.setPlz("76133");
		
		final Lieferant lieferant = new Lieferant();
		lieferant.setId(id + 1);
		lieferant.setName("Test");
//   	lieferant.setAdresse(adress);
		
		final Hersteller hersteller = new Hersteller();
		hersteller.setId(id + 2);
		hersteller.setName("TestTest");
//		hersteller.setAdresse(adress);
		
		final Rad arad = new Rad();
		arad.setId(id);
		arad.setName("Name von" + id);
		arad.setPreis(5000);
		arad.setBaujahr(1990);
		arad.setZoll(28);
		arad.setHersteller(hersteller);
		arad.setLieferant(lieferant);

		System.out.println("Das folgende Rad wurde gefunden" + arad);

		return arad;
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

	}

	public static void deleteKunde(Long id) {

		System.out.println("Kunde mit ID= " + id + "wurde geloescht");

	}

	public static AbstractKunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		final AbstractKunde kunde = new AbstractKunde(); 
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
	public static AbstractKunde findKundeByVorname(String vorname) {
		if (vorname == null) {
			return null;
		}
		final AbstractKunde kunde = new AbstractKunde(); 
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
	
	public static AbstractKunde findKundeByNachname(String nachname) {
		if (nachname == null) {
			return null;
		}
		final AbstractKunde kunde = new AbstractKunde(); 
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
	
	

	public static AbstractKunde createKunde(AbstractKunde kunde) {

		final AbstractKunde kund = new AbstractKunde();
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

	public static void updateKunde(AbstractKunde kunde) {
		System.out.println("Kunde mit ID = " + kunde + "wurde aktualliert");
	}
}
