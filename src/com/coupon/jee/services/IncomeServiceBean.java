package com.coupon.jee.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.coupon.jee.entities.Income;

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
	
	@Override
	public Collection<Income> viewAllIncome() {
		String query = "SELECT i FROM income i";
		return entityManager.createQuery(query, Income.class).getResultList();
	}

	@Override
	public Collection<Income> ViewIncomeByCustomer(long customerId) {
		String query = "SELECT i FROM income i WHERE  i.id =" + customerId;
		return entityManager.createQuery(query, Income.class).getResultList();
	}

	@Override
	public Collection<Income> ViewIncomeByCompany(long companyId) {
		String query = "SELECT i FROM income i WHERE  i.id =" + companyId;
		return entityManager.createQuery(query, Income.class).getResultList();
	}
}
