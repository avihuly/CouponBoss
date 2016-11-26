package com.coupon.web.exceptions.mappers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	@XmlElement private String errorMessage;
	@XmlElement private String errorCode;
	
	
	public ErrorMessage() {
	}
	
	public ErrorMessage(String errorMessage, String errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
	
	
}
