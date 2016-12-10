package com.coupon.jee.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.coupon.jee.entities.Income;
import com.coupon.jee.entities.IncomeType;

@Path("/income")
@Stateless(name = "IncomeServiceEJB")
public class IncomeServiceBean implements IncomeService {

	private EntityManager entityManager;
	
	// Constructor
	public IncomeServiceBean() {
		entityManager = Persistence.createEntityManagerFactory("couponSystem").createEntityManager();
	}
	
	@Override
	@Path("/store")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void storeIncome(Income income) {
		entityManager.getTransaction().begin();
		entityManager.persist(income);
		entityManager.getTransaction().commit();
	}
	
	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------
	
	@Path("/getIncome")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void getallIncome(long customerId){
		String query = "SELECT i FROM Income i WHERE i.accountId =" + customerId
				+ " AND i.description ='" + IncomeType.CUSTOMER_PURCHASE.toString() +"'";
		List<Income> incomeList = entityManager.createQuery(query, Income.class).getResultList();
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		for (Income income:incomeList) {
			System.out.println(income);
		}
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
	}
	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------
	
	@Override
	public Collection<Income> viewAllIncome() {
		String query = "SELECT i FROM Income i";
		return entityManager.createQuery(query, Income.class).getResultList();
	}

	@Override
	public Collection<Income> ViewIncomeByCustomer(long customerId) {
		String query = "SELECT i FROM Income i WHERE i.accountId =" + customerId
				+ " AND i.description ='" + IncomeType.CUSTOMER_PURCHASE.toString() +"'";
		return entityManager.createQuery(query, Income.class).getResultList();
	}

	@Override
	public Collection<Income> ViewIncomeByCompany(long companyId) {
		String query = "SELECT i FROM Income i WHERE i.accountId =" + companyId
				+ " AND i.description !='" + IncomeType.CUSTOMER_PURCHASE.toString() +"'";
		return entityManager.createQuery(query, Income.class).getResultList();
	}
}
