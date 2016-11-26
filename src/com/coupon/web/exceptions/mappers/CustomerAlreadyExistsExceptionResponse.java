package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.CustomerAlreadyExistsException;

@Provider
public class CustomerAlreadyExistsExceptionResponse implements ExceptionMapper<CustomerAlreadyExistsException> {

	@Override
	public Response toResponse(CustomerAlreadyExistsException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.CUSTOMER_NAME_EXISTS);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
