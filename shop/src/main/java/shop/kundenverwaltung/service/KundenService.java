package shop.kundenverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class KundenService implements Serializable {
	private static final long serialVersionUID = -4188395218729678116L;
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.id}")
	public AbstractKunde findKundeById(Long id) {
		if (id == null)
			return null;
		return Mock.findKundeById(id);
		
	}
	
	public List<AbstractKunde> findAllKunde() {
		return Mock.findAllKunden();
	}
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.email}")
	public AbstractKunde findKundebyEmail(String email) {
		if(email == null)
			return null;
		return Mock.findKundeByEmail(email);
		
	}
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.nachname}")
	public List<? extends AbstractKunde> findKundenByNachname(String nachname) {
		if(nachname == null)
			return null;
		return Mock.findKundenByNachname(nachname);
	}

	public <T extends AbstractKunde> T createKunde(T kunde) {
		if (kunde == null) {
			return kunde;
		}
		kunde = Mock.createKunde(kunde);

		return kunde;
	}
		
	public void updateKunde(AbstractKunde kunde) {
		Mock.updateKunde(kunde);
	}
	
	public void deleteKunde(@PathParam("id") Long id) {
		Mock.deleteKunde(id);
	}
}
