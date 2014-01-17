package shop.artikelverwaltung.service;

import static shop.util.Constants.KEINE_ID;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
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

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Hersteller;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.service.KundenService;
import shop.util.interceptor.Log;

@Dependent
@Log
public class ArtikelServiceImp implements ArtikelService, Serializable {
	private static final long serialVersionUID = 3471955805910643313L;

	@Inject
	private transient EntityManager em;
	
	@Inject
	private KundenService ks;;
	
	@Override
	public List<AbstractArtikel> findVerfuegbareArtikel() {
		final TypedQuery<AbstractArtikel> query = em.createNamedQuery(AbstractArtikel.FIND_VERFUEGBARE_ARTIKEL, 
																	  AbstractArtikel.class);

		return query.getResultList();
	}

	@Override
	@NotNull(message = "{artikelverwaltung.artikel.notFound.id}")
	public AbstractArtikel findArtikelById(Long id) {
		return em.find(AbstractArtikel.class, id);
	}

	@Override
	@Size(min = 1, message = "{artikel.notFound.ids}")
	public List<AbstractArtikel> findArtikelByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<AbstractArtikel> criteriaQuery = builder
				.createQuery(AbstractArtikel.class);
		final Root<AbstractArtikel> a = criteriaQuery
				.from(AbstractArtikel.class);

		final Path<Long> idPath = a.get("id");

		Predicate pred = null;
		if (ids.size() == 1) {
			pred = builder.equal(idPath, ids.get(0));
		} 
		else {
			final Predicate[] equals = new Predicate[ids.size()];
			int i = 0;
			for (Long id : ids) {
				equals[i++] = builder.equal(idPath, id);
			}

			pred = builder.or(equals);
		}

		criteriaQuery.where(pred);

		return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	@NotNull(message = "{artikelverwaltung.artikel.notFound.name}")
	public List<AbstractArtikel> findArtikelByName(String name) {
		return em
				.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_BY_NAME,
						AbstractArtikel.class)
				.setParameter(AbstractArtikel.PARAM_NAME, "%" + name + "%")
				.getResultList();
	}

	@Override
	@NotNull(message = "{artikelverwaltung.artikel.notFound.preis}")
	public List<AbstractArtikel> findArtikelByPreis(BigDecimal preis) {

		return em
				.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_BY_PREIS,
						AbstractArtikel.class)
				.setParameter(AbstractArtikel.PARAM_PREIS, "%" + preis + "%")
				.getResultList();
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
	
	@Override
	public <T extends AbstractArtikel> T createArtikel(T artikel, Long herstellerId) {
		if (artikel == null) {
			return null;
		}
	
		final Hersteller hersteller = findHerstellerById(herstellerId);
				
		return createArtikel(artikel, hersteller);
	}
	
	@Override
	public <T extends AbstractArtikel> T createArtikel(T artikel, Hersteller hersteller) {
		if (artikel == null) {
			return null;
		}
		if (!em.contains(hersteller)) {
			hersteller = findHerstellerById(hersteller.getId());
		}
		
		artikel.setHersteller(hersteller);
		
		artikel.setId(KEINE_ID);
		
		em.persist(artikel);

		return artikel;
	}
	
	@Override
	public <T> T updateArtikel(T artikel) {
        if (artikel == null)
            return null;
    
        em.merge(artikel);
        return artikel;
	}
	
	@Override
	@NotNull(message = "{artikelverwaltung.hersteller.notFound.id}")
	public Hersteller findHerstellerById(Long id) {
		return em.find(Hersteller.class, id);
	}
	
	@Override
	public Hersteller findHerstellerByName(String name) {
		final TypedQuery<Hersteller> query = em.createNamedQuery(Hersteller.FIND_HERSTELLER_BY_NAME,
                								Hersteller.class)
                							   .setParameter(Hersteller.PARAM_NAME, name);
		
		return (Hersteller) query.getResultList();
	}
	
	@Override
	public Hersteller createHersteller(Hersteller hersteller) {
		if (hersteller == null) {
			return hersteller;
		}
		
		ks.createAdresse(hersteller.getAdresse());
		
		em.persist(hersteller);
		return hersteller;
	}
}
