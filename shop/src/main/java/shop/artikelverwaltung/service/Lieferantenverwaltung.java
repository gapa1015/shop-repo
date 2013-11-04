package shop.artikelverwaltung.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import shop.artikelverwaltung.domain.Lieferant;

public class Lieferantenverwaltung {

	// Lieferant
	@GET
	public List<Lieferant> findAllLieferanten() {
		return null;
		// TODO Implement findAllLieferanten
	}

	@GET
	public Lieferant findLieferantById() {
		return null;
		// TODO Implement findAllLieferantenById
	}

	@GET
	public Lieferant findLieferantByName() {
		return null;
		// TODO Implement findAllLieferantenByName
	}

	@POST
	public Lieferant createLieferant() {
		return null;
		// TODO Implement createLieferant
	}

	@PUT
	public Lieferant updateLieferant() {
		return null;
		// TODO Implement updateLieferant
	}

	@DELETE
	public void deleteLieferant() {
		// TODO Implement deleteLieferant
	}

	@DELETE
	public void deleteLieferantById() {
		// TODO Implement deleteLieferantById
	}
}
