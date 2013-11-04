package shop.artikelverwaltung.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import shop.artikelverwaltung.domain.Hersteller;

public class Herstellerverwaltung {
	// Hersteller
	@GET
	public List<Hersteller> findAllHersteller() {
		return null;
		// TODO Implement findAllHersteller
	}

	@GET
	public Hersteller findHerstellerById() {
		return null;
		// TODO Implement findAllHerstellerById
	}

	@GET
	public Hersteller findHerstellerByName() {
		return null;
		// TODO Implement findAllHerstellerByName
	}

	@POST
	public Hersteller createHersteller() {
		return null;
		// TODO Implement createHersteller
	}

	@PUT
	public Hersteller updateHersteller() {
		return null;
		// TODO Implement updateHersteller
	}

	@DELETE
	public void deleteHersteller() {
		// TODO Implement deleteHersteller
	}

	@DELETE
	public void deleteHerstellerById() {
		// TODO Implement deleteHerstellerById
	}

}
