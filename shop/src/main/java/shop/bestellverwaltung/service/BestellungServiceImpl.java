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

import shop.bestellverwaltung.domain.Bestellposition;
import shop.kundenverwaltung.service.KundenService;
import shop.bestellverwaltung.domain.Lieferung;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.bestellverwaltung.domain.Bestellung;
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
	
	@Override
	@NotNull(message = "{bestellung.notFound.id}")
	public Bestellung findBestellungById(Long id, FetchType fetch) {
		if (id == null) {
			return null;
		}
		return em.find(Bestellung.class, id);
	}

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
	
	@Override
	@Size(min = 1, message = "{bestellung.notFound.ids}")
	public List<Bestellung> findBestellungenByIds(List<Long> ids, FetchType fetch) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Bestellung> criteriaQuery  = builder.createQuery(Bestellung.class);
		final Root<Bestellung> b = criteriaQuery.from(Bestellung.class);
		
		final Path<Long> idPath = b.get("id");
		final List<Predicate> predList = new ArrayList<>();
		for (Long id : ids) {
			final Predicate equal = builder.equal(idPath, id);
			predList.add(equal);
		}
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

	@Override
	public Bestellung createBestellung(Bestellung bestellung, Long kundeId) {
		if (bestellung == null) {
			return null;
		}
		
		final AbstractKunde kunde = ks.findKundeById(kundeId, KundenService.FetchType.MIT_BESTELLUNGEN);
		return createBestellung(bestellung, kunde);
	}
	
	@Override
	public Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde) {
		if (bestellung == null) {
			return null;
		}
		
		if (!em.contains(kunde)) {
			kunde = ks.findKundeById(kunde.getId(), KundenService.FetchType.MIT_BESTELLUNGEN);
		}
		kunde.addBestellung(bestellung);
		bestellung.setKunde(kunde);
		
		bestellung.setId(KEINE_ID);
		for (Bestellposition bp : bestellung.getBestellpositionen()) {
			bp.setId(KEINE_ID);
		}
		
		em.persist(bestellung);
		event.fire(bestellung);

		return bestellung;
	}
	
	@Override
	@Size(min = 1, message = "{lieferung.notFound.nr}")
	public List<Lieferung> findLieferungen(String nr) {
		final EntityGraph<?> entityGraph = em.getEntityGraph(Lieferung.GRAPH_BESTELLUNGEN);
		return em.createNamedQuery(Lieferung.FIND_LIEFERUNGEN_BY_LIEFERNR, Lieferung.class)
                 .setParameter(Lieferung.PARAM_LIEFERNR, nr)
                 .setHint(LOADGRAPH, entityGraph)
                 .getResultList();
	}

	@Override
	public Lieferung createLieferung(Lieferung lieferung, List<Bestellung> bestellungen) {
		if (lieferung == null || bestellungen == null || bestellungen.isEmpty()) {
			return null;
		}
		
		final List<Long> ids = new ArrayList<>();
		for (Bestellung b : bestellungen) {
			ids.add(b.getId());
		}
		
		bestellungen = findBestellungenByIds(ids, FetchType.MIT_LIEFERUNGEN);
		lieferung.setBestellungenAsList(bestellungen);
		for (Bestellung bestellung : bestellungen) {
			bestellung.addLieferung(lieferung);
		}
		
		lieferung.setId(KEINE_ID);
		em.persist(lieferung);		
		return lieferung;
	}
}
