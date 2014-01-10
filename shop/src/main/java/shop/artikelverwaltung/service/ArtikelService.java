package shop.artikelverwaltung.service;

import java.math.BigDecimal;
import java.util.List;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Hersteller;
import shop.artikelverwaltung.domain.Lieferant;

public interface ArtikelService {
	AbstractArtikel findArtikelById(Long id);

	List<AbstractArtikel> findArtikelByIds(List<Long> ids);

	List<AbstractArtikel> findArtikelByName(String name);

	List<AbstractArtikel> findArtikelByPreis(BigDecimal preis);

	<T extends AbstractArtikel> T createArtikel(T artikel);

	<T> T updateArtikel(T artikel);
	
	Lieferant findLieferantById(Long id);
	
	Lieferant findLieferantByName(String name);
	
	Lieferant createLieferant(Lieferant lieferant);
	
	Hersteller findHerstellerById(Long id);
	
	Hersteller findHerstellerByName(String name);
	
	Hersteller createHersteller(Hersteller hersteller);
}
