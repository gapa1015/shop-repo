package shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static shop.util.Constants.SELF_LINK;

import java.net.URI;

import javax.inject.Inject;
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

import shop.artikelverwaltung.domain.Rad;
import shop.artikelverwaltung.service.ArtikelService;
import shop.util.Mock;
import shop.util.UriHelper;

@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75",
		TEXT_XML + ";qs=0.5" })
@Consumes
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
		final Rad rad = as.findRadById(id);

		return Response		.ok(rad)
							.links(getTransitionalLinks(rad, uriInfo))
							.build();
	}

	private Link[] getTransitionalLinks(Rad rad, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriRad(rad, uriInfo)).rel(SELF_LINK)
				.build();
		return new Link[]{self};
	}

	public URI getUriRad(Rad rad, UriInfo uriInfo) {
		return uriHelper.getURI(ArtikelResource.class, "findRadById",
				rad.getId(), uriInfo);
	}

	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createRad(Rad rad) {
		rad = as.createRad(rad);
		return Response.created(getUriRad(rad, uriInfo)).build();
	}

	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateRad(Rad rad) {
		as.updateRad(rad);
	}

	@DELETE
	@Path("{id:[1-9][0-9]*}")
	@Produces
	public void deleteRad(@PathParam("id") Long id) {
		Mock.deleteRad(id);
	}
}
