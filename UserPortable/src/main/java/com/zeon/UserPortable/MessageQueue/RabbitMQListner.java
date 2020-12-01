//package com.zeon.UserPortable.MessageQueue;
//
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RabbitMQListner implements MessageListener {
//
//	
//	//To Recvie the string Publisher message
//	public void onMessage(Message message) {
//		System.out.println("The String Message");
//		System.out.println("Consuming Message - " + new String(message.getBody()));      //the error here 
//	}
//
//}