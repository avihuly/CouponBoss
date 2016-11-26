package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.CompanyFacadeException;

@Provider
public class CompanyFacadeExceptionResponse implements ExceptionMapper<CompanyFacadeException> {

	@Override
	public Response toResponse(CompanyFacadeException e) {
		e.printStackTrace();
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.COMPANY_UNEXPECTED);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}
}
