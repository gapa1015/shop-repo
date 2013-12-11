package shop.artikelverwaltung.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;

import shop.artikelverwaltung.domain.Artikel;
import shop.artikelverwaltung.domain.Rad;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class ArtikelServiceImp implements ArtikelService, Serializable {
	private static final long serialVersionUID = 3471955805910643313L;

	@Override
	@NotNull(message = "{artikelverwaltung.artikel.notFound.id}")
	public Artikel findArtikelById(Long id) {
		 return Mock.findArtikelById(id);
	 }

	@Override
	public Artikel createArtikel(Artikel artikel) {
		return 	Mock.createArtikel(artikel);
	}

	@Override
	public void updateRad(Rad rad) {
		Mock.updateRad(rad);
	}
}
