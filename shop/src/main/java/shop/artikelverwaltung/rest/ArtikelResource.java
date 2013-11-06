package shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
	public Rad findRadById(@PathParam("id") Long id) {
		final Rad rad = Mock.findeRadById(id);
		if (rad == null) {
			throw new NotFoundException("Kein Fahrrad mit der ID " + id
					+ " gefunden.");
		}
		return rad;
	}

	public URI getUriRad(Rad rad, UriInfo uriInfo) {
		return uriHelper.getURI(ArtikelResource.class, "findeRadById",
				rad.getId(), uriInfo);
	}

	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createRad(Rad rad) {
		rad = Mock.createRad(rad);
		return Response.created(getUriRad(rad, uriInfo)).build();
	}

	@PUT
	@Produces({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Consumes
	public void updateRad(Rad rad) {
		Mock.updateRad(rad);
	}

	@DELETE
	@Path("{id:[1-9] [0-9]*}")
	@Produces
	public void deleteRad(@PathParam("id") Long id) {
		Mock.deleteRad(id);
	}
}
