package shop.bestellverwaltung.service;

import static shop.util.Constants.KEINE_ID;
import static shop.util.Constants.LOADGRAPH;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

import shop.artikelverwaltung.domain.Artikel;
import shop.bestellverwaltung.domain.Bestellposition;
import shop.bestellverwaltung.domain.Bestellung;
import shop.bestellverwaltung.domain.Lieferung;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.service.KundeService;
import shop.util.interceptor.Log;

@Dependent
@Log
public class BestellungServiceImpl implements Serializable, BestellungService {
	private static final long serialVersionUID = -9145947650157430928L;
	
	@Inject
	private transient EntityManager em;
	
	@Inject
	private KundeService ks;
	
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
		
		Bestellung bestellung;
		EntityGraph<?> entityGraph;
		Map<String, Object> props;
		switch (fetch) {
			case NUR_BESTELLUNG:
				bestellung = em.find(Bestellung.class, id);
				break;
				
			case MIT_LIEFERUNGEN:
				entityGraph = em.getEntityGraph(Bestellung.GRAPH_LIEFERUNGEN);
				props = ImmutableMap.of(LOADGRAPH, (Object) entityGraph);
				bestellung = em.find(Bestellung.class, id, props);
				break;
				
			default:
				bestellung = em.find(Bestellung.class, id);
				break;
		}
		
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
	@Override
	@Size(min = 1, message = "{bestellung.notFound.ids}")
	public List<Bestellung> findBestellungenByIds(List<Long> ids, FetchType fetch) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}
		
		// SELECT b
		// FROM   Bestellung b
		// WHERE  b.id = <id> OR ...

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Bestellung> criteriaQuery  = builder.createQuery(Bestellung.class);
		final Root<Bestellung> b = criteriaQuery.from(Bestellung.class);
		
		// Die Vergleichen mit "=" als Liste aufbauen
		final Path<Long> idPath = b.get("id");
		final List<Predicate> predList = new ArrayList<>();
		for (Long id : ids) {
			final Predicate equal = builder.equal(idPath, id);
			predList.add(equal);
		}
		// Die Vergleiche mit "=" durch "or" verknuepfen
		final Predicate[] predArray = new Predicate[predList.size()];
		final Predicate pred = builder.or(predList.toArray(predArray));
		criteriaQuery.where(pred).distinct(true);

		final TypedQuery<Bestellung> query = em.createQuery(criteriaQuery);
		if (FetchType.MIT_LIEFERUNGEN.equals(fetch)) {
			final EntityGraph<?> entityGraph = em.getEntityGraph(Bestellung.GRAPH_LIEFERUNGEN);
			query.setHint(LOADGRAPH, entityGraph);
		}
				
		return query.getResultList();
	}

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
		final AbstractKunde kunde = ks.findKundeById(kundeId, KundeService.FetchType.MIT_BESTELLUNGEN);
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
			kunde = ks.findKundeById(kunde.getId(), KundeService.FetchType.MIT_BESTELLUNGEN);
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
	public List<Artikel> ladenhueter(int anzahl) {
		return em.createNamedQuery(Bestellposition.FIND_LADENHUETER, Artikel.class)
				 .setMaxResults(anzahl)
				 .getResultList();
	}
	
	/**
	 * {inheritDoc}
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Override
	@Size(min = 1, message = "{lieferung.notFound.nr}")
	public List<Lieferung> findLieferungen(String nr) {
		final EntityGraph<?> entityGraph = em.getEntityGraph(Lieferung.GRAPH_BESTELLUNGEN);
		return em.createNamedQuery(Lieferung.FIND_LIEFERUNGEN_BY_LIEFERNR, Lieferung.class)
                 .setParameter(Lieferung.PARAM_LIEFERNR, nr)
                 .setHint(LOADGRAPH, entityGraph)
                 .getResultList();
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public Lieferung createLieferung(Lieferung lieferung, List<Bestellung> bestellungen) {
		if (lieferung == null || bestellungen == null || bestellungen.isEmpty()) {
			return null;
		}
		
		// Beziehungen zu existierenden Bestellungen aktualisieren
		
		// Ids ermitteln
		final List<Long> ids = new ArrayList<>();
		for (Bestellung b : bestellungen) {
			ids.add(b.getId());
		}
		// FIXME JDK 8 hat Lambda-Ausdruecke
		//bestellungen.forEach(b -> ids.add(b.getId()));
		
		bestellungen = findBestellungenByIds(ids, FetchType.MIT_LIEFERUNGEN);
		lieferung.setBestellungenAsList(bestellungen);
		for (Bestellung bestellung : bestellungen) {
			bestellung.addLieferung(lieferung);
		}
		// FIXME JDK 8 hat Lambda-Ausdruecke
		//bestellungen.forEach(b -> b.addLieferung(lieferung));
		
		lieferung.setId(KEINE_ID);
		em.persist(lieferung);		
		return lieferung;
	}
}
