//package com.zeon.HotelReservation.MessageQueue;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.zeon.HotelReservation.Modal.UserHotel;
//import com.zeon.HotelReservation.Services.HotelService;
//import com.zeon.HotelReservation.Services.UserHotelService;
//
//@Component
//public class RabbitMQConsumer {
//
//	@Autowired
//	UserHotelService us;
//	
//	@Autowired
//	HotelService ps;
//
//	@RabbitListener(queues = "userHotelQueue") // here we but listener on the queue
//	public void recievedMessage(UserHotel user) { // this Listener for TEST for /producer controller
//
//		System.out.println("Before save the Send UserHotel");
//		System.out.println("The Recieved UserDeleteRequest Message From RabbitMQ: " + user.toString());
//		//uh.saveUser(user);
//		System.out.println("After save the Send UserHotel");
//
//	}
//	
//	
//	
//	@RabbitListener(queues = "userRequestDelete") // here we but listener on the queue
//	public void recievedMessage(String id) {
//
//		System.out.println("Before save the Send UserHotel");
//		System.out.println("The Recieved UserDeleteRequest Message From RabbitMQ: " + id);
//		long idReq = Long.valueOf(id);
//		UserHotel uh = new UserHotel();
//		System.out.println("beeeeefor canceled .............");
//		uh = us.canceledReserve(idReq);
//		System.out.println("afffffter canceled .............");
////		session.setAttribute("userHotel", uh);
//		System.out.println("The user is .........." + uh.getUserName());
//		System.out.println("The user is .........." + uh.getEndDate());
//		
//		System.out. println("After save the Send UserHotel");
//
//	}
//	
//	
//	@RabbitListener(queues = "userHotelPostQueue") // here we but listener on the queue
//	public void recievedUserHotelPost(UserHotel user) {
//
//		System.out.println("Before save the Send UserHotel");
//		System.out.println("The Recieved UserDeleteRequest Message From RabbitMQ: " + user.toString());
//		us.saveUser(user);
//		
//		System.out.println("wait ................");
//		
//		ps.SetRoomAvalible(user.getHotelId(), (int) user.getRoomId(), user.getRoomType(), true);
//		System.out.println("After save the Send UserHotel");
//
//	}
//	
//}