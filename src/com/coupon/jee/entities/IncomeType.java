package com.coupon.jee.entities;

public enum IncomeType {
	CUSTOMER_PURCHASE("Coupon purchase"), 
	COMPANY_NEW_COUPON("New coupon created"), 
	COMAPNY_UPDATE_COUPON("coupon updated");

	private String description;
	
	private IncomeType(String description) {
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
}
