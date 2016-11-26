package com.coupon.jee.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.coupon.jee.entities.Income;

@Stateless(name = "IncomeServiceEJB")
public class IncomeServiceBean implements IncomeService {

	@PersistenceContext(unitName = "couponSystem")
	EntityManager incomeManager;

	// cunstractor
	public IncomeServiceBean() {}

	@Override
	public void storeIncome(Income income) {
		incomeManager.persist(income);
	}

	@Override
	public Collection<Income> viewAllIncome() {
		String query = "SELECT i FROM income i";
		return incomeManager.createQuery(query, Income.class).getResultList();
	}

	@Override
	public Collection<Income> ViewIncomeByCustomer(long customerId) {
		String query = "SELECT i FROM income i WHERE  i.id =" + customerId;
		return incomeManager.createQuery(query, Income.class).getResultList();
	}

	@Override
	public Collection<Income> ViewIncomeByCompany(long companyId) {
		String query = "SELECT i FROM income i WHERE  i.id =" + companyId;
		return incomeManager.createQuery(query, Income.class).getResultList();
	}

}
