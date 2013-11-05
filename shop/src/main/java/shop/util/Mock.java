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
	
	
	public static Kunde findeKundeById(Kunde kunde) {
		
		
		return kunde;
	}
	
	
	
	public static Kunde createKunde(Kunde kunde) {
		
		final Kunde kund = new Kunde();
		kund.setId(kunde.getId());
		kund.setVorname(kunde.getVorname());
		kund.setNachname(kunde.getNachname());
		kund.setEmail(kunde.getEmail());
		kund.setGeburtstag(kunde.getGeburtstag());
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
