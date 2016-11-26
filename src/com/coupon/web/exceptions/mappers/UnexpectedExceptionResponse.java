package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnexpectedExceptionResponse implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		e.printStackTrace();
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.UNEXPECTED);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}
}
