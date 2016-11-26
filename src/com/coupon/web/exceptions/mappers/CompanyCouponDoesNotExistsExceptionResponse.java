package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.CompanyCouponDoesNotExistsException;

@Provider
public class CompanyCouponDoesNotExistsExceptionResponse implements ExceptionMapper<CompanyCouponDoesNotExistsException> {
	@Override
	public Response toResponse(CompanyCouponDoesNotExistsException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.COMPANY_COUPON_DONT_EXISTS);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}
}
