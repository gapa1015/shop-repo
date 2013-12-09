package shop.util.rest;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
public class UriHelper {
	public URI getURI(Class<?> clazz, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
					  .path(clazz)
					  .build();
	}
	
	public URI getURI(Class<?> clazz, String methodName, Long id, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
					  .path(clazz)
					  .path(clazz, methodName)
					  .build(id);
	}
}
