package shop.bestellverwaltung.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.bestellverwaltung.domain.Bestellung;
import shop.util.Mock;
import shop.util.interceptor.Log;

@Dependent
@Log
public class BestellungServiceImpl implements BestellungService, Serializable {
	private static final long serialVersionUID = 7643139186205745743L;
	
	@Inject
	private transient EntityManager em;
	
	@Inject
	@NeueBestellung
	private transient Event<Bestellung> event;
	
	@Override
	@NotNull(message = "{bestellverwaltung.bestellung.notFound.id}")
	public Bestellung findBestellungById(Long id) {
		return Mock.findeBestellungById(id);
	}
	
	@Override
	public Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde, Locale locale) {
		bestellung = Mock.createBestellung(bestellung);
		event.fire(bestellung);
		
		return bestellung;
	}
	
	@Override
	public Bestellung updateBestellung(Bestellung bestellung) {
		bestellung = Mock.updateBestellung(bestellung);
		event.fire(bestellung);
		
		return bestellung;
	}
	
	@Override
	@Size(min = 1, message = "{bestellverwaltung.bestellung.notFound.kunde}")
	public List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		return Mock.findBestellungenByKunde(kunde);
	}
}
