package com.coupon.jee.services;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import com.coupon.jee.entities.Income;
import com.coupon.jee.entities.IncomeType;

@Stateless(name = "IncomeServiceEJB")
public class IncomeServiceBean implements IncomeService {

	private EntityManager entityManager;
	
	// Constructor
	public IncomeServiceBean() {
		entityManager = Persistence.createEntityManagerFactory("couponSystem").createEntityManager();
	}
	
	public void storeIncome(Income income) {
		entityManager.getTransaction().begin();
		entityManager.persist(income);
		entityManager.getTransaction().commit();
	}
	
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
