package shop.artikelverwaltung.service;



import java.util.List;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Rad;

public interface ArtikelService {
	AbstractArtikel findArtikelById(Long id);
	List<AbstractArtikel> findArtikelByIds(List<Long> ids); 
	List<AbstractArtikel> findArtikelByName (String name);
	<T extends AbstractArtikel> T createArtikel(T artikel);
	void updateRad(Rad rad);
}
