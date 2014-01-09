package shop.artikelverwaltung.service;

import java.math.BigDecimal;
import java.util.List;

import shop.artikelverwaltung.domain.AbstractArtikel;

public interface ArtikelService {
	AbstractArtikel findArtikelById(Long id);

	List<AbstractArtikel> findArtikelByIds(List<Long> ids);

	List<AbstractArtikel> findArtikelByName(String name);

	List<AbstractArtikel> findArtikelByPreis(BigDecimal preis);

	<T extends AbstractArtikel> T createArtikel(T artikel);

	<T> T updateArtikel(T artikel);
}
