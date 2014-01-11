package shop.kundenverwaltung.service;

import static shop.util.Constants.LOADGRAPH;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.jboss.logging.Logger;

import com.google.common.collect.ImmutableMap;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.domain.AbstractKunde_;
import shop.kundenverwaltung.domain.Adresse;
import shop.kundenverwaltung.domain.Adresse_;
import shop.kundenverwaltung.domain.Bankdaten;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class KundenService implements Serializable {
	private static final long serialVersionUID = -4188395218729678116L;
	private static final Logger LOGGER = Logger.getLogger (MethodHandles.lookup().lookupClass());

	@Inject transient EntityManager em;
	
	public enum FetchType {
		NUR_KUNDE,
		MIT_BESTELLUNGEN,
	}
	
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
				
			default:
				kunde = em.find(AbstractKunde.class, id);
				break;
		}
		
		return kunde;
	
		
	}
	
	public List<AbstractKunde> findAllKunden() {
		return Mock.findAllKunden();
	}
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.email}")
	public AbstractKunde findKundeByEmail(String email) {
	
	return em.createNamedQuery(AbstractKunde.KUNDE_BY_EMAIL, AbstractKunde.class)
			.setParameter("email", email)
			.getSingleResult();
		
	}
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.nachname}")
	public List<? extends AbstractKunde> findKundenByNachname(String nachname) {
		if (nachname == null)
			return null;
		return Mock.findKundenByNachname(nachname);
	}
	
	
	public List <AbstractKunde> findKundeByNachname (String nachname) {
		CriteriaBuilder builder = em.getCriteriaBuilder ();
		CriteriaQuery <AbstractKunde> criteriaQuery = builder.createQuery(AbstractKunde.class);
		
		Root <AbstractKunde> k = criteriaQuery.from(AbstractKunde.class);
		
		Path <String> nachnamePath = k.get(AbstractKunde_.nachname);
		
		Predicate pred = builder.equal(nachnamePath, nachname);
		criteriaQuery.where(pred);
		 List <AbstractKunde> kunde = em.createQuery(criteriaQuery)
				 					.getResultList();
		return kunde;
		
	}
		public List <AbstractKunde> findKundeByEmailCriteria (String email) {
		CriteriaBuilder builder = em.getCriteriaBuilder ();
		CriteriaQuery <AbstractKunde> criteriaQuery = builder.createQuery(AbstractKunde.class);
		
		Root <AbstractKunde> k = criteriaQuery.from(AbstractKunde.class);
		
		Path <String> emailPath = k.get(AbstractKunde_.email);
		
		Predicate pred = builder.equal(emailPath, email);
		criteriaQuery.where(pred);
		 List <AbstractKunde> kunde = em.createQuery(criteriaQuery)
				 					.getResultList();
		return kunde;		
	}
	
	public List <AbstractKunde> findKundeByPlzCriteria (String plz) {
		CriteriaBuilder builder = em.getCriteriaBuilder ();
		CriteriaQuery <AbstractKunde> criteriaQuery = builder.createQuery(AbstractKunde.class);
		
		Root <AbstractKunde> k = criteriaQuery.from(AbstractKunde.class);
		
		Path <Adresse> adressePath = k.get(AbstractKunde_.adresse);
		Path <String> plzPath = adressePath.get(Adresse_.plz);
		
		Predicate pred = builder.equal(plzPath, plz);
		criteriaQuery.where(pred);
		 List <AbstractKunde> kunde = em.createQuery(criteriaQuery)
				 					.getResultList();
		return kunde;
		
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
