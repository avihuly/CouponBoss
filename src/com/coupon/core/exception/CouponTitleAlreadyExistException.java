package com.coupon.core.exception;

public class CouponTitleAlreadyExistException extends RuntimeException {

	public CouponTitleAlreadyExistException() {

	}

	public CouponTitleAlreadyExistException(String message) {
		super(message);

	}

	public CouponTitleAlreadyExistException(Throwable cause) {
		super(cause);

	}

	public CouponTitleAlreadyExistException(String message, Throwable cause) {
		super(message, cause);

	}

	public CouponTitleAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
