package shop.kundenverwaltung.rest;

import static shop.util.Constants.FIRST_LINK;
import static shop.util.Constants.LAST_LINK;
import static shop.util.Constants.SELF_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.Email;

import shop.bestellverwaltung.domain.Bestellung;
import shop.bestellverwaltung.rest.BestellungResource;
import shop.bestellverwaltung.service.BestellungService;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.service.KundenService;
import shop.util.Mock;
import shop.util.rest.UriHelper;

@Path("/kunden")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75",
		TEXT_XML + ";qs=0.5" })
@Consumes
@RequestScoped
public class KundeResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private UriHelper uriHelper;
	
	@Inject
	private BestellungResource bestellungResource;
	
	@Inject
	private BestellungService bs;
	
	@Inject
	private KundenService ks;

	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findKundeById(@PathParam("id") Long id) {

		final AbstractKunde kunde = ks.findKundeById(id);

		if (kunde == null) {
			throw new NotFoundException("Kein Kunde mit der ID " + id
					+ " gefunden.");
		}
		setStructuralLinks(kunde, uriInfo);
		return Response.ok(kunde)
					   .links(getTransitionalLinks(kunde, uriInfo))
					   .build();
	}
		
	
	@GET
	public Response findKundeByEmail(@QueryParam("email") 
			@DefaultValue("")
			@Email(message = "{kunde.email.pattern}") 
	        String email)
	{

			final AbstractKunde kunde = ks.findKundebyEmail(email);

			if (kunde == null) {
				throw new NotFoundException("Kein Kunde mit der Email " + email
						+ " gefunden.");
			}

		setStructuralLinks(kunde, uriInfo);
		return Response.ok(kunde).links(getTransitionalLinks(kunde, uriInfo))
				.build();
	}
	
	

	public void setStructuralLinks(AbstractKunde kunde, UriInfo uriInfo) {
		}
		
	
	private Link[] getTransitionalLinks(AbstractKunde kunde, UriInfo uriInfo) {

		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo))
				.rel(SELF_LINK).build();
		return new Link[] {self};

	}
   
   
		
		@GET
		public Response findKundeByVorname(@QueryParam("vorname") String vorname) {
			final AbstractKunde kunde = Mock.findKundeByVorname(vorname);
			if (kunde == null) {
				throw new NotFoundException("Kein Kunde mit folgenden Vorname " + vorname + " gefunden.");
			}
			
			setStructuralLinks(kunde, uriInfo);
			return Response.ok(kunde)
						   .links(getTransitionalLinks(kunde, uriInfo))
						   .build();

	}

		
		@GET
		public Response findKundeByNachname(@QueryParam("nachname") String nachname) {
			final AbstractKunde kunde = Mock.findKundeByNachname(nachname);
			if (kunde == null) {
				throw new NotFoundException("Kein Kunde mit folgenden Name " + nachname + " gefunden.");
			}
			
			setStructuralLinks(kunde, uriInfo);
			return Response.ok(kunde)
						   .links(getTransitionalLinks(kunde, uriInfo))
						   .build();

	}



	@GET
	@Path("{id:[1-9][0-9]*}/bestellungen")
	public Response findBestellungenByKundeId(@PathParam("id") Long kundeId) {
		final AbstractKunde kunde = ks.findKundeById(kundeId);
		final List<Bestellung> bestellungen = bs.findBestellungenByKunde(kunde);
		if (bestellungen.isEmpty()) {
			throw new NotFoundException("Zur ID " + kundeId + " wurden keine Bestellungen gefunden");
		}
		
		for (Bestellung bestellung : bestellungen) {
			bestellungResource.setStructuralLinks(bestellung, uriInfo);
		}
		
		return Response.ok(new GenericEntity<List<Bestellung>>(bestellungen) { })
                       .links(getTransitionalLinksBestellungen(bestellungen, kunde, uriInfo))
                       .build();
	}
	
	private Link[] getTransitionalLinksBestellungen (
		List<Bestellung> bestellungen, AbstractKunde kunde, UriInfo uriInfo) {
			if (bestellungen == null || bestellungen.isEmpty()) {
				return new Link[0];
		}
		
		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo))
                              .rel(SELF_LINK)
                              .build();
		
		final Link first = Link.fromUri(bestellungResource.getUriBestellung(bestellungen.get(0), uriInfo))
	                           .rel(FIRST_LINK)
	                           .build();
		final int lastPos = bestellungen.size() - 1;
		
		final Link last = Link.fromUri(bestellungResource.getUriBestellung(bestellungen.get(lastPos), uriInfo))
                              .rel(LAST_LINK)
                              .build();
		
		return new Link[] {self, first, last};
	}

		
	
	public URI getUriKunde(AbstractKunde kunde, UriInfo uriInfo) {
		return uriHelper.getURI(KundeResource.class, "findKundeById", kunde.getId(), uriInfo);
	}	

	
	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createKunde(@Valid AbstractKunde kunde) {
		kunde = ks.createKunde(kunde);
		return Response.created(getUriKunde(kunde, uriInfo)).build();
	}

	@PUT
	@Produces({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Consumes
	public void updateKunde(@Valid AbstractKunde kunde) {
		ks.updateKunde(kunde);
	}

	@DELETE
	@Path("{id:[1-9] [0-9]*}")
	@Produces
	public void deleteKunde(@PathParam("id") Long id) {
		ks.deleteKunde(id);
	}

}
