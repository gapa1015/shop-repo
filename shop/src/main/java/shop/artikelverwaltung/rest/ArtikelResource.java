package shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static shop.util.Constants.SELF_LINK;

import java.net.URI;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import shop.util.interceptor.Log;
import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Hersteller;
import shop.artikelverwaltung.service.ArtikelService;
import shop.util.rest.UriHelper;

@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
@Transactional
@RequestScoped
@Log
public class ArtikelResource {
	@Context
	private UriInfo uriInfo;

	@Inject
	private UriHelper uriHelper;
	
	@Inject
	private HerstellerResource herstellerResource;
	
	@Inject
	private ArtikelService as;

	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findRadById(@PathParam("id") Long id) {
		final AbstractArtikel artikel = as.findArtikelById(id);

		setStructuralLinks(artikel, uriInfo);
		
		return Response		.ok(artikel)
							.links(getTransitionalLinks(artikel, uriInfo))
							.build();
	}

	public void setStructuralLinks(AbstractArtikel artikel, UriInfo uriInfo) {
		final Hersteller hersteller = artikel.getHersteller();
		if (hersteller != null) {
			final URI herstellerUri = herstellerResource.getUriHersteller(artikel.getHersteller(), uriInfo);
			artikel.setHerstellerUri(herstellerUri);
		}
	}
	
	private Link[] getTransitionalLinks(AbstractArtikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo)).rel(SELF_LINK)
				.build();
		return new Link[]{self};
	}

	public URI getUriArtikel(AbstractArtikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findRadById",
				artikel.getId(), uriInfo);
	}
	
	@GET
	@Path("{name}")
	public Response findRadByKunde(@PathParam("name") Long id) {
		final AbstractArtikel artikel = as.findArtikelById(id);

		return Response		.ok(artikel)
							.links(getTransitionalLinks(artikel, uriInfo))
							.build();
	}

	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createRad(@Valid AbstractArtikel artikel) {
		final String herstellerUriStr = artikel.getHerstellerUri().toString();
		final int startPos = herstellerUriStr.lastIndexOf('/') + 1;
		final String herstellerIdStr = herstellerUriStr.substring(startPos);
		Long herstellerId = null;
		try {
			herstellerId = Long.valueOf(herstellerIdStr);
		}
		catch (NumberFormatException e) {
			herstellerIdInvalid();
		}
		
		artikel = as.createArtikel(artikel, herstellerId);
		
		final URI artikelUri = getUriArtikel(artikel, uriInfo);
		return Response.created(artikelUri).build();
	}

	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateArtikel(@Valid AbstractArtikel artikel) {
	    final AbstractArtikel origArtikel = as.findArtikelById(artikel.getId());
		
		origArtikel.setValues(artikel);
		
		as.updateArtikel(origArtikel);
	}
	
	@NotNull(message = "{artikel.hersteller.id.invalid}")
	public Long herstellerIdInvalid() {
		return null;
	}
}
