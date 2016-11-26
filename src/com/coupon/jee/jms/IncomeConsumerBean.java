package com.coupon.jee.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.coupon.jee.entities.Income;

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
    //On Message
    public void onMessage(Message message) {
        
    }

}
