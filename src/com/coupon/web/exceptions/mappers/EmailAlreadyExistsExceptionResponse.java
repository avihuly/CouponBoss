package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.EmailAlreadyExistsException;

@Provider
public class EmailAlreadyExistsExceptionResponse implements ExceptionMapper<EmailAlreadyExistsException> {

	@Override
	public Response toResponse(EmailAlreadyExistsException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.EMAIL_EXISTS);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
