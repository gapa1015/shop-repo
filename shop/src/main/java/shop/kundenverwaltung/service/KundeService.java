package shop.kundenverwaltung.service;

import static shop.util.Constants.LOADGRAPH;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.collect.ImmutableMap;

import shop.bestellverwaltung.domain.Bestellposition;
import shop.bestellverwaltung.domain.Bestellposition_;
import shop.bestellverwaltung.domain.Bestellung;
import shop.bestellverwaltung.domain.Bestellung_;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.domain.AbstractKunde_;
import shop.kundenverwaltung.domain.Adresse_;
import shop.kundenverwaltung.domain.Wartungsvertrag;
import shop.util.interceptor.Log;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Dependent
@Log
public class KundeService implements Serializable {
	private static final long serialVersionUID = -5520738420154763865L;
	
	public enum FetchType {
		NUR_KUNDE,
		MIT_BESTELLUNGEN,
		MIT_WARTUNGSVERTRAEGEN
	}
	
	public enum OrderType {
		KEINE,
		ID
	}
	
	@Inject
	private transient EntityManager em;

	/**
	 * Suche einen Kunden zu gegebener ID.
	 * @param id Die gegebene ID.
	 * @param fetch Angabe, welche Objekte aus der DB mitgeladen werden sollen, z.B. Bestellungen.
	 * @return Der gefundene Kunde.
	 * @exception ConstraintViolationException zu @NotNull, falls kein Kunde gefunden wurde
	 */
	@NotNull(message = "{kunde.notFound.id}")
	public AbstractKunde findKundeById(Long id, FetchType fetch) {
		if (id == null) {
			return null;
		}
		
		AbstractKunde kunde;
		EntityGraph<?> entityGraph;
		Map<String, Object> props;
		switch (fetch) {
			case NUR_KUNDE:
				kunde = em.find(AbstractKunde.class, id);
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				props = ImmutableMap.of(LOADGRAPH, (Object) entityGraph);
				kunde = em.find(AbstractKunde.class, id, props);
				break;
				
			case MIT_WARTUNGSVERTRAEGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_WARTUNGSVERTRAEGE);
				props = ImmutableMap.of(LOADGRAPH, (Object) entityGraph);
				kunde = em.find(AbstractKunde.class, id, props);
				break;

			default:
				kunde = em.find(AbstractKunde.class, id);
				break;
		}
		
