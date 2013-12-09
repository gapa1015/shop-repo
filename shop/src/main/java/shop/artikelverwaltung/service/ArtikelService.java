package shop.artikelverwaltung.service;

import shop.artikelverwaltung.domain.Rad;

public interface ArtikelService {
	Rad findRadById(Long id);
	Rad createRad(Rad rad);
	void updateRad(Rad rad);
	
}
