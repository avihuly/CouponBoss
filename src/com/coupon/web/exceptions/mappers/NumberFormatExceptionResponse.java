package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NumberFormatExceptionResponse implements ExceptionMapper<NumberFormatException> {
	@Override
	public Response toResponse(NumberFormatException e) {
		ErrorMessage message = new ErrorMessage("Value must be a number", ErrorCode.ID_MUST_B_NUM);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}
}
