package com.coupon.web.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

import com.coupon.core.beans.Coupon;
import com.coupon.core.constants.CouponType;
import com.coupon.core.facade.CompanyFacade;


@Path("/company")
public class CompanyServlet {
	
	private static final String Facade_Attr = "FACADE";
	@Context private HttpServletRequest request;
		
	//createCoupon
	@POST
	@Path("/createCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon createCoupon(Coupon coupon) {	
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the createCoupon function
		compFacade.createCoupon(coupon);
		// return coupon with updated id from DB
		return coupon;
	}
	
	//removeCoupon
	@DELETE
	@Path("/removeCoupon")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon removeCoupon(long id){
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		// get coupon instance from DB
		Coupon coupon = compFacade.getCoupon(id);
		//the removeCoupon function
		compFacade.removeCoupon(coupon);
		// return the removed coupon
		return coupon;
	}
	
	//updateCoupon
	@POST
	@Path("/updateCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon updateCoupon(Coupon coupon){
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);		
		//the updateCoupon function
		compFacade.updateCoupon(coupon);
		// return updated coupon
		return compFacade.getCoupon(coupon.getId());
	}
	
	//getCoupon(long id)
	@POST
	@Path("/getCoupon")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon getCoupon(Long id) {
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCouopn function
		return compFacade.getCoupon(id);
	}
		
	//getAllCoupons()
	@GET
	@Path("/getAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllCoupons() {
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getAllCoupons function
		return compFacade.getAllCoupons();
	}
	
	//getCouponByType
	@POST
	@Path("/getCouponByType")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon[] getCouponByType(String type) {
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		CouponType coupoType = CouponType.valueOf(type);
		// get by type
		return compFacade.getCouponByType(coupoType).toArray(new Coupon[]{});
	}
	
	//getCouponByPrice(double price)
	@POST
	@Path("/getCouponByPrice")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon[] getCouponByPrice(double price) {
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		//the getCouponByPrice function
		return compFacade.getCouponByPrice(price).toArray(new Coupon[]{});
	}
	
	//getCouponByStartDate
	@POST
	@Path("/getCouponByStartDate")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon[] getCouponByStartDate(String startDate) {
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		// Parsing LocalDate 
		LocalDate startLocalDate = LocalDate.parse(startDate);
		//the getCouponByStartDate function
		return compFacade.getCouponByStartDate(startLocalDate).toArray(new Coupon[]{});
	}
	
	//getCouponByEndDate
	@POST
	@Path("/getCouponByEndDate")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon[] getCouponByEndDate(String endDate) {
		//getting the companyFacade saved in the session
		CompanyFacade compFacade = (CompanyFacade) request.getSession().getAttribute(Facade_Attr);
		// Parsing LocalDate
		LocalDate endLocalDate = LocalDate.parse(endDate);
		//the getCouponByStartDate function
		return compFacade.getCouponByEndDate(endLocalDate).toArray(new Coupon[]{});
	}
}
