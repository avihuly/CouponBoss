package com.coupon.jee.delegates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.coupon.jee.entities.Income;
import com.coupon.jee.entities.IncomeType;

public class BusinessDelegateMockup implements BusinessDelegat {

	@Override
	public void storeIncome(Income income) {
		System.out.println("***************************");
		System.out.println("BusinessDelegateMockup storeIncome => " + income);
		System.out.println("***************************");
	}

	@Override
	public Collection<Income> viewAllIncome() {
		Collection<Income> incomeCollection = new ArrayList<>();

		for (int i = 0; i < 5; i++) 
			incomeCollection.add(
					new Income(i+10000,"Custimer " + i,LocalDate.now(),IncomeType.CUSTOMER_PURCHASE,59.99));
		
		for (int i = 0; i < 5; i++) 
			incomeCollection.add(
					new Income(i+10000,"Company " + i,LocalDate.now(),IncomeType.COMAPNY_UPDATE_COUPON,10));
		
		for (int i = 0; i < 5; i++) 
			incomeCollection.add(
					new Income(i+10000,"Company " + i,LocalDate.now(),IncomeType.COMPANY_NEW_COUPON,100));
		
		return incomeCollection;
	}

	@Override
	public Collection<Income> ViewIncomeByCustomer(long customerId) {
		Collection<Income> incomeCollection = new ArrayList<>();

		for (int i = 0; i < 5; i++) 
			incomeCollection.add(
					new Income(i+10000,"Custimer " +  customerId,LocalDate.now(),IncomeType.CUSTOMER_PURCHASE,59.99));
		
		return incomeCollection;
	}

	@Override
	public Collection<Income> ViewIncomeByCompany(long companyId) {
		Collection<Income> incomeCollection = new ArrayList<>();

		for (int i = 0; i < 5; i++) 
			incomeCollection.add(
					new Income(i+10000,"Company " +  companyId,LocalDate.now(),IncomeType.COMPANY_NEW_COUPON,100));
		
		for (int i = 0; i < 5; i++) 
			incomeCollection.add(
					new Income(i+10000,"Company " +  companyId,LocalDate.now(),IncomeType.COMAPNY_UPDATE_COUPON,10));
		
		return incomeCollection;
	}
}
