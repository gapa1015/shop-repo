package shop.util.rest;

import static javax.validation.ElementKind.RETURN_VALUE;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static org.jboss.resteasy.api.validation.ConstraintType.Type.PARAMETER;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ViolationReport;

import java.util.List;

import shop.util.interceptor.Log;

@Provider
@ApplicationScoped
@Log
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {	@Override
	public Response toResponse(ConstraintViolationException exception) {
		final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		if (violations.size() == 1) {
			final ConstraintViolation<?> violation = violations.iterator().next();
			
			final Iterator<Path.Node> pathIterator = violation.getPropertyPath().iterator();
			Node node = null;
			while (pathIterator.hasNext()) {
				node = pathIterator.next();
			}
			
			if (node != null && node.getKind() == RETURN_VALUE) {
				final Object invalidValue = violation.getInvalidValue();
				if (invalidValue == null || (invalidValue instanceof List && ((List<?>) invalidValue).isEmpty())) {
					return Response.status(NOT_FOUND)
							       .type(TEXT_PLAIN)
						           .entity(violation.getMessage())
		                           .build();
				}
			}
		}
		
		return Response.status(BAD_REQUEST)
				       .entity(toViolationReport(violations))
                       .build();
	}
	
	private static ViolationReport toViolationReport(Set<ConstraintViolation<?>> violations) {
		final ArrayList<ResteasyConstraintViolation> parameterViolations = new ArrayList<>();
		for (ConstraintViolation<?> v : violations) {
			final String path = v.getPropertyPath().toString();
			final String message = v.getMessage();
			final Object invalidValue = v.getInvalidValue();
			final String inalidValueStr = invalidValue == null ? null : invalidValue.toString();
			final ResteasyConstraintViolation resteasyConstraintViolation =
					                          new ResteasyConstraintViolation(PARAMETER, path, message, inalidValueStr);
			parameterViolations.add(resteasyConstraintViolation);
		}
		
		final ViolationReport violationReport = new ViolationReport();
		violationReport.setParameterViolations(parameterViolations);
		return violationReport;
	}
}
