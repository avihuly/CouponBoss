package com.coupon.jee.delegates;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import com.coupon.jee.entities.Income;
import com.coupon.jee.services.IncomeService;

public class BusinessDelegate {

	private static BusinessDelegate inctance;

	@EJB(name = "IncomeServiceEJB")
	private IncomeService iencomeService;

	@Resource(name = "RemoteConnectionFactory")
	private ConnectionFactory connectionFactory;
	@Resource(mappedName = "java:/jms/queue/couponIncomeQueue")
	private Queue couponIncomeQueue;
	private JMSContext jmsContext;
	private JMSProducer jmsProducer;

	// Constructor
	private BusinessDelegate() {
		System.out.println("************************************");
		System.out.println(connectionFactory);
		System.out.println(couponIncomeQueue);
		System.out.println("************************************");
		jmsContext = connectionFactory.createContext();
		jmsProducer = jmsContext.createProducer();
	};

	// Singleton factory
	public static BusinessDelegate getInctance() {
		if (inctance == null) {
			return new BusinessDelegate();
		}
		return inctance;
	}

	//
	// Methods
	//
	public void storeIncome(Income income) {
		System.out.println("************************************");
		System.out.println(connectionFactory);
		System.out.println(couponIncomeQueue);
		System.out.println(jmsContext);
		System.out.println(jmsProducer);
		System.out.println("************************************");
		
		jmsProducer.send(couponIncomeQueue, income);
	}

	public Collection<Income> viewAllIncome() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Income> ViewIncomeByCustomer(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Income> ViewIncomeByCompany(long companyId) {
		// TODO Auto-generated method stub
		return null;
	}
}
