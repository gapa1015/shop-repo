package shop.bestellverwaltung.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.bestellverwaltung.domain.Bestellung;
import shop.bestellverwaltung.service.NeueBestellung;
import shop.util.Mock;

@Dependent
public class BestellungServiceImpl implements BestellungService, Serializable {
	private static final long serialVersionUID = 1446381948660654505L;
	
	@Inject
	@NeueBestellung
	private transient Event<Bestellung> event;
	
	@Override
	@NotNull(message = "{bestellung.notFound.id}")
	public Bestellung findBestellungById(Long id) {
		return Mock.findeBestellungById(id);
	}
	
	@Override
	@Size(min = 1, message = "{bestellung.notFound.kunde}")
	public List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		return Mock.findBestellungenByKunde(kunde);
	}
	
	@Override
	public Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde, Locale locale) {
		bestellung = Mock.createBestellung(bestellung);
		event.fire(bestellung);
		
		return bestellung;
	}
}
