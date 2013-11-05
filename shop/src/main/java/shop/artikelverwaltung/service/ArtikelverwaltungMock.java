package shop.artikelverwaltung.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import shop.artikelverwaltung.domain.Ersatzteil;
import shop.artikelverwaltung.domain.Rad;

public class ArtikelverwaltungMock {

	@POST
	public Rad createRad() {
		return null;
		// TODO Implement createRad
	}

	@PUT
	public Rad updateRad(Rad rad) {
		return rad;
		// TODO Implement updateRad
	}
	
	@DELETE
	public void deleteRadById(Long id) {
		// TODO Implement deleteRadById
	}

	// Ersatzteile
	@GET
	public List<Ersatzteil> findAllErsatzteile() {
		return null;
		// TODO Implement findAllErsatzteile
	}

	@GET
	public Ersatzteil findErsatzteilById(int id) {
		return null;
		// TODO Implement findErsatzteilById
	}
	@POST
	public Ersatzteil createErsatzteil() {
		return null;
		// TODO Implement createErsatzteil
	}

	@PUT
	public Ersatzteil updateErsatzteil(Ersatzteil ersatzteil) {
		return null;
		// TODO Implement updateErsatzteil
	}
	
	@DELETE
	public void deleteErsatzteilById(Long id) {
		// TODO Implement deleteErsatztteilById
	}
}
