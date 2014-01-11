package shop.bestellverwaltung.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.bestellverwaltung.domain.Bestellung;
import shop.kundenverwaltung.domain.AbstractKunde;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Decorator
@Dependent
public abstract class BestellungServiceMitGeschenkverpackung implements BestellungService {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Inject
	@Delegate
	@Any
	private BestellungService bs;

	/**
	 * {inheritDoc}
	 */
	@Override
	public Bestellung findBestellungById(Long id, FetchType fetch) {
		return bs.findBestellungById(id, fetch);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public AbstractKunde findKundeById(Long id) {
		return bs.findKundeById(id);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		return bs.findBestellungenByKunde(kunde);
	}
	
	/**
	 * {inheritDoc}
	 */

	/**
	 * {inheritDoc}
	 */
	@Override
	public Bestellung createBestellung(Bestellung bestellung, Long kundeId) {
		LOGGER.warn("Geschenkverpackung noch nicht implementiert");
		return bs.createBestellung(bestellung, kundeId);
	}
	
	/**
	 * {inheritDoc}
	 */
	@Override
	public Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde) {
		LOGGER.warn("Geschenkverpackung noch nicht implementiert");
		return bs.createBestellung(bestellung, kunde);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public List<AbstractArtikel> ladenhueter(int anzahl) {
		return bs.ladenhueter(anzahl);
	}

	/**
	 * {inheritDoc}
	 */


	/**
	 * {inheritDoc}
	 */

}
