package shop.artikelverwaltung.service;

import javax.enterprise.context.Dependent;

import shop.artikelverwaltung.domain.Hersteller;
import shop.artikelverwaltung.domain.Lieferant;
import shop.artikelverwaltung.domain.Rad;
import shop.kundenverwaltung.domain.Adresse;
import shop.util.cdi.MockService;
import shop.util.interceptor.Log;

@MockService
@Dependent
@Log
public class ArtikelServiceMock extends ArtikelServiceImp {
	private static final long serialVersionUID = -2362261956191316538L;
	
	private static final int MAX_ID = 99;
	
	@Override
	public Rad findRadById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final int preis = 150;
		final int baujahr = 2012;
		final int zoll = 28;
		
		final Lieferant lieferant = new Lieferant();
		lieferant.setId(id + 1);
		lieferant.setName("Franz");
		
		final Adresse adressa = new Adresse(); 
		adressa.setStrasse("Amalienstraße"); 
		adressa.setHausnummer("70"); 
		adressa.setStadt("Karlsruhe"); 
		adressa.setPlz("76133"); 
		lieferant.setAdresse(adressa);

		final Hersteller hersteller = new Hersteller();
		hersteller.setId(id + 2);
		hersteller.setName("Velo GmbH");
		
		final Adresse adressz = new Adresse(); 
		adressz.setStrasse("Amalienstraße"); 
		adressz.setHausnummer("70"); 
		adressz.setStadt("Karlsruhe"); 
		adressz.setPlz("76133"); 
		hersteller.setAdresse(adressz);
		
		final Rad rad = new Rad();
		rad.setId(id);
		rad.setName("City-Bike");
		rad.setPreis(preis);
		rad.setBaujahr(baujahr);
		rad.setZoll(zoll);
		rad.setHersteller(hersteller);
		rad.setLieferant(lieferant);

		return rad;
	}
}