		return kunde;
	}

	/**
	 * Suche nach IDs mit gleichem Praefix.
	 * @param idPrefix Der gemeinsame Praefix.
	 * @return Liste der passenden Praefixe.
	 */
	public List<Long> findIdsByPrefix(String idPrefix) {
		return em.createNamedQuery(AbstractKunde.FIND_IDS_BY_PREFIX, Long.class)
				 .setParameter(AbstractKunde.PARAM_KUNDE_ID_PREFIX, idPrefix + '%')
				 .getResultList();
	}
	
	/**
	 * Suche einen Kunden zu gegebener Email-Adresse.
	 * @param email Die gegebene Email-Adresse.
	 * @return Der gefundene Kunde.
	 * @exception ConstraintViolationException zu @NotNull, falls kein Kunde gefunden wurde
	 */
	@NotNull(message = "{kunde.notFound.email}")
	public AbstractKunde findKundeByEmail(String email) {
		try {
			return em.createNamedQuery(AbstractKunde.FIND_KUNDE_BY_EMAIL, AbstractKunde.class)
					 .setParameter(AbstractKunde.PARAM_KUNDE_EMAIL, email)
					 .getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Suche alle Kunden.
	 * @param fetch Angabe, welche Objekte aus der DB mitgeladen werden sollen, z.B. Bestellungen.
	 * @param order Sortierreihenfolge, z.B. noch ID
	 * @return Liste der Kunden
	 */
	public List<AbstractKunde> findAllKunden(FetchType fetch, OrderType order) {
		final TypedQuery<AbstractKunde> query = OrderType.ID.equals(order)
		                                        ? em.createNamedQuery(AbstractKunde.FIND_KUNDEN_ORDER_BY_ID,
		                                        		              AbstractKunde.class)
		                                        : em.createNamedQuery(AbstractKunde.FIND_KUNDEN, AbstractKunde.class);
		
		EntityGraph<?> entityGraph;
		switch (fetch) {
			case NUR_KUNDE:
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			case MIT_WARTUNGSVERTRAEGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_WARTUNGSVERTRAEGE);
				query.setHint(LOADGRAPH, entityGraph);
				break;

			default:
				break;
		}

		return query.getResultList();
	}
	
	/**
	 * Suche alle Kunden mit gleichem Nachnamen
	 * @param nachname Der gemeinsame Nachname
	 * @param fetch Angabe, welche Objekte aus der DB mitgeladen werden sollen, z.B. Bestellungen.
	 * @return Liste der gefundenen Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.nachname}")
	public List<AbstractKunde> findKundenByNachname(String nachname, FetchType fetch) {
		final TypedQuery<AbstractKunde> query = em.createNamedQuery(AbstractKunde.FIND_KUNDEN_BY_NACHNAME,
                                                                    AbstractKunde.class)
                                                  .setParameter(AbstractKunde.PARAM_KUNDE_NACHNAME, nachname);
		
		EntityGraph<?> entityGraph;
		switch (fetch) {
			case NUR_KUNDE:
				break;
				
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			case MIT_WARTUNGSVERTRAEGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_WARTUNGSVERTRAEGE);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			default:
				break;
		}
		
		return query.getResultList();
	}

	/**
	 * Suche alle Nachnamen mit gleichem Praefix
	 * @param nachnamePrefix Der gemeinsame Praefix
	 * @return Liste der passenden Nachnamen
	 */
	public List<String> findNachnamenByPrefix(String nachnamePrefix) {
		return em.createNamedQuery(AbstractKunde.FIND_NACHNAMEN_BY_PREFIX, String.class)
				 .setParameter(AbstractKunde.PARAM_KUNDE_NACHNAME_PREFIX, nachnamePrefix + '%')
				 .getResultList();
	}
	
	/**
	 * Die Kunden mit gleicher Postleitzahl suchen.
	 * @param plz Die Postleitzahl
	 * @return Liste der passenden Kunden.
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.plz}")
	public List<AbstractKunde> findKundenByPLZ(String plz) {
		return em.createNamedQuery(AbstractKunde.FIND_KUNDEN_BY_PLZ, AbstractKunde.class)
                 .setParameter(AbstractKunde.PARAM_KUNDE_ADRESSE_PLZ, plz)
                 .getResultList();
	}

	/**
	 * Diejenigen Kunden suchen, die seit einem bestimmten Datum registriert sind. 
	 * @param seit Das zu vergleichende Datum
	 * @return Die Liste der passenden Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.seit}")
	public List<AbstractKunde> findKundenBySeit(Date seit) {
		return em.createNamedQuery(AbstractKunde.FIND_KUNDEN_BY_DATE, AbstractKunde.class)
                 .setParameter(AbstractKunde.PARAM_KUNDE_SEIT, seit)
                 .getResultList();
	}
	
	/**
	 * Alle Privat- und Firmenkunden suchen.
	 * @return Liste der Privat- und Firmenkunden.
	 */
	public List<AbstractKunde> findPrivatkundenFirmenkunden() {
		return em.createNamedQuery(AbstractKunde.FIND_PRIVATKUNDEN_FIRMENKUNDEN, AbstractKunde.class)
                 .getResultList();
	}
	
	/**
	 * Kunden mit gleichem Nachnamen durch eine Criteria-Query suchen.
	 * @param nachname Der gemeinsame Nachname.
	 * @return Liste der passenden Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.nachname}")
	public List<AbstractKunde> findKundenByNachnameCriteria(String nachname) {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<AbstractKunde> criteriaQuery = builder.createQuery(AbstractKunde.class);
		final Root<AbstractKunde> k = criteriaQuery.from(AbstractKunde.class);

		final Path<String> nachnamePath = k.get(AbstractKunde_.nachname);
		//final Path<String> nachnamePath = k.get("nachname");
		
		final Predicate pred = builder.equal(nachnamePath, nachname);
		criteriaQuery.where(pred);
		
		// Ausgabe des komponierten Query-Strings. Voraussetzung: das Modul "org.hibernate" ist aktiviert
		//LOGGER.tracef("", em.createQuery(criteriaQuery).unwrap(org.hibernate.Query.class).getQueryString());
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	/**
	 * Die Kunden mit einer bestimmten Mindestbestellmenge suchen.
	 * @param minMenge Die Mindestbestellmenge
	 * @return Liste der passenden Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.minBestMenge}")
	public List<AbstractKunde> findKundenMitMinBestMenge(short minMenge) {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<AbstractKunde> criteriaQuery  = builder.createQuery(AbstractKunde.class);
		final Root<AbstractKunde> k = criteriaQuery.from(AbstractKunde.class);

		final Join<AbstractKunde, Bestellung> b = k.join(AbstractKunde_.bestellungen);
		final Join<Bestellung, Bestellposition> bp = b.join(Bestellung_.bestellpositionen);
		criteriaQuery.where(builder.gt(bp.<Short>get(Bestellposition_.anzahl), minMenge))
		             .distinct(true);
		
		return em.createQuery(criteriaQuery)
		         .getResultList();
	}

	/**
	 * Kunden zu den Suchkriterien suchen
	 * @param email Email-Adresse
	 * @param nachname Nachname
	 * @param plz Postleitzahl
	 * @üaram seit Datum seit
	 * @param minBestMenge Mindestbestellmenge
	 * @return Die gefundenen Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@NotNull(message = "{kunde.notFound.criteria}")
	public List<AbstractKunde> findKundenByCriteria(String email, String nachname, String plz, Date seit,
			                                        Short minBestMenge) {
		// SELECT DISTINCT k
		// FROM   AbstractKunde k
		// WHERE  email = ? AND nachname = ? AND k.adresse.plz = ? and seit = ?
		
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<AbstractKunde> criteriaQuery  = builder.createQuery(AbstractKunde.class);
		final Root<? extends AbstractKunde> k = criteriaQuery.from(AbstractKunde.class);
		
		Predicate pred = null;
		if (email != null) {
			final Path<String> emailPath = k.get(AbstractKunde_.email);
			pred = builder.equal(emailPath, email);
		}
		if (nachname != null) {
			final Path<String> nachnamePath = k.get(AbstractKunde_.nachname);
			final Predicate tmpPred = builder.equal(nachnamePath, nachname);
			pred = pred == null ? tmpPred : builder.and(pred, tmpPred);
		}
		if (plz != null) {
			final Path<String> plzPath = k.get(AbstractKunde_.adresse)
                                          .get(Adresse_.plz);
			final Predicate tmpPred = builder.equal(plzPath, plz);
			pred = pred == null ? tmpPred : builder.and(pred, tmpPred);
		}
		if (seit != null) {
			final Path<Date> seitPath = k.get(AbstractKunde_.seit);
			final Predicate tmpPred = builder.equal(seitPath, seit);
			pred = pred == null ? tmpPred : builder.and(pred, tmpPred);
		}
		if (minBestMenge != null) {
			final Path<Short> anzahlPath = k.join(AbstractKunde_.bestellungen)
                                            .join(Bestellung_.bestellpositionen)
                                            .get(Bestellposition_.anzahl);
			final Predicate tmpPred = builder.gt(anzahlPath, minBestMenge);
			pred = pred == null ? tmpPred : builder.and(pred, tmpPred);
		}
		
		criteriaQuery.where(pred)
		             .distinct(true);
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	/**
	 * Einen neuen Kunden in der DB anlegen.
	 * @param kunde Der anzulegende Kunde.
	 * @return Der neue Kunde einschliesslich generierter ID.
	 */
	public <T extends AbstractKunde> T createKunde(T kunde) {
		if (kunde == null) {
			return kunde;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		final AbstractKunde tmp = findKundeByEmail(kunde.getEmail());   // Kein Aufruf als Business-Methode
		if (tmp != null) {
			throw new EmailExistsException(kunde.getEmail());
		}
		
		em.persist(kunde);
		return kunde;		
	}
	
	/**
	 * Einen vorhandenen Kunden aktualisieren
	 * @param kunde Der Kunde mit aktualisierten Attributwerten
	 * @return Der aktualisierte Kunde
	 */
	public <T extends AbstractKunde> T updateKunde(T kunde) {
		if (kunde == null) {
			return null;
		}
		
		// kunde vom EntityManager trennen, weil anschliessend z.B. nach Id und Email gesucht wird
		em.detach(kunde);
		
		// Gibt es ein anderes Objekt mit gleicher Email-Adresse?
		final AbstractKunde tmp = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
		if (tmp != null) {
			em.detach(tmp);
			if (tmp.getId().longValue() != kunde.getId().longValue()) {
				// anderes Objekt mit gleichem Attributwert fuer email
				throw new EmailExistsException(kunde.getEmail());
			}
		}

		em.merge(kunde);
		return kunde;
	}

	/**
	 * Einen Kunden aus der DB loeschen, falls er existiert.
	 * @param kunde Der zu loeschende Kunde.
	 */
	public void deleteKunde(AbstractKunde kunde) {
		if (kunde == null) {
			return;
		}
		
		// Bestellungen laden, damit sie anschl. ueberprueft werden koennen
		kunde = findKundeById(kunde.getId(), FetchType.MIT_BESTELLUNGEN);  // Kein Aufruf als Business-Methode
		if (kunde == null) {
			return;
		}
		
		// Gibt es Bestellungen?
		if (!kunde.getBestellungen().isEmpty()) {
			throw new KundeDeleteBestellungException(kunde);
		}

		em.remove(kunde);
	}

	/**
	 * Wartungsvertrage zu einem Kunden suchen
	 * @param kundeId ID des Kunden
	 * @return Liste der Wartungsvertraege des Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{wartungsvertrag.notFound.kundeId}")
	public List<Wartungsvertrag> findWartungsvertraege(Long kundeId) {
		return em.createNamedQuery(Wartungsvertrag.FIND_WARTUNGSVERTRAEGE_BY_KUNDE_ID, Wartungsvertrag.class)
                 .setParameter(Wartungsvertrag.PARAM_KUNDE_ID, kundeId)
                 .getResultList();
	}
	
	/**
	 * Einen neuen Wartungsvertrag in der DB anlegen.
	 * @param wartungsvertrag Der neu anzulegende Wartungsvertrag
	 * @param kunde Der zugehoerige Kunde
	 * @return Der neu angelegte Wartungsvertrag
	 */
	public Wartungsvertrag createWartungsvertrag(Wartungsvertrag wartungsvertrag, AbstractKunde kunde) {
		if (wartungsvertrag == null || kunde == null) {
			return null;
		}
		
		kunde = findKundeById(kunde.getId(), FetchType.NUR_KUNDE);
		wartungsvertrag.setKunde(kunde);
		kunde.addWartungsvertrag(wartungsvertrag);
		
		em.persist(wartungsvertrag);
		return wartungsvertrag;
	}
}
