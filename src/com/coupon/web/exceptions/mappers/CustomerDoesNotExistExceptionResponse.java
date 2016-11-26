package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.CustomerDoesNotExistException;

@Provider
public class CustomerDoesNotExistExceptionResponse implements ExceptionMapper<CustomerDoesNotExistException> {
	@Override
	public Response toResponse(CustomerDoesNotExistException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.CUSTOMER_DONT_EXISTS);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}
}
