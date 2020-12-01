//package com.zeon.UserPortable.MessageQueue;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.amqp.core.AmqpTemplate;  
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.zeon.UserPortable.Model.UserHotel;
//import com.zeon.UserPortable.Model.UserPlane;
//import com.zeon.UserPortable.Model.Users;
//
//// this class will send the message to the exchane rabbit
//
//@Service
//public class RabbitMQSender {
//	
//	@Autowired
//	private AmqpTemplate rabbitTemplate;
//	
////	@Value("${javainuse.rabbitmq.exchange}")
////	private String exchange;
////	
////	@Value("${javainuse.rabbitmq.routingkey}")
////	private String routingkey;	
//	
//	public void send(UserHotel user) {   // here I send the message
//		String queueName = "userHotelQueue";
//		String exchange = "direct-exchange";
//		String routingKey = "userHotel";
//		
//		rabbitTemplate.convertAndSend(exchange, routingKey, user);
//		System.out.println("The Send Message is : " + user);
//	    
//	}
//	
//	public void sendId(String id) {   
//		String queueName = "userRequestDelete";
//		String exchange = "direct-exchange";
//		String routingKey = "userDelete";
//		
//		rabbitTemplate.convertAndSend(exchange, routingKey, id);
//		System.out.println("The Send Message is : " + id);
//	    
//	}
//	public void sendUserHotelPost(UserHotel user) {   
//		String queueName = "userHotelPostQueue";
//		String exchange = "direct-exchange";
//		String routingKey = "userHotelPost";
//		
//		rabbitTemplate.convertAndSend(exchange, routingKey, user);
//		System.out.println("The Send Message is : " + user.toString());
//	    
//	}
//	
//	public void sendUserRegister(Users user) {   
//		String queueName = "userRegisterQueue";
//		String exchange = "direct-exchange";
//		String routingKey = "userRegister";
//		
//		rabbitTemplate.convertAndSend(exchange, routingKey, user);
//		System.out.println("The Send Message is : " + user.toString());
//	    
//	}
//	
//	
//	public void sendUserFlightRegisterGo(UserPlane user) { 
//		
//		String queueName = "userFlightRegisterQueueGo";
//		String exchange = "direct-exchange";
//		String routingKey = "userFlightRegisterGo";
//		
//		rabbitTemplate.convertAndSend(exchange, routingKey, user);
//		System.out.println("The Send Message is : " + user.toString());
//	    
//	}		
//	
//	public void sendUserFlightRegisterGoAndReturn(UserPlane user) { 
//		
//		String queueName = "userFlightRegisterQueueGoAndReturn";
//		String exchange = "direct-exchange";
//		String routingKey = "userFlightRegisterGoAndReturn";
//		
//		rabbitTemplate.convertAndSend(exchange, routingKey, user);
//		System.out.println("The Send Message is : " + user.toString());
//	    
//	}
//	
//	public void sendUserSetEditInfo(Users user) { 
//		
//		String queueName = "UserSetEditInfoQueue";
//		String exchange = "direct-exchange";
//		String routingKey = "UserSetEditInfo";
//		
//		rabbitTemplate.convertAndSend(exchange, routingKey, user);
//		System.out.println("The Send Message is : " + user.toString());
//	    
//	}
//}
