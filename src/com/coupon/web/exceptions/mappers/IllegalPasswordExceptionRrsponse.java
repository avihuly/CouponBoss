package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.IllegalPasswordException;

@Provider
public class IllegalPasswordExceptionRrsponse implements ExceptionMapper<IllegalPasswordException> {

	@Override
	public Response toResponse(IllegalPasswordException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.ILLGEL_PASSWORD);
		return Response.status(Status.BAD_REQUEST)
				.entity(message)
				.build();
	}
}
