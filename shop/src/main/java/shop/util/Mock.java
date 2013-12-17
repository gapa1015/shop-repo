package shop.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Hersteller;
import shop.artikelverwaltung.domain.Lieferant;
import shop.artikelverwaltung.domain.Rad;
import shop.bestellverwaltung.domain.Bestellung;
import shop.kundenverwaltung.domain.Adresse;
import shop.kundenverwaltung.domain.Bankdaten;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.domain.Privatkunde;
import shop.util.cdi.MockService;
import shop.util.interceptor.Log;

@MockService
@Dependent
@Log
public class Mock {
	private static final int MAX_ID = 99;
	private static final int MAX_BESTELLUNGEN = 4;

	public static Bestellung findeBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final AbstractKunde kunde = findKundeById(id + 1);
		final Date date = new Date();

		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(kunde);
		bestellung.setBestelldatum(date);

		return bestellung;
	}

	public static Bestellung createBestellung(Bestellung bestellung) {
		final Date date = new Date();
		
		bestellung.setId((long) bestellung.getKunde().getNachname().length());
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(bestellung.getKunde());
		bestellung.setBestelldatum(date);
		
		return bestellung;
	}

	public static Bestellung updateBestellung(Bestellung bestellung) {
		System.out.println("Aktualisierte Bestellung: " + bestellung);
		return bestellung;
	}

	public static AbstractArtikel findArtikelById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final int preis = 150;
		final int zoll = 28;
		final String baujahr = "2012";
		
		final Lieferant lieferant = new Lieferant();
		lieferant.setId(id + 1);
		lieferant.setName("Franz");
		
		final Adresse adressa = new Adresse(); 
		adressa.setStrasse("Amalienstrasse"); 
		adressa.setHausnummer("70"); 
		adressa.setStadt("Karlsruhe"); 
		adressa.setPlz("76133"); 
		lieferant.setAdresse(adressa);

		final Hersteller hersteller = new Hersteller();
		hersteller.setId(id + 2);
		hersteller.setName("Velo");
		
		final Adresse adressz = new Adresse(); 
		adressz.setStrasse("Amalienstrasse"); 
		adressz.setHausnummer("70"); 
		adressz.setStadt("Karlsruhe"); 
		adressz.setPlz("76133"); 
		hersteller.setAdresse(adressz);
		
		final AbstractArtikel artikel = new Rad();
		artikel.setId(id);
		artikel.setName("City-Bike");
		artikel.setPreis(preis);
		artikel.setHersteller(hersteller);
		artikel.setLieferant(lieferant);

		if (artikel.getClass().equals(Rad.class)) {
			final Rad rad = (Rad) artikel;
			rad.setBaujahr(baujahr);
			rad.setZoll(zoll);
		}
		
		return artikel;
	}
	
	public static <T extends AbstractArtikel> T createArtikel(T artikel) {
		final long id = 10;
		
		final AbstractArtikel radx = new Rad();
		radx.setId(id);
		radx.setName(artikel.getName());
		radx.setPreis(artikel.getPreis());
		radx.setHersteller(artikel.getHersteller());
		radx.setLieferant(artikel.getLieferant());
		
		if (artikel.getClass().equals(Rad.class)) {
			final Rad rad = (Rad) radx;
			rad.setZoll(rad.getZoll());
			rad.setBaujahr(rad.getBaujahr());
		}

		return artikel;
	}

	public static void updateRad(Rad rad) {
		System.out.println("Aktualisiertes Rad: " + rad);
	}

	public static AbstractKunde findKundeById(Long id) {

		if (id > MAX_ID) {
			return null;
		}
		
		final AbstractKunde kunde = new Privatkunde(); 
		kunde.setId(id); 
		kunde.setVorname("Andreas"); 
		kunde.setNachname("Jankowoi"); 
		kunde.setEmail("jaan1011@hskarlsruhe.de"); 
		final Date geburt = new Date(); 
		kunde.setGeburtstag(geburt); 
		kunde.setTelefon("00234234994");

		final Adresse adress = new Adresse(); 
		adress.setStrasse("Kaiserstrasse"); 
		adress.setHausnummer("12"); 
		adress.setStadt("Karlsruhe"); 
		adress.setPlz("76100"); 
		kunde.setAdresse(adress);

		final Bankdaten bank = new Bankdaten(); 
		bank.setBankname("Sparkasse"); 
		bank.setKontonummer("837474462"); 
		bank.setBlz("32544567"); 
		kunde.setBankdaten(bank);

		return kunde;
	}
	
    public static List<AbstractKunde> findAllKunden() {
        final int anzahl = 3;
        final List<AbstractKunde> kundeList = new ArrayList<>(anzahl);
        for (int i = 1; i <= anzahl; i++) {
                final AbstractKunde kunde = findKundeById((long) i);
                kundeList.add(kunde);
        }
        return kundeList;
    }
	
	public static List<AbstractKunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunde.setNachname(nachname);
			kunden.add(kunde);			
		}
		return kunden;
	}
	
	public static AbstractKunde findKundeByEmail(String email) {
		if (email == null) {
			return null;
		}
		
		final AbstractKunde kunde = new Privatkunde(); 
		
		kunde.setId((long) email.length()); 
		kunde.setVorname("Andreas"); 
		kunde.setNachname("Jankoboy"); 
		kunde.setEmail(email); 
		final Date geburt = new Date(); 
		kunde.setGeburtstag(geburt); 
		kunde.setTelefon("00234234994");

		final Adresse adress = new Adresse(); 
		adress.setStrasse("Kaiserstrasse"); 
		adress.setHausnummer("12"); 
		adress.setStadt("Karlsruhe"); 
		adress.setPlz("76100"); 
		kunde.setAdresse(adress);

		final Bankdaten bank = new Bankdaten(); 
		bank.setBankname("Sparkasse"); 
		bank.setKontonummer("887347446"); 
		bank.setBlz("32544356"); 
		kunde.setBankdaten(bank);

		return kunde;
	}
	
	public static List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = kunde.getId().intValue() % MAX_BESTELLUNGEN + 1;  // 1, 2, 3 oder 4 Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findeBestellungById(Long.valueOf(i));
			bestellung.setKunde(kunde);
			bestellungen.add(bestellung);			
		}
		
		return bestellungen;
	}
	
	public static <T extends AbstractKunde> T createKunde(T kunde) {

		final AbstractKunde kund = new Privatkunde();
		
		kund.setId(kunde.getId());
		kund.setVorname(kunde.getVorname());
		kund.setNachname(kunde.getNachname());
		kund.setEmail(kunde.getEmail());
		final Date geburt = new Date();
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

		return kunde;
	}

		public static void updateKunde(AbstractKunde kunde) {

	}
		
		public static void deleteRad(Long id) {
			System.out.println("Rad mit ID = " + id + "geloescht");
		}

		public static void deleteKunde(Long id) {

			System.out.println("Kunde mit ID= " + id + "wurde geloescht");

		}
}
