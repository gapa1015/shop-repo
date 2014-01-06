package shop.artikelverwaltung.service;



import java.util.List;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Rad;

public interface ArtikelService {
	AbstractArtikel findArtikelById(Long id);
	List<AbstractArtikel> findArtikelByName (String name);
	AbstractArtikel createArtikel(AbstractArtikel artikel);
	void updateRad(Rad rad);
}
