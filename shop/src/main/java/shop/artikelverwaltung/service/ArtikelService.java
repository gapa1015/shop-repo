package shop.artikelverwaltung.service;



import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Rad;

public interface ArtikelService {
	AbstractArtikel findArtikelById(Long id);
	AbstractArtikel createArtikel(AbstractArtikel artikel);
	void updateRad(Rad rad);
}
