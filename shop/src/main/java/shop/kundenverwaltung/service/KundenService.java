package shop.kundenverwaltung.service;

import java.io.Serializable;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.util.Mock;

public class KundenService implements Serializable {
	
	
	public AbstractKunde findKundeById(Long id) {
		if (id == null)
			return null;
		return Mock.findKundeById(id);
		
	}

}
