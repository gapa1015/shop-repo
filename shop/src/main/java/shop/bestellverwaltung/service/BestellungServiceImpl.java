package shop.bestellverwaltung.service;

import static shop.util.Constants.KEINE_ID;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.collect.ImmutableMap;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.bestellverwaltung.domain.Bestellposition;
import shop.bestellverwaltung.domain.Bestellung;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.service.KundenService;
import shop.util.interceptor.Log;

@Dependent
@Log
public class BestellungServiceImpl implements Serializable, BestellungService {
	private static final long serialVersionUID = -9145947650157430928L;
	
	@Inject
	private transient EntityManager em;
	
	@Inject
	private KundenService ks;
	
	@Inject
	@NeueBestellung
	private transient Event<Bestellung> event;
	
	/**
	 * {inheritDoc}
	 * @exception ConstraintViolationException zu @NotNull, falls keine Bestellung gefunden wurde
	 */
	@Override
	@NotNull(message = "{bestellung.notFound.id}")
	public Bestellung findBestellungById(Long id, FetchType fetch) {
		if (id == null) {
			return null;
		}
	
		Bestellung bestellung = em.find(Bestellung.class, id);
		
		return bestellung;
	}

	/**
	 * {inheritDoc}
	 * @exception ConstraintViolationException zu @NotNull, falls kein Kunde gefunden wurde
	 */
	@Override
	@NotNull(message = "{bestellung.kunde.notFound.id}")
	public AbstractKunde findKundeById(Long id) {
		try {
			return em.createNamedQuery(Bestellung.FIND_KUNDE_BY_ID, AbstractKunde.class)
                     .setParameter(Bestellung.PARAM_ID, id)
					 .getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * {inheritDoc}
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Override
	@Size(min = 1, message = "{bestellung.notFound.kunde}")
	public List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		if (kunde == null) {
			return Collections.emptyList();
		}
		return em.createNamedQuery(Bestellung.FIND_BESTELLUNGEN_BY_KUNDE, Bestellung.class)
                 .setParameter(Bestellung.PARAM_KUNDE, kunde)
				 .getResultList();
	}
	
	
	/**
	 * {inheritDoc}
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	

	/**
	 * Zuordnung einer neuen, transienten Bestellung zu einem existierenden, persistenten Kunden.
	 * Der Kunde ist fuer den EntityManager bekannt, die Bestellung dagegen nicht. Das Zusammenbauen
	 * wird sowohl fuer einen Web Service aus auch fuer eine Webanwendung benoetigt.
	 */
	@Override
	public Bestellung createBestellung(Bestellung bestellung, Long kundeId) {
		if (bestellung == null) {
			return null;
		}
		
		// Den persistenten Kunden mit der transienten Bestellung verknuepfen
		final AbstractKunde kunde = ks.findKundeById(kundeId, KundenService.FetchType.MIT_BESTELLUNGEN);
		return createBestellung(bestellung, kunde);
	}
	
	/**
	 * Zuordnung einer neuen, transienten Bestellung zu einem existierenden, persistenten Kunden.
	 * Der Kunde ist fuer den EntityManager bekannt, die Bestellung dagegen nicht. Das Zusammenbauen
	 * wird sowohl fuer einen Web Service aus auch fuer eine Webanwendung benoetigt.
	 */
	@Override
	public Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde) {
		if (bestellung == null) {
			return null;
		}
		
		// Den persistenten Kunden mit der transienten Bestellung verknuepfen
		if (!em.contains(kunde)) {
			kunde = ks.findKundeById(kunde.getId(), KundenService.FetchType.MIT_BESTELLUNGEN);
		}
		kunde.addBestellung(bestellung);
		bestellung.setKunde(kunde);
		
		// Vor dem Abspeichern IDs zuruecksetzen:
		// IDs koennten einen Wert != null haben, wenn sie durch einen Web Service uebertragen wurden
		bestellung.setId(KEINE_ID);
		for (Bestellposition bp : bestellung.getBestellpositionen()) {
			bp.setId(KEINE_ID);
		}
		// FIXME JDK 8 hat Lambda-Ausdruecke
		//bestellung.getBestellpositionen()
		//          .forEach(bp -> bp.setId(KEINE_ID));
		
		em.persist(bestellung);
		event.fire(bestellung);

		return bestellung;
	}
	
	/**
	 * {inheritDoc}
	 */
	@Override
	public List<AbstractArtikel> ladenhueter(int anzahl) {
		return em.createNamedQuery(Bestellposition.FIND_LADENHUETER, AbstractArtikel.class)
				 .setMaxResults(anzahl)
				 .getResultList();
	}

	@Override
	public List<Bestellung> findBestellungenByIds(List<Long> ids,
			FetchType fetch) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * {inheritDoc}
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */


	/**
	 * {inheritDoc}
	 */
	
}
