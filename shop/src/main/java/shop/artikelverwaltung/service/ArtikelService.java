package shop.artikelverwaltung.service;

import javax.validation.Valid;

import shop.artikelverwaltung.domain.Rad;

public interface ArtikelService {
	Rad findRadById(Long id);
	Rad createRad(@Valid Rad rad);
	void updateRad(Rad rad);
	
}
