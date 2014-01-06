package shop.artikelverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

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
	@NotNull
	public List<AbstractArtikel> findArtikelByName(String name) {
		
				return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_BY_NAME,AbstractArtikel.class)
				.setParameter(AbstractArtikel.PARAM_NAME, "%" + name + "%")
				.getResultList();
	}

	@Override
	public AbstractArtikel createArtikel(AbstractArtikel artikel) {
		return 	Mock.createArtikel(artikel);
	}

	@Override
	public void updateRad(Rad rad) {
		Mock.updateRad(rad);
	}
}
