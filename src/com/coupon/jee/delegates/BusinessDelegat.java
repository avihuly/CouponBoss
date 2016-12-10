package com.coupon.jee.delegates;

import java.util.Collection;
import com.coupon.jee.entities.Income;

public interface BusinessDelegat {
		public void storeIncome(Income income) ;
		public Collection<Income> viewAllIncome();
		public Collection<Income> ViewIncomeByCustomer(long customerId);
		public Collection<Income> ViewIncomeByCompany(long companyId);
}
