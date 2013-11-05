package shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static shop.util.Constants.SELF_LINK;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import shop.artikelverwaltung.domain.Rad;
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

	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findeRadById(@PathParam("id") Long id) {
		final Rad rad = Mock.findRadById(id);
		if (rad == null) {
			throw new NotFoundException("Kein Rad mit der ID " + id
					+ " gefunden.");
		}

		setStructuralLinks(rad, uriInfo);

		final Response response = Response.ok(rad)
				.links(getTransitionalLinks(rad, uriInfo)).build();

		return response;
	}

	public void setStructuralLinks(Rad rad, UriInfo uriInfo) {

	}

	private Link[] getTransitionalLinks(Rad rad, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriRad(rad, uriInfo)).rel(SELF_LINK)
				.build();
		return new Link[] { self };
	}

	public URI getUriRad(Rad rad, UriInfo uriInfo) {
		return uriHelper.getURI(ArtikelResource.class, "findeBestellungById",
				rad.getId(), uriInfo);
	}

}
