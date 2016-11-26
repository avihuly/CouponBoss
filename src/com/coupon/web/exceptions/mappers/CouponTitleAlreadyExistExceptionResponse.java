package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.CouponTitleAlreadyExistException;

@Provider
public class CouponTitleAlreadyExistExceptionResponse implements ExceptionMapper<CouponTitleAlreadyExistException> {

	@Override
	public Response toResponse(CouponTitleAlreadyExistException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.COUPON_TITLE_EXISTS);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
