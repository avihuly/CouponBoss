package com.coupon.web.services;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.coupon.core.beans.Coupon;
import com.coupon.core.constants.CouponType;
import com.coupon.core.facade.CustomerFacade;
import com.coupon.jee.delegates.BusinessDelegate;
import com.coupon.jee.entities.Income;
import com.coupon.jee.entities.IncomeType;

@Path("/customer")
public class CustomerServlet {

	private static final String Facade_Attr = "FACADE";
	
	@Context 
	private HttpServletRequest request;
	
	// purchaseCoupon(long id)
	@POST
	@Path("/purchaseCoupon")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon purchaseCoupon(long id) {
		// getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		// The purchaseCoupon function
		Coupon purchasedCoupon = custFacade.purchaseCoupon(id);
		// creating Income object to store 
		Income income = new Income(
				custFacade.getCustomer().getCustName(),
				LocalDate.now(),
				IncomeType.CUSTOMER_PURCHASE,
				purchasedCoupon.getPrice()
				);
		// Sending Income to be asynchronously stored in the DB by the 
		BusinessDelegate.getInctance().storeIncome(income);
		// return purchased coupon
		return purchasedCoupon;
	}
	
	// getAllPurchasedCoupons()
	@GET
	@Path("/purchasedCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon[] getAllPurchasedCoupons() {
		// getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		// The getAllPurchasedCoupons function
		return custFacade.getAllPurchasedCoupons().toArray(new Coupon[]{});
	}

	//Collection<Coupon> getAllPurchasedCouponsByType(CouponType type)
	@POST
	@Path("/purchasedByType")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon[] getAllPurchasedCouponsByType(String type) {
		// getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		// converting type to enum
		CouponType couponType = CouponType.valueOf(type);		
		// return result
		return custFacade.getAllPurchasedCouponsByType(couponType).toArray(new Coupon[]{});
	}
	
	//Collection<Coupon> getAllPurchasedCouponsByPrice(Double price)
	@POST
	@Path("/purchasedByPrice")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon[] getAllPurchasedCouponsByPrice(double price) {
		//getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		// return result
		return custFacade.getAllPurchasedCouponsByPrice(price).toArray(new Coupon[]{});
	}	
	
	//Collection<Coupon> getAllCoupons()
	//this method gets all the coupons in the coupon system
	@GET
	@Path("/browseCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon[] getAllcoupons() {
		//getting the customerFacade saved in the session
		CustomerFacade custFacade = (CustomerFacade) request.getSession().getAttribute(Facade_Attr);
		// return result
		return custFacade.getAllCoupons().toArray(new Coupon[]{});
	}
}
