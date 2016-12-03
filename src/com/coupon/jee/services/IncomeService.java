package com.coupon.jee.services;

import java.util.Collection;
import javax.ejb.Remote;

import com.coupon.jee.entities.Income;

@Remote
public interface IncomeService {
	public void storeIncome(Income income);
	public Collection<Income> viewAllIncome();
	public Collection<Income> ViewIncomeByCustomer(long customerId);
	public Collection<Income> ViewIncomeByCompany(long companyId);
}
