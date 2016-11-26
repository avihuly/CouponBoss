package com.coupon.web.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.coupon.core.beans.Company;
import com.coupon.core.beans.Coupon;
import com.coupon.core.beans.Customer;
import com.coupon.core.exception.CouponSystemException;
import com.coupon.core.facade.AdminFacade;
import com.coupon.core.system.CouponSystem;

@Path("/admin")
public class AdminServlet {
	
	private static final String Facade_Attr = "FACADE";

	@Context private HttpServletRequest request;
		
	//////////////////// *************** /////////////////
	//////////////////// COMPANY METHODS /////////////////
	//////////////////// *************** /////////////////
		
		//Create company
		@POST
		@Path("/createCompany")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Company createCompany(Company company){	
			// getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			// create company - run time exception are being needled by mappers 
			adminFacade.createCompany(company);
			// return company after DB insert and ID update
			return company;
		}
		
		//removeCompany
		@DELETE
		@Path("/removeCompany")
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.APPLICATION_JSON)
		public Company removeCompany(long id){
			//getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			// Getting company instance by id
			Company company = adminFacade.getCompany(id);
			// Remove company from DB
			adminFacade.removeCompany(company);
			// After DB deletion return the company instance
			return company;
		}

		//updateCompany
		@POST
		@Path("/updateCompany")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Company updateCompany(Company company) {
			//getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			// Up dating company 
			adminFacade.updateCompany(company);
			// return updated company
			return adminFacade.getCompany(company.getId());
		}
		
		//getCompany
		@POST
		@Path("/getCompany")
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.APPLICATION_JSON)
		public Company getCompany(long id) {
			//getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			//the getCompany function
			return adminFacade.getCompany(id);
		}
		
		//getAllCompanies
		@GET
		@Path("/getAllCompanies")
		@Produces(MediaType.APPLICATION_JSON)
		public Company[] getAllCompanies() {
		//getting the adminFacade saved in the session
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
		// get companies from DB
		return adminFacade.getAllCompanies().toArray(new Company[]{});
		}
		
		// /getCompanyCoupons
		@POST
		@Path("/getCompanyCoupons")
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.APPLICATION_JSON)
		public Coupon[] getCompanyCoupons(long id) throws CouponSystemException { //Implement in ADMIN FACADE and deal with Exception
			return CouponSystem.getInstance().getCompanyDBDAO().getCoupons(id).toArray(new Coupon[]{});
		}
		
		//////////////////// **************** /////////////////
		//////////////////// CUSTOMER METHODS /////////////////
		//////////////////// **************** /////////////////
		
		//createCustomer
		@POST
		@Path("/createCustomer")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Customer createCustomer(Customer customer){
			//getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			//the createCustomer function
			adminFacade.createCustomer(customer);
			// return updated customer with id from db
			return adminFacade.getCustomer(customer.getId()); 
		}
		
		//removeCustomer
		@DELETE
		@Path("/removeCustomer")
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.APPLICATION_JSON)
		public Customer removeCustomer(long id){
			//getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			// get Customer by id
			Customer customer = adminFacade.getCustomer(id);
			//the createCustomer function
			adminFacade.removeCustomer(customer);
			// return deleted customer
			return customer;
		}
		
		//updateCustomer(Customer customer)
		@POST
		@Path("/updateCustomer")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)		
		public Customer updateCustomer(Customer customer){
			// Getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			// The updateCustomer function
			adminFacade.updateCustomer(customer);
			// Return updated customer
			return adminFacade.getCustomer(customer.getId());
		}
		
		// getCustomer(long id)
		@POST
		@Path("/getCustomer")
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.APPLICATION_JSON)
		public Customer getCustomer(long id){
			//getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			//the getCustomer function
			return adminFacade.getCustomer(id);
		}
		
		// Get All Customers
		@GET
		@Path("/getAllCustomers")
		@Produces(MediaType.APPLICATION_JSON)
		public Customer[] getAllCustomers() {
			//getting the adminFacade saved in the session
			AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute(Facade_Attr);
			//the getAllCustomers function
			return adminFacade.getAllCustomers().toArray(new Customer[]{});	
		}
		
		//getCustomerCoupons
		@POST
		@Path("/getCustomerCoupons")
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.APPLICATION_JSON)
		public Coupon[] getCustomerCoupons(long id) throws CouponSystemException { // Implement in ADMIN FACADE and deal with Exception													
			return CouponSystem.getInstance().getCustomerDBDAO().getCoupons(id).toArray(new Coupon[] {});
	}
}
