package shop.util.rest;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import shop.util.interceptor.Log;
import shop.util.rest.Messages;

@Provider
@Log
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
	@Context
	private HttpHeaders headers;
	
	@Inject
	private Messages messages;
	
	@Override
	public Response toResponse(NotFoundException e) {
		final String msg = messages.getMessage(headers, e.getMessage(), e.getArgs());
		return Response.status(NOT_FOUND)
		               .type(TEXT_PLAIN)
		               .entity(msg)
		               .build();
	}
}
