//package com.zeon.UserPortable.MessageQueue;
//
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.modal.User;
//import com.example.demo.services.UserService;
//
//
//@Component
//public class RabbitMQConsumer {
//	
//	@Autowired
//	UserService userService;
//
//	@RabbitListener(queues = "${javainuse.rabbitmq.queue}")  //here we but listener on the queue
//	public void recievedMessage(User user) {
//
//		System.out.println("hellllllllllllllllllll***********************");
//		System.out.println("The Recieved User Message From RabbitMQ: " + user);
//		
//		boolean bool = userService.userExisit(user.getUsername());
//		if(!bool) {
//			userService.saveMyUser(user);
//		
//		}
//		else {
//			System.out.println("ssss");
//		}
//		
//	}
//}