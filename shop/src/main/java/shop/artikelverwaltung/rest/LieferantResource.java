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

import shop.util.interceptor.Log;
import shop.artikelverwaltung.domain.Lieferant;
import shop.artikelverwaltung.service.ArtikelService;
import shop.util.rest.UriHelper;

@Path("/lieferant")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75",
		TEXT_XML + ";qs=0.5" })
@Consumes
@Transactional
@RequestScoped
@Log
public class LieferantResource {
	@Context
	private UriInfo uriInfo;

	@Inject
	private UriHelper uriHelper;
	
	@Inject
	private ArtikelService as;

	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findLieferantById(@PathParam("id") Long id) {
		final Lieferant lieferant = as.findLieferantById(id);

		return Response		.ok(lieferant)
							.links(getTransitionalLinks(lieferant, uriInfo))
							.build();
	}

	private Link[] getTransitionalLinks(Lieferant lieferant, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriLieferant(lieferant, uriInfo)).rel(SELF_LINK)
				.build();
		return new Link[]{self};
	}
	
	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createLieferant(@Valid Lieferant lieferant) {
		lieferant = as.createLieferant(lieferant);
		return Response.created(getUriLieferant(lieferant, uriInfo)).build();
	}
	
	public URI getUriLieferant(Lieferant lieferant, UriInfo uriInfo) {
		return uriHelper.getURI(LieferantResource.class, "findLieferantById",
				lieferant.getId(), uriInfo);
	}
}
