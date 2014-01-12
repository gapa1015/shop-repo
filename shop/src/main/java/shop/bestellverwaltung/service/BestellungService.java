package shop.bestellverwaltung.service;

import java.util.List;

import shop.bestellverwaltung.domain.Bestellung;
import shop.bestellverwaltung.domain.Lieferung;
import shop.kundenverwaltung.domain.AbstractKunde;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
public interface BestellungService {
	public enum FetchType { NUR_BESTELLUNG, MIT_LIEFERUNGEN }

	/*
	 * Java 8 hat zwar Default-Methoden in Interfaces, wie z.B.
	 *    default public Bestellung findBestellungById(Long id, FetchType fetch) {...}
	 * ABER:
	 *    Es duerfen keine Attribute definiert werden - auch nicht injizierte.
	 */

	/**
	 * Bestellung zu gegebener ID suchen
	 * @param id ID der gesuchten Bestellung
	 * @param fetch Welche Objekte sollen mitgeladen werden, z.B. Lieferungen
	 * @return Die gesuchte Bestellung
	 */
	Bestellung findBestellungById(Long id, FetchType fetch);
	
	/**
	 * Kunde zur einer Bestellung suchen
	 * @param id ID der Bestellung
	 * @return Gesuchter Kunde
	 */
	AbstractKunde findKundeById(Long id);
	
	/**
	 * Bestellungen zu einem gegebenen Kunden suchen
	 * @param kunde Gegebener Kunde
	 * @return Liste der Bestellungen
	 */
	List<Bestellung> findBestellungenByKunde(AbstractKunde kunde);
	
	/**
	 * Bestellungen zu gegebenen IDs suchen
	 * @param kunde Gegebener Kunde
	 * @return Liste der Bestellungen
	 */
	List<Bestellung> findBestellungenByIds(List<Long> ids, FetchType fetch);
	
	/**
	 * Bestellung zu einem vorhandenen Kunden anlegen
	 * @param bestellung neue Bestellung
	 * @param kundeId ID des Kunden
	 * @return Neue Bestellung einschliesslich generierter ID
	 */
	Bestellung createBestellung(Bestellung bestellung, Long kundeId);
	
	/**
	 * Neue Bestellung zu einem vorhandenen Kunden anlegen
	 * @param bestellung Neue Bestellung
	 * @param kunde Der vorhandene Kunde
	 * @return Neue Bestellung einschliesslich generierter ID
	 */
	Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde);
	
	/**
	 * Artikel suchen die nur selten bestellt wurden
	 * @param anzahl Obergrenze fuer maximale Bestellungsanzahl
	 * @return Liste der Artikel
	 */

	
	/**
	 * Lieferungen zu gegebenem Nummer-Praefix suchen
	 * @param nr Nummer-Praefix
	 * @return Liste der Lieferungen
	 */
	List<Lieferung> findLieferungen(String nr);
	
	/**
	 * Eine neue Lieferung mit auszuliefernden Bestellungen anlegen
	 * @param lieferung Neue Lieferung
	 * @param bestellungen Zugehoerige Bestellungen
	 * @return Neue Lieferung einschliesslich generierter ID
	 */
	Lieferung createLieferung(Lieferung lieferung, List<Bestellung> bestellungen);
}
