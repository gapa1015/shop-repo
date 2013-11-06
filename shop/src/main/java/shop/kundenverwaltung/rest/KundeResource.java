package shop.kundenverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static shop.util.Constants.SELF_LINK;

import java.net.URI;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import shop.kundenverwaltung.domain.Kunde;
import shop.util.Mock;
import shop.util.UriHelper;


@Path("/kunden")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
public class KundeResource {
	
	@Context
	private UriInfo uriInfo;
	@Inject
	private UriHelper uriHelper;
	
	@GET
	@Path("{id:[1-9] [0-9]*}")
	public Kunde findKundeById(@PathParam("id") Long id) {
		final Kunde kunde = Mock.findeKundeById(id);
		if (kunde == null) {
			throw new NotFoundException("Keine Kunde mit der ID " + id + " gefunden.");
		}
		
		return kunde;
	}
	
	public void setStructuralLinks(Kunde kunde, UriInfo uriInfo) {
		
	}
	
	private Link[] getTransitionalLinks(Kunde kunde, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo))
							  .rel(SELF_LINK)
							  .build();
		return new Link[] { self };
	}
	
	public URI getUriKunde(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getURI(KundeResource.class, "findeKundeById", kunde.getId(), uriInfo);
	}


		
	
	
	@POST
	@Consumes( {APPLICATION_JSON, APPLICATION_XML,TEXT_XML})
	@Produces
	public Response createKunde(Kunde kunde) {
		kunde = Mock.createKunde(kunde);
		return Response.created(getUriKunde(kunde, uriInfo))
		.build();
	}
	
	@PUT
	@Produces({ APPLICATION_JSON, APPLICATION_XML,TEXT_XML})
	@Consumes
	public void updateKunde(Kunde kunde) {
		Mock.updateKunde(kunde);
	}
	
	
	@DELETE
	@Path("{id:[1-9] [0-9]*}")
	@Produces
	public void deleteKunde(@PathParam("id")Long id) {
		Mock.deleteKunde(id);
	}
	
	
	
	

}
