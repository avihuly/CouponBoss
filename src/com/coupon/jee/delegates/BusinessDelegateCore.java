package com.coupon.jee.delegates;

import java.util.Collection;
import com.coupon.jee.entities.Income;
import com.coupon.jee.services.IncomeService;
import com.coupon.jee.services.IncomeServiceBean;

import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class BusinessDelegateCore implements BusinessDelegat {
	private final static String QUEUE_NAME = "java:/jms/queue/couponIncomeQueue";
	private final static String QUEUE_CON_FACTORY_NAME = "java:/jms/RemoteConnectionFactory"; 	// private final static String INITIAL_CONTEXT_FACTORY ="org.jboss.naming.remote.client.InitialContextFactory";
	private final static String INITIAL_CONTEXT_FACTORY ="org.apache.activemq.jndi.ActiveMQInitialContextFactory";
	private final static String PROVIDER_URL = "http-remoting://localhost:8080";
	private final static String JMS_USERNAME="jmsuser";     
	private final static String JMS_PASSWORD="jmsuser@123";
	
	private Context context;
	private QueueConnectionFactory queueConnectionFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue queue;
	private MessageProducer producer;
	private IncomeService incomeService;

	// Constructor
	public BusinessDelegateCore() {
		init();
	}

	public void storeIncome(Income income) {
		try {
			ObjectMessage objectMessage = queueSession.createObjectMessage();
			objectMessage.setObject(income);
			producer.send(objectMessage);
		} catch (JMSException e) {
			System.out.println("*** Income was not registered: ");
			System.out.println(income);
			System.out.println("*** Problem encountered while storing income:");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public Collection<Income> viewAllIncome() {
		return incomeService.viewAllIncome();
	}

	public Collection<Income> ViewIncomeByCustomer(long customerId) {
		return incomeService.ViewIncomeByCustomer(customerId);
	}

	public Collection<Income> ViewIncomeByCompany(long companyId) {
		return incomeService.ViewIncomeByCompany(companyId);
	}

	// Init
	private void init() {
		final String appName = "CouponWebBoss";
		final String distinctName = "";
		final String beanName = IncomeServiceBean.class.getSimpleName();
		final String className = IncomeService.class.getName();
	
		try {
			// get the initial context
			context = getInitialContext();
			// lookup IncomeService
			incomeService = (IncomeService) context
					.lookup("ejb:" + appName + "/" + distinctName + "/" + beanName + "!" + className);
			// lookup the queue object
			queue = (Queue) context.lookup(QUEUE_NAME);
			// lookup the queue connection factory
			queueConnectionFactory = (QueueConnectionFactory) context.lookup(QUEUE_CON_FACTORY_NAME);
			// create a queue session
			queueConnection = queueConnectionFactory.createQueueConnection(JMS_USERNAME, JMS_PASSWORD);
			// create a queue session
			queueSession = queueConnection.createQueueSession(true, QueueSession.AUTO_ACKNOWLEDGE);
			// create a queue sender
			producer = queueSession.createProducer(queue);
		} catch (NamingException | JMSException e) {
			System.out.println("Initializing BusinessDelegateCore class encountered a problem:");
			e.printStackTrace();
		}
	}
	
	// Initial Context
	public InitialContext getInitialContext() {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, PROVIDER_URL);
		env.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
		env.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
		try {
			return new InitialContext(env);
		} catch (NamingException e) {
			System.out.println("Cannot generate InitialContext");
			e.printStackTrace();
		}
		return null;
	}
	
	// Close Connections
	private void close() {
		try {
			producer.close();
			queueSession.close();
			queueConnection.close();
		} catch (JMSException e) {
			System.out.println("Cannot Close Connections");
			e.printStackTrace();
		}
	}
	
}
