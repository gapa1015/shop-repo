package shop.artikelverwaltung.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Rad;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class ArtikelServiceImp implements ArtikelService, Serializable {
	private static final long serialVersionUID = 3471955805910643313L;

	@Override
	@NotNull(message = "{artikelverwaltung.artikel.notFound.id}")
	public AbstractArtikel findArtikelById(Long id) {
		 return Mock.findArtikelById(id);
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
