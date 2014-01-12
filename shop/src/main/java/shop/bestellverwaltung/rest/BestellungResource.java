package shop.bestellverwaltung.rest;

import static shop.bestellverwaltung.service.BestellungService.FetchType.NUR_BESTELLUNG;
import static shop.util.Constants.ADD_LINK;
import static shop.util.Constants.SELF_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.rest.ArtikelResource;
import shop.artikelverwaltung.service.ArtikelService;
import shop.bestellverwaltung.domain.Bestellposition;
import shop.bestellverwaltung.domain.Bestellung;
import shop.bestellverwaltung.service.BestellungService;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.kundenverwaltung.rest.KundeResource;
import shop.util.interceptor.Log;
import shop.util.rest.UriHelper;

@Path("/bestellungen")
@Produces({APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
@RequestScoped
@Transactional
@Log
public class BestellungResource {
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private BestellungService bs;
	
	@Inject
	private ArtikelService as;
	
	@Inject
	private ArtikelResource artikelResource;

	@Inject
	private KundeResource kundeResource;
	
	@Inject
	private UriHelper uriHelper;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findBestellungById(@PathParam("id") Long id) {
		final Bestellung bestellung = bs.findBestellungById(id, NUR_BESTELLUNG);

		setStructuralLinks(bestellung, uriInfo);
		
		final Response response = Response.ok(bestellung)
                                          .links(getTransitionalLinks(bestellung, uriInfo))
                                          .build();
		return response;
	}
	
	public void setStructuralLinks(Bestellung bestellung, UriInfo uriInfo) {
		final AbstractKunde kunde = bestellung.getKunde();
		if (kunde != null) {
			final URI kundeUri = kundeResource.getUriKunde(bestellung.getKunde(), uriInfo);
			bestellung.setKundeUri(kundeUri);
		}
		
		final Collection<Bestellposition> bestellpositionen = bestellung.getBestellpositionen();
		if (bestellpositionen != null && !bestellpositionen.isEmpty()) {
			for (Bestellposition bp : bestellpositionen) {
				final URI artikelUri = artikelResource.getUriArtikel(bp.getArtikel(), uriInfo);
				bp.setArtikelUri(artikelUri);
			}
		}
	}
	
	public Link[] getTransitionalLinks(Bestellung bestellung, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriBestellung(bestellung, uriInfo))
                              .rel(SELF_LINK)
                              .build();
		final Link add = Link.fromUri(uriHelper.getUri(BestellungResource.class, uriInfo))
                             .rel(ADD_LINK)
                             .build();
		return new Link[] { self, add };
	}

	public URI getUriBestellung(Bestellung bestellung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findBestellungById", bestellung.getId(), uriInfo);
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}/lieferungen")
	public Response findLieferungenByBestellungId(@PathParam("id") Long id) {
		return Response.status(INTERNAL_SERVER_ERROR)
			       .entity("findLieferungenByBestellungId: NOT YET IMPLEMENTED")
			       .type(TEXT_PLAIN)
			       .build();
	}

	@GET
	@Path("{id:[1-9][0-9]*}/kunde")
	public Response findKundeByBestellungId(@PathParam("id") Long id) {
		final AbstractKunde kunde = bs.findKundeById(id);
		kundeResource.setStructuralLinks(kunde, uriInfo);
		
		final Response response = Response.ok(kunde)
                                          .links(kundeResource.getTransitionalLinks(kunde, uriInfo))
                                          .build();
		return response;
	}

	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createBestellung(@Valid Bestellung bestellung) {
		final String kundeUriStr = bestellung.getKundeUri().toString();
		int startPos = kundeUriStr.lastIndexOf('/') + 1;
		final String kundeIdStr = kundeUriStr.substring(startPos);
		Long kundeId = null;
		try {
			kundeId = Long.valueOf(kundeIdStr);
		}
		catch (NumberFormatException e) {
			kundeIdInvalid();
		}
		
		final Collection<Bestellposition> bestellpositionen = bestellung.getBestellpositionen();
		final List<Long> artikelIds = new ArrayList<>(bestellpositionen.size());
		for (Bestellposition bp : bestellpositionen) {
			final URI artikelUri = bp.getArtikelUri();
			if (artikelUri == null) {
				continue;
			}
			final String artikelUriStr = artikelUri.toString();
			startPos = artikelUriStr.lastIndexOf('/') + 1;
			final String artikelIdStr = artikelUriStr.substring(startPos);
			Long artikelId = null;
			try {
				artikelId = Long.valueOf(artikelIdStr);
			}
			catch (NumberFormatException e) {
				continue;
			}
			artikelIds.add(artikelId);
		}
		
		if (artikelIds.isEmpty()) {
			artikelIdsInvalid();
		}

		final Collection<AbstractArtikel> gefundeneArtikel = as.findArtikelByIds(artikelIds);
		
		int i = 0;
		final Set<Bestellposition> neueBestellpositionen = new HashSet<>();
		for (Bestellposition bp : bestellpositionen) {
			final long artikelId = artikelIds.get(i++);
			
			for (AbstractArtikel artikel : gefundeneArtikel) {
				if (artikel.getId().longValue() == artikelId) {
					bp.setArtikel(artikel);
					neueBestellpositionen.add(bp);
					break;					
				}
			}
		}
		bestellung.setBestellpositionen(neueBestellpositionen);
		
		bestellung = bs.createBestellung(bestellung, kundeId);

		final URI bestellungUri = getUriBestellung(bestellung, uriInfo);
		return Response.created(bestellungUri).build();
	}
	
	@NotNull(message = "{bestellung.artikelIds.invalid}")
	public List<Long> artikelIdsInvalid() {
		return null;
	}
	
	@NotNull(message = "{bestellung.kunde.id.invalid}")
	public Long kundeIdInvalid() {
		return null;
	}
}
