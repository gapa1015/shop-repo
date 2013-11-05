package shop.util;

import java.util.Date;

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
	public static void deleteKunde(Long id) {
		
		System.out.println("Kunde mit ID= " + id + "wurde geloescht");
		
	}
	
	
	public static Kunde findeKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		
		final Kunde kunde = new Kunde();
		kunde.setId(id);
		kunde.setVorname("Andreas");
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

}
