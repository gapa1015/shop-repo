package shop.kundenverwaltung.rest;

import static shop.util.Constants.SELF_LINK;
import static shop.util.Constants.ADD_LINK;
import static shop.util.Constants.LIST_LINK;
import static shop.util.Constants.REMOVE_LINK;
import static shop.util.Constants.UPDATE_LINK;
import static shop.util.Constants.FIRST_LINK;
import static shop.util.Constants.LAST_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
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

import shop.bestellverwaltung.domain.Bestellung;
import shop.bestellverwaltung.rest.BestellungResource;
import shop.bestellverwaltung.service.BestellungService;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.service.KundenService;
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
	private KundenService ks;
	
	@Inject 
	private BestellungResource bestellungResource;
	
	@Inject
	private BestellungService bs;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findKundeById(@PathParam("id") Long id) {
		final AbstractKunde kunde = ks.findKundeById(id);

		setStructuralLinks(kunde, uriInfo);
		return Response.ok(kunde)
					   .links(getTransitionalLinks(kunde, uriInfo))
					   .build();
	}
		
	@GET
	public Response findKundeByEmail(@QueryParam("email") String email) {
			final AbstractKunde kunde = ks.findKundebyEmail(email);

			if (kunde == null) {
				throw new NotFoundException("Kein Kunde mit der Email " + email
						+ " gefunden.");
			}

		setStructuralLinks(kunde, uriInfo);
		return Response.ok(kunde).links(getTransitionalLinks(kunde, uriInfo))
				.build();
	}
	
	@GET
	public Response findAllKunde() {
		final List<AbstractKunde> kunden = ks.findAllKunde();
		
		if (kunden != null) {
			for (AbstractKunde kunde : kunden) {
				setStructuralLinks(kunde, uriInfo);
			}
		}
		
		return Response.ok(new GenericEntity<List<AbstractKunde>>(kunden){})
	                   .links(getTransitionalLinksKunden(kunden, uriInfo))
	                   .build();
	}

	@GET
	@Path("{id:[1-9][0-9]*}/bestellungen")
	public Response findBestellungenByKundeId(@PathParam("id") Long kundeId) {
		final AbstractKunde kunde = ks.findKundeById(kundeId);
		final List<Bestellung> bestellungen = bs.findBestellungenByKunde(kunde);
		
		if (bestellungen != null) {
			for (Bestellung bestellung : bestellungen) {
				bestellungResource.setStructuralLinks(bestellung, uriInfo);
			}
		}
		
		return Response.ok(new GenericEntity<List<Bestellung>>(bestellungen){})
                       .links(getTransitionalLinksBestellungen(bestellungen, kunde, uriInfo))
                       .build();
	}
	
	public void setStructuralLinks(AbstractKunde kunde, UriInfo uriInfo) {
		}
	
	public URI getUriKunde(AbstractKunde kunde, UriInfo uriInfo) {
		return uriHelper.getURI(KundeResource.class, "findKundeById", kunde.getId(), uriInfo);
	}
	
	private URI getUriBestellungen(AbstractKunde kunde, UriInfo uriInfo) {
		return uriHelper.getURI(KundeResource.class, "findBestellungenByKundeId", kunde.getId(), uriInfo);
	}
	
	private Link[] getTransitionalLinks(AbstractKunde kunde, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo))
	                          .rel(SELF_LINK)
	                          .build();

		final Link list = Link.fromUri(uriHelper.getURI(KundeResource.class, uriInfo))
		                      .rel(LIST_LINK)
		                      .build();

		final Link add = Link.fromUri(uriHelper.getURI(KundeResource.class, uriInfo))
		                     .rel(ADD_LINK)
		                     .build();
		
		final Link update = Link.fromUri(uriHelper.getURI(KundeResource.class, uriInfo))
			                    .rel(UPDATE_LINK)
			                    .build();
		
		final Link remove = Link.fromUri(uriHelper.getURI(KundeResource.class, "deleteKunde", kunde.getId(), uriInfo))
		                        .rel(REMOVE_LINK)
		                        .build();
		
		return new Link[] { self, list, add, update, remove };
	}
	
	private Link[] getTransitionalLinksKunden(List<? extends AbstractKunde> kunden, UriInfo uriInfo) {
		if (kunden == null || kunden.isEmpty()) {
			return null;
		}
		
		final Link first = Link.fromUri(getUriKunde(kunden.get(0), uriInfo))
	                           .rel(FIRST_LINK)
	                           .build();
		final int lastPos = kunden.size() - 1;
		final Link last = Link.fromUri(getUriKunde(kunden.get(lastPos), uriInfo))
                              .rel(LAST_LINK)
                              .build();
		
		return new Link[] { first, last };
	}
	
	private Link[] getTransitionalLinksBestellungen(List<Bestellung> bestellungen, AbstractKunde kunde, UriInfo uriInfo) {
		if (bestellungen == null || bestellungen.isEmpty()) {
			return new Link[0];
		}
		
		final Link self = Link.fromUri(getUriBestellungen(kunde, uriInfo))
                .rel(SELF_LINK)
                .build();
		
		final Link first = Link.fromUri(bestellungResource.getUriBestellung(bestellungen.get(0), uriInfo))
						       .rel(FIRST_LINK)
						       .build();
		
		final int lastPos = bestellungen.size() - 1;
		
		final Link last = Link.fromUri(bestellungResource.getUriBestellung(bestellungen.get(lastPos), uriInfo))
						      .rel(LAST_LINK)
						      .build();

		return new Link[] { self, first, last };
	}
		
	@GET
	public Response findKundenByNachname(@QueryParam("nachname") String nachname) {
		List<? extends AbstractKunde> kunden = null;
		if (nachname != null) {
			kunden = ks.findKundenByNachname(nachname);
		}
		else {
			kunden = ks.findAllKunde();
		}
		
		for (AbstractKunde k : kunden) {
			setStructuralLinks(k, uriInfo);
		}
		
		return Response.ok(new GenericEntity<List<? extends AbstractKunde>>(kunden){})
                       .links(getTransitionalLinksKunden(kunden, uriInfo))
                       .build();
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
	public void updateKunde(AbstractKunde kunde) {
		ks.updateKunde(kunde);
	}

	@DELETE
	@Path("{id:[1-9] [0-9]*}")
	@Produces
	public void deleteKunde(@PathParam("id") Long id) {
		ks.deleteKunde(id);
	}
}
