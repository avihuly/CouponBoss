package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.AdminFacadeException;

@Provider
public class AdminFacadeExceptionResponse implements ExceptionMapper<AdminFacadeException> {

	@Override
	public Response toResponse(AdminFacadeException e) {
		e.printStackTrace();
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.ADMIN_UNEXPECTED);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}
}
