package shop.artikelverwaltung.service;



import shop.artikelverwaltung.domain.Artikel;
import shop.artikelverwaltung.domain.Rad;

public interface ArtikelService {
	Artikel findArtikelById(Long id);
	Artikel createArtikel(Artikel artikel);
	void updateRad(Rad rad);
}
