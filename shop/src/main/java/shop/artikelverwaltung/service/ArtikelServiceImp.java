package shop.artikelverwaltung.service;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import shop.artikelverwaltung.domain.Rad;
import shop.util.Mock;

public class ArtikelServiceImp implements ArtikelService, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	@NotNull(message = "{artikel.id.notNull}")
	public Rad findRadById(Long id) {
		 return Mock.findRadById(id);
	 }

	@Override
	public Rad createRad(Rad rad) {
		return 	Mock.createRad(rad);
	}

	@Override
	public void updateRad(Rad rad) {
		Mock.updateRad(rad);
	}
}
