package shop.artikelverwaltung.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import shop.artikelverwaltung.domain.Rad;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class ArtikelServiceImp implements ArtikelService, Serializable {
	private static final long serialVersionUID = 3471955805910643313L;

	@Override
	@NotNull(message = "{artikelverwaltung.artikel.notFound.id}")
	public Rad findRadById(Long id) {
		 return Mock.findRadById(id);
	 }

	@Override
	public Rad createRad(@Valid Rad rad) {
		return 	Mock.createRad(rad);
	}

	@Override
	public void updateRad(Rad rad) {
		Mock.updateRad(rad);
	}
}
