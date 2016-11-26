package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.OutOfDateException;

@Provider
public class OutOfStockExceptionResponse implements ExceptionMapper<OutOfDateException> {

	@Override
	public Response toResponse(OutOfDateException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.OUT_OF_DATE);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
