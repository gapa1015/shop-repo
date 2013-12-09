package shop.kundenverwaltung.rest;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import shop.kundenverwaltung.service.EmailExistsException;
import shop.util.Messages;

public class EmailExistsExceptionMapper implements ExceptionMapper<EmailExistsException> {
	
	@Context
	private HttpHeaders headers;
	
	@Inject 
	private Messages messages;
	
	@Override
	public Response toResponse(EmailExistsException ex) {
		final String message = messages.getMessage(headers, ex.getMessageKey(), ex.getEmail());
		return Response.status(BAD_REQUEST)
						.type(TEXT_PLAIN)
						.entity(message)
						.build();
		
	}
	

}
