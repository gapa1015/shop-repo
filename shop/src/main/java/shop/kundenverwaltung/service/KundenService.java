package shop.kundenverwaltung.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class KundenService implements Serializable {
	private static final long serialVersionUID = -4188395218729678116L;

	public AbstractKunde findKundeById(Long id) {
		if (id == null)
			return null;
		return Mock.findKundeById(id);
		
	}

}
