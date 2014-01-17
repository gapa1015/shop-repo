package shop.artikelverwaltung.service;

import java.math.BigDecimal;
import java.util.List;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Hersteller;

public interface ArtikelService {
	List<AbstractArtikel> findVerfuegbareArtikel();
	
	AbstractArtikel findArtikelById(Long id);

	List<AbstractArtikel> findArtikelByIds(List<Long> ids);

	List<AbstractArtikel> findArtikelByName(String name);

	List<AbstractArtikel> findArtikelByPreis(BigDecimal preis);
	
	<T extends AbstractArtikel> T createArtikel(T artikel, Long herstellerId);
	
	<T extends AbstractArtikel> T createArtikel(T artikel, Hersteller hersteller);

	<T> T updateArtikel(T artikel);
	
	Hersteller findHerstellerById(Long id);
	
	Hersteller findHerstellerByName(String name);
	
	Hersteller createHersteller(Hersteller hersteller);
}
