package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.CouponDoesNotExistException;

@Provider
public class CouponDoesNotExistExceptionResponse implements ExceptionMapper<CouponDoesNotExistException> {
	@Override
	public Response toResponse(CouponDoesNotExistException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.COUPON_DONT_EXISTS);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}
}
