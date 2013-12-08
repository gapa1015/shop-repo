package shop.bestellverwaltung.service;

import java.util.List;
import java.util.Locale;

import shop.bestellverwaltung.domain.Bestellung;
import shop.kundenverwaltung.domain.AbstractKunde;

public interface BestellungService {

	Bestellung findBestellungById(Long id);
	Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde, Locale locale);
	Bestellung updateBestellung(Bestellung bestellung);
	List<Bestellung> findBestellungenByKunde(AbstractKunde kunde);
}