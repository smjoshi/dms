package com.dc.dms.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.internal.Errors.ErrorMessage;

public class AppExceptionMapper implements ExceptionMapper<ApplicationRestException> {

	@Override
	public Response toResponse(ApplicationRestException restException) {
		return Response.status(restException.getStatus()).entity(new com.dc.dms.rest.exception.ErrorMessage(restException))
				.type(MediaType.APPLICATION_JSON).build();
	}

}
