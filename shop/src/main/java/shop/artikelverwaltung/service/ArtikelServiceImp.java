package shop.artikelverwaltung.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fest.util.Strings;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Rad;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class ArtikelServiceImp implements ArtikelService, Serializable {
	private static final long serialVersionUID = 3471955805910643313L;
	
 @Inject
 private transient EntityManager em;
 
 //TODO Fehlermeldung
 public List<AbstractArtikel> findVerfuegbareArtikel() {
		return em.createNamedQuery(AbstractArtikel.FIND_VERFUEGBARE_ARTIKEL, AbstractArtikel.class)
				 .getResultList();
	}


	@Override
	@NotNull(message = "{artikelverwaltung.artikel.notFound.id}")
	public AbstractArtikel findArtikelById(Long id) {
		 return em.find(AbstractArtikel.class,id);
	 }
	
	@Override
	@Size(min = 1, message = "{artikel.notFound.ids}")
	public List<AbstractArtikel> findArtikelByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}
		
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<AbstractArtikel> criteriaQuery = builder.createQuery(AbstractArtikel.class);
		final Root<AbstractArtikel> a = criteriaQuery.from(AbstractArtikel.class);

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
		
		return em.createQuery(criteriaQuery)
		         .getResultList();
	}
	
	@Override
	@NotNull //TODO Message
	public List<AbstractArtikel> findArtikelByName(String name) {
		
		if (Strings.isNullOrEmpty(name)) {
			return findVerfuegbareArtikel();
		}
		
				return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_BY_NAME,AbstractArtikel.class)
				.setParameter(AbstractArtikel.PARAM_NAME, "%" + name + "%")
				.getResultList();
	}
	
	@Override
	@NotNull //TODO Message
	public List<AbstractArtikel> findArtikelByPreis(BigDecimal preis){
		return null;
		
	}

	@Override
	public <T extends AbstractArtikel > T createArtikel(T artikel) {
			if (artikel == null) {
			return artikel;
		}
		em.persist(artikel);
		return artikel;
	}
			
	

	@Override
	public void updateRad(Rad rad) {
		Mock.updateRad(rad);
	}
}
