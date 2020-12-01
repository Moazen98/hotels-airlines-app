//package com.zeon.HotelReservation.Aspict;
//
//
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.zeon.HotelReservation.Services.HotelService;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//
//
//@Aspect
//@Component
//public class ServiceAspect {
//
//	@Autowired
//	HotelService ps;
//
//
////	@Around("@annotation(com.zeon.HotelReservation.Aspict.MyAnno)")
////	public void trackTime(ProceedingJoinPoint pjp) throws Throwable {
////		long stratTime=System.currentTimeMillis();
////		Object obj=pjp.proceed(); 
////		long endTime=System.currentTimeMillis();
////		System.out.println("time taken to execute : "+(endTime-stratTime));
////		
////	}
//	
//	@Before("@annotation(com.zeon.HotelReservation.Aspict.MyAnno)")
//	public void beforeAdvice(JoinPoint joinPoint) {
//		
//		
//	//	System.out.println("Before method:" + joinPoint.getSignature());
//
//	}
//
////	@After(value = "execution(* com.example.demo.services.UserService.*(..))")
////	public void afterAdvice(JoinPoint joinPoint) {
////		System.out.println("After method:" + joinPoint.getSignature());
////
////	} 
//	
//	
//	
//	
//	
//	
//}