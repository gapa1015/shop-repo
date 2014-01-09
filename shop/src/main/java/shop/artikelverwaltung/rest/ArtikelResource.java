package shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static shop.util.Constants.SELF_LINK;

import java.net.URI;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.artikelverwaltung.domain.Rad;
import shop.artikelverwaltung.service.ArtikelService;
import shop.util.Mock;
import shop.util.rest.UriHelper;

@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75",
		TEXT_XML + ";qs=0.5" })
@Consumes
@RequestScoped
public class ArtikelResource {
	@Context
	private UriInfo uriInfo;

	@Inject
	private UriHelper uriHelper;
	
	@Inject
	private ArtikelService as;

	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findRadById(@PathParam("id") Long id) {
		final AbstractArtikel artikel = as.findArtikelById(id);

		return Response		.ok(artikel)
							.links(getTransitionalLinks(artikel, uriInfo))
							.build();
	}

	private Link[] getTransitionalLinks(AbstractArtikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo)).rel(SELF_LINK)
				.build();
		return new Link[]{self};
	}

	public URI getUriArtikel(AbstractArtikel artikel, UriInfo uriInfo) {
		return uriHelper.getURI(ArtikelResource.class, "findRadById",
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
		artikel = as.createArtikel(artikel);
		return Response.created(getUriArtikel(artikel, uriInfo)).build();
	}

	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateArtikel(@Valid Rad rad) {
		as.updateArtikel(rad);
	}

	@DELETE
	@Path("{id:[1-9][0-9]*}")
	@Produces
	public void deleteRad(@PathParam("id") Long id) {
		Mock.deleteRad(id);
	}
}
