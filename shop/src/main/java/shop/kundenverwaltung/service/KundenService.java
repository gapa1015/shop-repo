package shop.kundenverwaltung.service;

import static shop.util.Constants.LOADGRAPH;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.domain.Adresse;
import shop.kundenverwaltung.domain.Bankdaten;
import shop.util.interceptor.Log;

@Dependent
@Log
public class KundenService implements Serializable {
	private static final long serialVersionUID = -4188395218729678116L;

	@Inject transient EntityManager em;
	
	public enum FetchType {
		NUR_KUNDE,
		MIT_BESTELLUNGEN,
		MIT_WARTUNGSVERTRAEGEN
	}
	
	public enum OrderType {
        KEINE,
        ID
	}
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.id}")
	public AbstractKunde findKundeById(Long id, FetchType fetch) {
		if (id == null)
			return null;
		final AbstractKunde kunde = em.find(AbstractKunde.class, id);
		return kunde;
		
	}

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

			default:
				break;
		}

		return query.getResultList();
	}
	
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
	
	@Size(min = 1, message = "{kunde.notFound.plz}")
	public List<AbstractKunde> findKundenByPLZ(String plz) {
		return em.createNamedQuery(AbstractKunde.FIND_KUNDEN_BY_PLZ, AbstractKunde.class)
                 .setParameter(AbstractKunde.PARAM_KUNDE_ADRESSE_PLZ, plz)
                 .getResultList();
	}
	
	public List<Long> findIdsByPrefix(String idPrefix) {
		return em.createNamedQuery(AbstractKunde.FIND_IDS_BY_PREFIX, Long.class)
				 .setParameter(AbstractKunde.PARAM_KUNDE_ID_PREFIX, idPrefix + '%')
				 .getResultList();
	}
	
	public List<String> findNachnamenByPrefix(String nachnamePrefix) {
		return em.createNamedQuery(AbstractKunde.FIND_NACHNAMEN_BY_PREFIX, String.class)
				 .setParameter(AbstractKunde.PARAM_KUNDE_NACHNAME_PREFIX, nachnamePrefix + '%')
				 .getResultList();
	}
	
	public <T extends AbstractKunde> T createKunde(T kunde) {
		if (kunde == null) {
			return null;
		}
		kunde.setId(null);
		createAdresse(kunde.getAdresse());
		createBankdaten(kunde.getBankdaten());
		em.persist(kunde);
		return kunde;
	}
	
	public Adresse createAdresse(Adresse adresse) {
		if (adresse == null) {
			return null;
		}
		adresse.setId(null);
		em.persist(adresse);
		return adresse;
	}
	
	public Bankdaten createBankdaten(Bankdaten bankdaten) {
		if (bankdaten == null) {
			return null;
		}
		bankdaten.setId(null);
		em.persist(bankdaten);
		return bankdaten;
	}
		
	public <T extends AbstractKunde> T updateKunde(T kunde) {
		if (kunde == null)
			return null;
		
		kunde = em.merge(kunde);
		return kunde;
	}
	
	public void deleteKunde(AbstractKunde kunde) {
		if (!em.contains(kunde)) {
			kunde = em.find(AbstractKunde.class, kunde.getId());
			if (kunde == null)
				return;
		}
		em.remove(kunde);
	}
}
