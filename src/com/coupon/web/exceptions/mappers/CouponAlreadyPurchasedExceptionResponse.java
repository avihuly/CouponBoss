package com.coupon.web.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.coupon.core.exception.CouponAlreadyPurchasedException;

@Provider
public class CouponAlreadyPurchasedExceptionResponse implements ExceptionMapper<CouponAlreadyPurchasedException> {

	@Override
	public Response toResponse(CouponAlreadyPurchasedException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage(), ErrorCode.COUPON_PURCHASED);
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
