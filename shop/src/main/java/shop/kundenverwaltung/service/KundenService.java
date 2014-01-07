package shop.kundenverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import org.jboss.logging.Logger;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class KundenService implements Serializable {
	private static final long serialVersionUID = -4188395218729678116L;
	private static final Logger LOGGER = Logger.getLogger
			(MethodHandles.lookup().lookupClass());
	
	public enum FetchType {
        NUR_KUNDE,
        MIT_BESTELLUNGEN,
	}

	public enum OrderType {
        KEINE,
        ID
	}
	
	@Inject EntityManager em;
	
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.id}")
	public AbstractKunde findKundeById(Long id) {
		LOGGER.debugf("Beginn findKundeById %s", id);
		if (id == null)
			return null;
		//LOGGER.debugf("Ende findKundeById %d", Mock);
		AbstractKunde kunde = em.find(AbstractKunde.class, id);
		return kunde;
		
	}
	
	public List<AbstractKunde> findAllKunden() {
		return Mock.findAllKunden();
	}
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.email}")
	public AbstractKunde findKundeByEmail(String email) {
		if (email == null)
			return null;
		return Mock.findKundeByEmail(email);
		
	}
	
	@NotNull(message = "{kundenverwaltung.kunde.notFound.nachname}")
	public List<? extends AbstractKunde> findKundenByNachname(String nachname) {
		if (nachname == null)
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
