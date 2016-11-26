package com.coupon.jee.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.coupon.jee.services.IncomeService;

/**
 * Message-Driven Bean implementation class for: IncomeConsumerBean
 */
@MessageDriven(
		name = "IncomeConsumerBean",
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "********QUEUE NAME*******")
		})
public class IncomeConsumerBean implements MessageListener {
	
	@EJB (name ="IncomeServiceEJB") IncomeService iencomeService; 
	
	//On Message
    public void onMessage(Message message) {
        
    }

}
