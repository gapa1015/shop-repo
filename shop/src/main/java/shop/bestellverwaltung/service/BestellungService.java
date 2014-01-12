package shop.bestellverwaltung.service;

import java.util.List;

import shop.bestellverwaltung.domain.Bestellung;
import shop.bestellverwaltung.domain.Lieferung;
import shop.kundenverwaltung.domain.AbstractKunde;

public interface BestellungService {
	public enum FetchType { NUR_BESTELLUNG, MIT_LIEFERUNGEN }

	Bestellung findBestellungById(Long id, FetchType fetch);
	
	AbstractKunde findKundeById(Long id);
	
	List<Bestellung> findBestellungenByKunde(AbstractKunde kunde);
	
	List<Bestellung> findBestellungenByIds(List<Long> ids, FetchType fetch);
	
	Bestellung createBestellung(Bestellung bestellung, Long kundeId);
	
	Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde);
	
	List<Lieferung> findLieferungen(String nr);
	
	Lieferung createLieferung(Lieferung lieferung, List<Bestellung> bestellungen);
}
