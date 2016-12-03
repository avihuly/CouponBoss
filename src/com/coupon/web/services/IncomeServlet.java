package com.coupon.web.services;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coupon.jee.entities.Income;
import com.coupon.jee.services.IncomeService;

@Path("/income")
public class IncomeServlet {
	
	@EJB(name = "IncomeServiceEJB")
	private IncomeService iencomeService;
	
	@POST
	@Path("/store")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Income storeIncome (Income income) {
		
		System.out.println("*************************");
		System.out.println("IN Income Store service");
		System.out.println(income);
		System.out.println("*************************");
		
		
		iencomeService.storeIncome(income);
		
		
		return income;
		
		
		
	}
}
