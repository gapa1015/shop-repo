package shop.artikelverwaltung.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import shop.artikelverwaltung.domain.Ersatzteil;
import shop.artikelverwaltung.domain.Rad;

public class Artikelverwaltung {
	// Rad

	@GET
	@Path("/rad")
	public List<Rad> findAllRaeder() {
		return null;
		// TODO Implement findAllRaeder
	}

	@GET
	@Path("/rad/{id:[1-9][0-9]*}")
	public Rad findRadById() {
		return null;
		// TODO Implement findRadById
	}

	@GET
	public Rad findRadByName() {
		return null;
		// TODO Implement findRadByName
	}

	@GET
	public List<Rad> findRadByZoll() {
		return null;
		// TODO Implement findRadByZoll
	}

	@GET
	public List<Rad> findRadZwischenPreisUndPreis(int preis1, int preis2) {
		return null;
		// TODO Implement findRadZwischenPreisUndPreis
	}

	@GET
	public List<Rad> findRaederByHersteller() {
		return null;
		// TODO Implement findRaederByHersteller
	}

	@GET
	public List<Rad> findRaederByHerstellerId(Long id) {
		return null;
		// TODO Implement findRaederByHerstellerById
	}

	@GET
	public List<Rad> findRaederByLieferant() {
		return null;
		// TODO Implement findRaederByLieferant
	}

	@GET
	public List<Rad> findRaederByLieferantId(Long id) {
		return null;
		// TODO Implement findRaederByLieferantId
	}

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
	public void deleteRad(Rad rad) {
		// TODO Implement deleteRad
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

	@GET
	public Ersatzteil findErsatzteilByName(String name) {
		return null;
		// TODO Implement findErsatzteilByName
	}

	@GET
	public List<Rad> findErsatzteilkompartibelMitRaeder(Ersatzteil ersatzteil) {
		return null;
		// TODO Implement findErsatzteilkompartibelMitRaeder
	}

	@GET
	public List<Ersatzteil> findErsatzteilkompartibelMitRad(Rad rad) {
		return null;
		// TODO Implement findErsatzteilkompartibelMitRad
	}

	@GET
	public List<Ersatzteil> findErsatzteilZwischenPreisUndPreis(int preis1,
			int preis2) {
		return null;
		// TODO Implement findErsatzteilZwischenPreisUndPreis
	}

	@GET
	public List<Ersatzteil> findErsatzteilByHersteller() {
		return null;
		// TODO Implement findErsatzteilByHersteller
	}

	@GET
	public List<Ersatzteil> findErsatzteilByHerstellerId(Long id) {
		return null;
		// TODO Implement findErsatzteilByHerstellerById
	}

	@GET
	public List<Ersatzteil> findErsatzteilByLieferant() {
		return null;
		// TODO Implement findErsatzteilByLieferant
	}

	@GET
	public List<Ersatzteil> findErsatzteilyLieferantId(Long id) {
		return null;
		// TODO Implement findErsatzteilByLieferantId
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
	public void deleteErsatzteil(Ersatzteil ersatzteil) {
		// TODO Implement deleteErsatzteil
	}

	@DELETE
	public void deleteErsatzteilById(Long id) {
		// TODO Implement deleteErsatztteilById
	}
}
