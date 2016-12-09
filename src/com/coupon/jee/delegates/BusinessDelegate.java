package com.coupon.jee.delegates;

import java.util.Collection;
import com.coupon.jee.entities.Income;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class BusinessDelegate {
	private static BusinessDelegate inctance;
	
	private final static String QUEUE_NAME = "java:/jms/queue/couponIncomeQueue";
	private final static String QUEUE_CON_FACTORY_NAME = "RemoteConnectionFactory";
	private Context context = null;
	private QueueConnectionFactory queueConnectionFactory = null;
	private QueueConnection queueConnection = null;
	private QueueSession queueSession = null;
	private Queue queue = null;
	private MessageProducer producer = null;

	// Constructor
	private BusinessDelegate() throws NamingException, JMSException {
		// get the initial context
		context = getInitialContext();
		// lookup the queue object
		queue = (Queue) context.lookup(QUEUE_NAME);
		// lookup the queue connection factory
		queueConnectionFactory = (QueueConnectionFactory) context.lookup(QUEUE_CON_FACTORY_NAME);
		// create a queue session		
		queueConnection = queueConnectionFactory.createQueueConnection();
		// create a queue session
		queueSession = queueConnection.createQueueSession(true, QueueSession.AUTO_ACKNOWLEDGE);
		// create a queue sender
		producer = queueSession.createProducer(queue);
		context.close();
	}

	// Singleton factory
	public static BusinessDelegate getInctance() throws NamingException, JMSException {
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
		System.out.println("B4 Q Send");
		
		try {
			ObjectMessage objectMessage = queueSession.createObjectMessage();
			objectMessage.setObject(income);
			producer.send(objectMessage);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("AFTER Q Send");	
		System.out.println("************************************");

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

	
	//
	// Initial Context
	// 
	public InitialContext getInitialContext() {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		// env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		try {
			return new InitialContext(env);
		} catch (NamingException e) {
			System.out.println("Cannot generate InitialContext");
			e.printStackTrace();
		}
		return null;
	}

}
