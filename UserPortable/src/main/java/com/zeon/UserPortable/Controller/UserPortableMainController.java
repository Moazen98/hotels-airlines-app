package com.zeon.UserPortable.Controller;

import java.io.IOException;  
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.asn1.dvcs.Data;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.HotelOffer.MediaURI;
import com.amadeus.resources.HotelOffer.Offer;
import com.google.gson.Gson;

import com.netflix.appinfo.InstanceInfo;

import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zeon.UserPortable.Export.ExcelListReportUserHotelView;
import com.zeon.UserPortable.Export.ExcelListReportUserPlaneView;
import com.zeon.UserPortable.Export.PDFListReportUserHotelView;
import com.zeon.UserPortable.Export.PDFListReportUserPlaneView;
//import com.zeon.UserPortable.MessageQueue.RabbitMQSender;
import com.zeon.UserPortable.Model.AddressHotel;
import com.zeon.UserPortable.Model.Contact;
import com.zeon.UserPortable.Model.Coordinates;
import com.zeon.UserPortable.Model.DualRoom;
import com.zeon.UserPortable.Model.EmpFlight;
import com.zeon.UserPortable.Model.Hotel;
import com.zeon.UserPortable.Model.Plane;
import com.zeon.UserPortable.Model.Role;
import com.zeon.UserPortable.Model.SingleRoom;
import com.zeon.UserPortable.Model.SuitRoom;
import com.zeon.UserPortable.Model.Taxi;
import com.zeon.UserPortable.Model.User;
import com.zeon.UserPortable.Model.UserBank;
import com.zeon.UserPortable.Model.UserHotel;
import com.zeon.UserPortable.Model.UserPlane;
import com.zeon.UserPortable.Model.Users;
import com.zeon.UserPortable.Service.Oparations;

@Controller
public class UserPortableMainController {

	@Autowired
	EurekaClient eurakClient;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Autowired
	EurekaDiscoveryClient dicovery;

	@Autowired
	public JavaMailSender javaMailSender;

	// here we use it if we have more than one queue
//	@Autowired
//	private AmqpTemplate amqpTemplate;
	@Autowired
	private AmqpTemplate amqpTemplate;

	//@Autowired
	//RabbitMQSender rabbitMQSender;

	int planeServicePort;
	int empServicePort;
	int userDataServicePort;
	int userServicePort;
	int hotelServicePort;

	Oparations ops = new Oparations();

	private User[] userPar = new User[20];

	private EmpFlight[] empPar = new EmpFlight[20];

	@GetMapping(value = "/producer")
	@ResponseBody
	public String producer() {

		UserHotel user = new UserHotel(200, 201, 202, "USA", "www.nero98.com", new Date(), new Date(), "single", 0, 80,
				false, 1);

		String queueName = "userHotelQueue";
		String exchange = "direct-exchange";
		String routingKey = "userHotel";

		amqpTemplate.convertAndSend(exchange, routingKey, user);

		// amqpTemplate.convertAndSend("javainuseExchange", "javainuse", user);
	//	rabbitMQSender.send(user);
		return "Message sent to the RabbitMQ Successfully";

	}

	@GetMapping("/")
	public String welcomPage(HttpServletRequest request) {
		return "homepage";
	}

	@GetMapping("/home")
	public String homePage(HttpServletRequest request) {
		return "welcomepage";
	}

///////////////                 The Plane Operation					///////////////

	@GetMapping("/register-plane")
	public String registerPage() {
		return "Register";
	}

	@RequestMapping(value = "/get-plane-port", method = RequestMethod.GET)
	@ResponseBody
	public String getPlaneServicePort() {

		RestTemplate restTemplate = restTemplateBuilder.build();
		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("FLIGHTRESEVATION-SERVICE", false);
		InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);

		String baseUrl = info.getHomePageUrl();

		planeServicePort = info.getPort();

		System.out.println("The info is ........................... :" + baseUrl);

		return restTemplate.getForObject(baseUrl + "/flightReserveion/location", String.class);

	}

	public String getFallBackMethod(@RequestParam String from, @RequestParam String to, @RequestParam String depart,
			@RequestParam String retturn, @RequestParam int child, @RequestParam int adult, @RequestParam String degree,
			HttpServletRequest request) {

		return "stop";
	}

	@RequestMapping(value = "/payment-plane", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getFallpaymentPlane")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String paymentPlane(@RequestParam String price, @RequestParam int plane_id, @RequestParam String useremail,
			@RequestParam long accountId, @RequestParam String username, @RequestParam String password,
			@RequestParam String location, @RequestParam String destination, @RequestParam String date,
			@RequestParam String arriveTime, @RequestParam String book, HttpServletRequest request, Principal principal,
			HttpSession s) throws IOException, ParseException {

		
		s.setAttribute("type", 0);
		
		SimpleDateFormat formatter2 = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		Date date1 = formatter2.parse(date);

		int sum = (Integer) s.getAttribute("sum");
		String degree = (String) s.getAttribute("degree");

		System.out.println("the degreeee is " + degree);
		System.out.println("the sum is " + sum);

		float p = Float.parseFloat(price);
		float totalPrice = Math.abs(p * sum);
		String priceString = String.valueOf(totalPrice);

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("BANKSYSTEMRESEVATION-SERVICE", false);
		String baseUrl = info.getHomePageUrl();

		List<String> res = new ArrayList<String>();
		List<String> listInfo = new ArrayList<String>();
		listInfo.add(useremail);
		listInfo.add(password);
		listInfo.add(Long.toString(accountId));
		listInfo.add(priceString);

		// System.out.println("The PriceString day is .........................:" +
		// priceString);

		System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		UserBank userBank = new UserBank();

		try {
			// for request
			response = restTemplate.exchange(baseUrl + "/check-bank-account", HttpMethod.POST, requestEntity,
					String.class);
			// res = ArrayList.valueOf(response.getBody());
			// int answer =Integer.valueOf(response.getBody());

			res = new Gson().fromJson(response.getBody(), ArrayList.class);
			System.out.println("Reemty ..............." + res.toString());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return "stop"; ////////// here I need GUI
		} else if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println("The res is ....." + Integer.valueOf(res.get(0)));

			if (Integer.valueOf(res.get(0)) != 0) {

				UserPlane userPlane = new UserPlane();
				userPlane.setUserName(useremail);
				userPlane.setPlanelId(plane_id);
				userPlane.setAvalible(1);
				userPlane.setTotalCost(totalPrice);
				userPlane.setAccountId(accountId);
				userPlane.setCanceled(false);
				userPlane.setPlaneType("oneWay");
				userPlane.setDegree(degree);
				userPlane.setNum(sum);
				userPlane.setLocation(location);
				userPlane.setDestination(destination);
				userPlane.setDate(date1);
				userPlane.setArriveTime(arriveTime);

				System.out.println("The response is : ......" + response.getBody());

				int taxicheck = 0;

			

				s.setAttribute("userPlanePostOne", userPlane);
				

				request.setAttribute("userbankinfo", userBank);

				if (book.equals("1")) {
					checkTaxiExist(principal, request, s);
				}
				
				
				 s = request.getSession();
				 
				 
				Taxi taxi = new Taxi();
				taxi =  (Taxi) s.getAttribute("TaxiExist");
				
				if (book.equals("1") && taxi != null) {
					taxicheck = 1;
					userPlane.setTaxiReserve(1);
				}else {
					userPlane.setTaxiReserve(0);
				}

				userPlanePost(userPlane);
				
				return "index";

			} else {
				return "noresult";
			}
		} else {

			System.out.println("The Services is OK ................. :" + response.getStatusCode());
			return "403";
		}

	}

	@ResponseBody
	public String getFallpaymentPlane(@RequestParam String price, @RequestParam int plane_id, @RequestParam String useremail,
			@RequestParam long accountId, @RequestParam String username, @RequestParam String password,
			@RequestParam String location, @RequestParam String destination, @RequestParam String date,
			@RequestParam String arriveTime, @RequestParam String book, HttpServletRequest request, Principal principal,
			HttpSession s) { /////////// **********************************************?///////////////////////

		int type = (int) s.getAttribute("type");

		if (type == 0) {

			UserPlane userPlane3 = (UserPlane) s.getAttribute("userPlanePostOne");

			String queueName = "userFlightRegisterQueueGo";
			String exchange = "direct-exchange";
			String routingKey = "userFlightRegisterGo";

			amqpTemplate.convertAndSend(exchange, routingKey);

		//	rabbitMQSender.sendUserFlightRegisterGo(userPlane3);

			return "error6";

		} else {
			UserPlane userPlane1 = (UserPlane) s.getAttribute("userPlanePostGo");

			UserPlane userPlane2 = (UserPlane) s.getAttribute("userPlanePostReturn");

			String queueName = "userFlightRegisterQueueGoAndReturn";
			String exchange = "direct-exchange";
			String routingKey = "userFlightRegisterGoAndReturn";

			amqpTemplate.convertAndSend(exchange, routingKey);

		//	rabbitMQSender.sendUserFlightRegisterGo(userPlane1);

		//	rabbitMQSender.sendUserFlightRegisterGo(userPlane2);

			return "error6";

		}

	}

	@RequestMapping(value = "/payment-return-plane", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getFallpaymentReturnPlane")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
//	@ResponseBody
	public String paymentReturnPlane(@RequestParam String price, @RequestParam String pricet,
			@RequestParam long plane_id, @RequestParam long planet_id, @RequestParam String useremail,
			@RequestParam long accountId, @RequestParam String username, @RequestParam String password,
			@RequestParam String location, @RequestParam String destination, @RequestParam String locationt,
			@RequestParam String destinationt, @RequestParam String date, @RequestParam String arriveTime,
			@RequestParam String datet, @RequestParam String arriveTimet, @RequestParam String book,
			Principal principal, HttpServletRequest request) throws IOException, ParseException {

		HttpSession s = request.getSession();
		
		s.setAttribute("type", 1);
		
		System.out.println("The date is ..........." + date);
		System.out.println("The datet is ..........." + datet);
		SimpleDateFormat formatter2 = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		Date date1 = formatter2.parse(date);
		Date date2 = formatter2.parse(datet);

		
		int sum = (Integer) s.getAttribute("sum");
		String degree = (String) s.getAttribute("degree");

		System.out.println("the degreeee is " + degree);
		System.out.println("the sum is " + sum);

		float priceString2 = Float.valueOf(price);
		float pricetString2 = Float.valueOf(pricet);

		System.out.println("The Price is :" + priceString2);
		System.out.println("The Price2 is :" + pricetString2);

		float p = priceString2 + pricetString2;
		float totalPrice = Math.abs(p * sum);
		String priceString = String.valueOf(totalPrice);

		System.out.println("The totalPrice is :" + totalPrice);

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("BANKSYSTEMRESEVATION-SERVICE", false);
		String baseUrl = info.getHomePageUrl();

		System.out.println("The Price is ......" + priceString);

		List<String> res = new ArrayList<String>();
		List<String> listInfo = new ArrayList<String>();
		listInfo.add(useremail);
		listInfo.add(password);
		listInfo.add(Long.toString(accountId));
		listInfo.add(priceString);

		// System.out.println("The PriceString day is .........................:" +
		// priceString);

		System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		UserBank userBank = new UserBank();

		try {
			// for request
			response = restTemplate.exchange(baseUrl + "/check-bank-account", HttpMethod.POST, requestEntity,
					String.class);
			// res = ArrayList.valueOf(response.getBody());
			// int answer =Integer.valueOf(response.getBody());

			res = new Gson().fromJson(response.getBody(), ArrayList.class);
			System.out.println("Reemty ..............." + res.toString());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return "stop"; ////////// here I need GUI
		} else if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println("The res is .........." + Integer.valueOf(res.get(0)));

			if (Integer.valueOf(res.get(0)) != 0) {
				UserPlane userPlane = new UserPlane();
				UserPlane userPlanet = new UserPlane();
				userPlane.setUserName(useremail);
				userPlane.setPlanelId(plane_id);
				userPlane.setAvalible(1);
				userPlane.setTotalCost(Integer.valueOf(price) * sum);
				userPlane.setAccountId(accountId);
				userPlane.setCanceled(false);
				userPlane.setPlaneType("oneWay");
				userPlane.setDegree(degree);
				userPlane.setNum(sum);
				userPlane.setLocation(location);
				userPlane.setDestination(destination);
				userPlane.setDate(date1);
				userPlane.setArriveTime(arriveTime);

				userPlanet.setUserName(useremail);
				userPlanet.setPlanelId(planet_id);
				userPlanet.setAvalible(1);
				userPlanet.setTotalCost(Integer.valueOf(pricet) * sum);
				userPlanet.setAccountId(accountId);
				userPlanet.setCanceled(false);
				userPlanet.setPlaneType("oneWay");
				userPlanet.setDegree(degree);
				userPlanet.setNum(sum);
				userPlanet.setLocation(locationt);
				userPlanet.setDestination(destinationt);
				userPlanet.setDate(date2);
				userPlanet.setArriveTime(arriveTimet);

				System.out.println("The response is : ......" + response.getBody());

				System.out.println("The User Plane ttttttttttt  is : ......" + userPlanet.toString());

				s.setAttribute("userPlanePostGo", userPlane);
				s.setAttribute("userPlanePostReturn", userPlanet);
				

				int checktaxi = 0;

//				if (book.equals("1")) {
//					checktaxi = 1;
//					userPlane.setTaxiReserve(1);
//				}

				

				request.setAttribute("userbankinfo", userBank);

				if (book.equals("1")) {
					checkTaxiExist(principal, request, s);
				}
				
				 s = request.getSession();
				 
				 
					Taxi taxi = new Taxi();
					taxi =  (Taxi) s.getAttribute("TaxiExist");
					
					if (book.equals("1") && taxi != null) {
						checktaxi = 1;
						userPlane.setTaxiReserve(1);
					}else {
						userPlane.setTaxiReserve(0);
					}

					userPlanePost(userPlane);
					userPlanePost(userPlanet);

				return "index";
			} else {
				return "noresult";
			}
		} else {
			System.out.println("The Services is OK ................. :" + response.getStatusCode());
			return "403";
		}

	}

	public String getFallpaymentReturnPlane(@RequestParam String price, @RequestParam String pricet,
			@RequestParam long plane_id, @RequestParam long planet_id, @RequestParam String useremail,
			@RequestParam long accountId, @RequestParam String username, @RequestParam String password,
			@RequestParam String location, @RequestParam String destination, @RequestParam String locationt,
			@RequestParam String destinationt, @RequestParam String date, @RequestParam String arriveTime,
			@RequestParam String datet, @RequestParam String arriveTimet, @RequestParam String book,
			Principal principal, HttpServletRequest request) {

		HttpSession s = request.getSession();

		int type = (int) s.getAttribute("type");

		System.out.println("The Type is .................. :" + type);

		if (type == 0) {

			UserPlane userPlane3 = (UserPlane) s.getAttribute("userPlanePostOne");

			String queueName = "userFlightRegisterQueueGo";
			String exchange = "direct-exchange";
			String routingKey = "userFlightRegisterGo";

//			amqpTemplate.convertAndSend(exchange, routingKey);
//
//			rabbitMQSender.sendUserFlightRegisterGo(userPlane3);

			return "error6";

		} else {
			UserPlane userPlane1 = (UserPlane) s.getAttribute("userPlanePostGo");

			UserPlane userPlane2 = (UserPlane) s.getAttribute("userPlanePostReturn");

			String queueName = "userFlightRegisterQueueGoAndReturn";
			String exchange = "direct-exchange";
			String routingKey = "userFlightRegisterGoAndReturn";

		//	rabbitMQSender.sendUserFlightRegisterGoAndReturn(userPlane1);

		//	rabbitMQSender.sendUserFlightRegisterGoAndReturn(userPlane2);
//			amqpTemplate.convertAndSend(routingKey, userPlane1);
//
//			rabbitMQSender.sendUserFlightRegisterGoAndReturn(userPlane1);

//			amqpTemplate.convertAndSend(exchange, routingKey, userPlane1);

			// amqpTemplate.convertAndSend("javainuseExchange", "javainuse", user);
//			rabbitMQSender.sendUserFlightRegisterGoAndReturn(userPlane1);

			// rabbitMQSender.sendUserFlightRegisterGo(userPlane2);

			return "error6";

		}

	}

	@RequestMapping("/check-taxi-exist")
	@ResponseBody
	public String checkTaxiExist(Principal principal, HttpServletRequest request, HttpSession s) {

		ArrayList<String> list = new ArrayList<String>();

		int type = (int) s.getAttribute("type");

		if (type == 0) {

			UserPlane userPlane3 = (UserPlane) s.getAttribute("userPlanePostOne");

			String country = userPlane3.getDestination();
			String timeArrive = userPlane3.getArriveTime();
			Date date = userPlane3.getDate();

			list.add(country);
			list.add(timeArrive);
			list.add(date.toString());

		} else {
			UserPlane userPlane1 = (UserPlane) s.getAttribute("userPlanePostGo");

			UserPlane userPlane2 = (UserPlane) s.getAttribute("userPlanePostReturn");

			String city = userPlane1.getDestination();
			String timeArrive = userPlane1.getArriveTime();
			Date date = userPlane1.getDate();

			list.add(city);
			list.add(timeArrive);
			list.add(date.toString());

		}

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<List<String>> requestEntity2 = new HttpEntity<>(list, headers2);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("TAXIRESERVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		Taxi taxi = new Taxi();

		try {

			System.out.println("Before .....................");
			response2 = restTemplate.exchange(baseUrl2 + "/check-taxi", HttpMethod.POST, requestEntity2, String.class);
			taxi = new Gson().fromJson(response2.getBody(), Taxi.class);
			System.out.println("The Res Taxi is : ............" + taxi.toString());
			s.setAttribute("TaxiExist", taxi);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			return "403";

		} else if (response2.getStatusCode() == HttpStatus.OK) {

			if (taxi.getCarModel().equals("")) {
				System.out.println("The Taxi is Null .......");
				return "error6";
			} else {

				System.out.println("Before save userTaxi Service");
				userTaxiPost(principal, request, s, taxi, type);
				System.out.println("After save userTaxi Service");

				return "index";
			}

			
		} else {
			return "403";
		}

	}

	public void userTaxiPost(Principal principal, HttpServletRequest request, HttpSession s, Taxi taxi, int type) {

		List<String> param = new ArrayList<String>();

		Users user = UserInfo(principal, request, s);

		param.add(user.getName());
		param.add(user.getEmail());
		param.add(user.getPhoneNumber());
		param.add(String.valueOf(user.getId()));
		param.add(String.valueOf(taxi.getId()));

		param.add(String.valueOf(1));

		System.out.println("The Type is ..... :" + type);

		if (type == 0) {

			UserPlane userPlane3 = (UserPlane) s.getAttribute("userPlanePostOne");

			String city = userPlane3.getDestination();
			String timeArrive = userPlane3.getArriveTime();
			Date date = userPlane3.getDate();

			param.add(userPlane3.getArriveTime());

			param.add(String.valueOf(city));

		} else {
			UserPlane userPlane1 = (UserPlane) s.getAttribute("userPlanePostGo");

			UserPlane userPlane2 = (UserPlane) s.getAttribute("userPlanePostReturn");

			String city = userPlane1.getDestination();
			String timeArrive = userPlane1.getArriveTime();
			Date date = userPlane1.getDate();

			param.add(userPlane1.getArriveTime());

			param.add(city);

		}

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers3 = new HttpHeaders();
		headers3.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response3 = null;
		HttpEntity<List<String>> requestEntity3 = new HttpEntity<>(param, headers3);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("TAXIRESERVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		int res;

		try {

			response3 = restTemplate.exchange(baseUrl2 + "/save-user-taxi", HttpMethod.POST, requestEntity3,
					String.class);
			// res = new Gson().fromJson(response2.getBody(), Integer.class);
			// System.out.println("The taxi ops is :"+res);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@GetMapping("/user-plane-post")
	@ResponseBody
	public String userPlanePost(UserPlane userPlane) {

		UserPlane userPlane2 = new UserPlane();

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<UserPlane> requestEntity2 = new HttpEntity<>(userPlane, headers2);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("FLIGHTRESEVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		List<String> listInfo2 = new ArrayList<String>();

		try {
			// for request
			int res2;
			response2 = restTemplate.exchange(baseUrl2 + "/save-user-plane", HttpMethod.POST, requestEntity2,
					String.class);
			res2 = Integer.valueOf(response2.getBody());
			// int answer =Integer.valueOf(response.getBody());
			System.out.println("Modty ..............." + response2.getBody());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			System.out.println("The User Hotel  not Create ................. :" + response2.getStatusCode());
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {
			return "index";
		} else {
			return "403";
		}

	}

	@GetMapping("/get-all-user-plane-reserve")
	@HystrixCommand(fallbackMethod = "getFallgetAllUserPlaneReserve")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String getAllUserPlaneReserve(HttpServletRequest request, HttpSession session) {

		HttpSession s = request.getSession();
		Users user = new Users();
		user =  (Users) s.getAttribute("userLogin");
		
		String userName = user.getEmail();
		UserPlane userPlane2 = new UserPlane();

		List<String> param = new ArrayList<String>();
		param.add(userName);

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<List<String>> requestEntity2 = new HttpEntity<>(param, headers2);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("FLIGHTRESEVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		UserPlane[] userPlanes = new UserPlane[500];
		UserPlane userPlane = new UserPlane();

		List<String> listInfo2 = new ArrayList<String>();

		try {
			// for request
			int res2;
			response2 = restTemplate.exchange(baseUrl2 + "/get-all-reserved-user", HttpMethod.POST, requestEntity2,
					String.class);
			userPlanes = new Gson().fromJson(response2.getBody(), UserPlane[].class);
			// int answer =Integer.valueOf(response.getBody());
			System.out.println("After getAll UserHotel of user ..............." + response2.getBody());

			System.out.println("The all user hotel size is ............:" + userPlanes.length);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			System.out.println("The User Hotel  not Create ................. :" + response2.getStatusCode());
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {
			request.setAttribute("reserved", userPlanes);
			session.setAttribute("userPlaneExport", userPlanes);
			return "showalluserplanereserve";
		} else {
			return "403";
		}

	}
	
	public String getFallgetAllUserPlaneReserve(HttpServletRequest request, HttpSession session) {
		return "error6";
	}

	@RequestMapping("/delete-reserve-plane")
	@HystrixCommand(fallbackMethod = "getFalldeleteReservedPlane")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String deleteReservedPlane(@RequestParam long id, @RequestParam String userName,
			@RequestParam String location, @RequestParam String destination, @RequestParam int taxiReserve,
			HttpServletRequest request) {

		// userName = "www.nero98.com";
		// UserHotel userHotel2 = new UserHotel();

		// List<String> param = new ArrayList<String>();
		// param.add(userName);

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<Long> requestEntity2 = new HttpEntity<>(id, headers2);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("FLIGHTRESEVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		try {
			// for request
			int res2;
			response2 = restTemplate.exchange(baseUrl2 + "/canceled-reserved", HttpMethod.POST, requestEntity2,
					String.class);
			res2 = new Gson().fromJson(response2.getBody(), Integer.class);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {

			if (taxiReserve != 0) {
				System.out.println("Before deleteReservedTaxi ........... :");

				deleteReservedTaxi(userName, location, destination, request);
				System.out.println("After deleteReservedTaxi ........... :");
			}

			return "index";
		} else {
			return "403";
		}
	}

	public String getFalldeleteReservedPlane(@RequestParam long id, @RequestParam String userName,
			@RequestParam String location, @RequestParam String destination, @RequestParam int taxiReserve,
			HttpServletRequest request) {

		System.out.println("The Service is down ......");
		return "error6";
	}

	@RequestMapping("/delete-reserve-taxi")
	public String deleteReservedTaxi(String userName, String location, String destination, HttpServletRequest request) {

		List<String> param = new ArrayList<String>();
		param.add(userName);
		param.add(location);
		param.add(destination);

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<List<String>> requestEntity2 = new HttpEntity<>(param, headers2);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("TAXIRESERVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		try {
			// for request
			int res2;
			response2 = restTemplate.exchange(baseUrl2 + "/delete-taxi", HttpMethod.POST, requestEntity2, String.class);
			res2 = new Gson().fromJson(response2.getBody(), Integer.class);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {
			return "index";
		} else {
			return "403";
		}
	}

///////////////                 The Employee Operation					///////////////

	@RequestMapping(value = "/get-emp-port", method = RequestMethod.GET)
	@ResponseBody
	public String getUserDataServicePort() {

		RestTemplate restTemplate = restTemplateBuilder.build();

		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("USERDATARESEVATION-SERVICE", false);
		InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);

		String baseUrl = info.getHomePageUrl();

		empServicePort = info.getPort();

		System.out.println("The info is ........................... :" + baseUrl);

		return restTemplate.getForObject(baseUrl + "/userReserveion/location", String.class);

	}

///////////////                 The User Operation					///////////////

	@RequestMapping(value = "/get-user-port", method = RequestMethod.GET)
	@ResponseBody
	public String getUserServicePort() {

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("USERDATARESEVATION-SERVICE", false);

		String baseUrl = info.getHomePageUrl();

		userDataServicePort = info.getPort();

		System.out.println("The info is ........................... :" + baseUrl);

		return restTemplate.getForObject(baseUrl + "/location", String.class);

	}

	@RequestMapping(value = "/add-usr", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getFallbackAddUser")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String addUser(@RequestParam String username, @RequestParam String lastname, @RequestParam String email,
			@RequestParam String phone, @RequestParam String password, HttpServletRequest request)
			throws RestClientException, IOException {

		List<String> param = new ArrayList<String>();
		param.add(username);
		param.add(lastname);
		param.add(email);
		param.add(phone);
		param.add(password);

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<List<String>> requestEntity2 = new HttpEntity<>(param, headers2);

		// InstanceInfo info2 = eurakClient.getNextServerFromEureka("ZUUL-SERVER",
		// false);
		InstanceInfo info2 = eurakClient.getNextServerFromEureka("USERDATARESEVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();
		int res2 = 0;

		try {

			response2 = restTemplate.exchange(baseUrl2 + "/add-user-account", HttpMethod.POST, requestEntity2,
					String.class);
			res2 = new Gson().fromJson(response2.getBody(), Integer.class);
			System.out.println("After Save User Account ..............." + response2.getBody());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			System.out.println("The  User Account  not Create ................. :" + response2.getStatusCode());
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {

			if (res2 == 1) {

				return "index";
			} else {
				return "register";
			}
		} else {
			return "403";
		}

	}

	public String getFallbackAddUser(@RequestParam String username, @RequestParam String lastname,
			@RequestParam String email, @RequestParam String phone, @RequestParam String password,
			HttpServletRequest request) {

		return "error6";

	}

	@RequestMapping(value = "/add-usr-account", method = RequestMethod.GET)
	public String addUserAccount(@RequestParam String username, @RequestParam String lastname,
			@RequestParam String email, @RequestParam String phone, @RequestParam String password,
			HttpServletRequest request) throws RestClientException, IOException {

		List<String> param = new ArrayList<String>();
		param.add(username);
		param.add(lastname);
		param.add(email);
		param.add(phone);
		param.add(password);

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<List<String>> requestEntity2 = new HttpEntity<>(param, headers2);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
		// InstanceInfo info2 =
		// eurakClient.getNextServerFromEureka("BANKSYSTEMRESEVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		try {
			int res2;
			response2 = restTemplate.exchange(baseUrl2 + "/userReserveion/addtest", HttpMethod.POST, requestEntity2,
					String.class);
			res2 = new Gson().fromJson(response2.getBody(), Integer.class);
			System.out.println("After getAll UserHotel of user ..............." + response2.getBody());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			System.out.println("The User Hotel  not Create ................. :" + response2.getStatusCode());
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {
			return "index";
		} else {
			return "403";
		}

	}

//////////////////////////////////////////for reserve
	@GetMapping("/get-all-user-reserve")
	@HystrixCommand(fallbackMethod = "getFallbackgetAllUserReserve")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String getAllUserReserve(HttpServletRequest request, HttpSession session,Principal principle) {

		HttpSession s = request.getSession();
		Users user = new Users();
		user =  (Users) s.getAttribute("userLogin");
		
		String userName = user.getEmail();
		System.out.println("The userName is .............:"+userName);
		UserHotel userHotel2 = new UserHotel();

		List<String> param = new ArrayList<String>();
		param.add(userName);

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<List<String>> requestEntity2 = new HttpEntity<>(param, headers2);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
		// InstanceInfo info2 =
		// eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		UserHotel[] userHotels = new UserHotel[500];
		UserHotel userHotel = new UserHotel();

		List<String> listInfo2 = new ArrayList<String>();

		try {
// for request
			int res2;
			response2 = restTemplate.exchange(baseUrl2 + "/hotelReserveion/get-all-reserved-user", HttpMethod.POST,
					requestEntity2, String.class);
			userHotels = new Gson().fromJson(response2.getBody(), UserHotel[].class);
// int answer =Integer.valueOf(response.getBody());
			System.out.println("After getAll UserHotel of user ..............." + response2.getBody());

			System.out.println("The all user hotel size is ............:" + userHotels.length);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			System.out.println("The User Hotel  not Create ................. :" + response2.getStatusCode());
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {
			request.setAttribute("reserved", userHotels);
			session.setAttribute("userHotelExport", userHotels);

			return "showalluserreserve";
		} else {
			return "403";
		}

	}
	
	public String getFallbackgetAllUserReserve(HttpServletRequest request, HttpSession session,Principal principle) {
		
		return "error6";
	}

	@RequestMapping("/delete-reserve")
	@HystrixCommand(fallbackMethod = "getFallbackDeleteReserve")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String deletereserve(@RequestParam long id, HttpServletRequest request) {

//userName = "www.nero98.com";
//UserHotel userHotel2 = new UserHotel();

//List<String> param = new ArrayList<String>();
//param.add(userName);

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<Long> requestEntity2 = new HttpEntity<>(id, headers2);

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
		// InstanceInfo info2 =
		// eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		try {
// for request
			int res2;
			response2 = restTemplate.exchange(baseUrl2 + "/hotelReserveion/canceled-reserved", HttpMethod.POST,
					requestEntity2, String.class);
			res2 = new Gson().fromJson(response2.getBody(), Integer.class);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {
			return "index";
		} else {
			return "403";
		}
	}

	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	@ResponseBody
	public String getFallbackDeleteReserve(@RequestParam long id, HttpServletRequest request) {

//		UserHotel user = new UserHotel(200, 201, 202, "USA", "www.nero98.com", new Date(), new Date(),
//				"single", 0, 80, false, 1);
		String queueName = "userRequestDelete";
		String exchange = "direct-exchange";
		String routingKey = "userDelete";
		String s = String.valueOf(id);

		amqpTemplate.convertAndSend(exchange, routingKey, s);

		// amqpTemplate.convertAndSend("javainuseExchange", "javainuse", user);
		// rabbitMQSender.sendId(s);
		return "error6";

	}

	@RequestMapping(value = "/edit-usr", method = RequestMethod.POST) ///////////// ????????????????????????????????????????
	public String editUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request)
			throws RestClientException, IOException {

		System.out.println("The user is : " + user.toString());

		RestTemplate restTemplate = restTemplateBuilder.build();

		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("USERDATARESEVATION-SERVICE", false);
		InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);

		String baseurl = info.getHomePageUrl();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<User> response = null;
		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

		System.out.println("The entity Body is :" + requestEntity.getBody());

		try {
			response = restTemplate.exchange(baseurl + "/userReserveion/edit-request-user", HttpMethod.POST,
					requestEntity, User.class);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println("The Services is OK ................. :" + response.getStatusCode());
		}
		return "welcomepage";
	}

	@RequestMapping(value = "/get-page-edit", method = RequestMethod.GET) ///////////////////////////// here the edit
																			///////////////////////////// page
	public String getUserEditPage(@RequestParam long id, HttpServletRequest request) {

		for (int i = 0; i < userPar.length; i++) {
			System.out.println("user :" + userPar[i]);
		}

		User userObj = ops.findUserById(userPar, id);

		request.setAttribute("user", userObj);

		return "updateuser";
	}

//////////////////////////////////Don't forgget to Edit this function 

//	@RequestMapping(value = "/del-usr", method = RequestMethod.GET)
//	public String delUser(long id) throws RestClientException, IOException {
//
//		RestTemplate restTemplate = restTemplateBuilder.build();
//
//		// InstanceInfo info =
//		// eurakClient.getNextServerFromEureka("USERDATARESEVATION-SERVICE", false);
//		InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
//
//		String baseurl = info.getHomePageUrl();
//
//		System.out.println("The User id is : ......." + id);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//		ResponseEntity<Long> response = null;
//		HttpEntity<Long> requestEntity = new HttpEntity<>(id, headers);
//
//		System.out.println("The entity Body is :" + requestEntity.getBody());
//
//		try {
//			response = restTemplate.exchange(baseurl + "/userReserveion/delete-request-usr", HttpMethod.POST,
//					requestEntity, Long.class);
//			System.out.println("The Response is : >>>>>>>>>>>>>>>>" + response.toString());
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//		if (response.getStatusCode() == HttpStatus.OK) {
//
//			System.out.println("The Services is OK ................. :" + response.getStatusCode());
//		} else {
//			System.out.println("Error ...............");
//		}
//
//		return "showalluser";
//	}

///////////////                 The Hotel Operation					///////////////

	@RequestMapping(value = "/get-hotel-port", method = RequestMethod.GET)
	@ResponseBody
	public String getHotelServicePort() {

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);

		String baseUrl = info.getHomePageUrl();

		hotelServicePort = info.getPort();

		System.out.println("The info Hotel is ........................... :" + baseUrl);

		return restTemplate.getForObject(baseUrl + "/hotelReserveion/location", String.class);

	}

	// Get All Hotel From DataBase
	@RequestMapping(value = "/get-all-hotel", method = RequestMethod.GET)
	public String getAllHotel(HttpServletRequest request) throws IOException {

		RestTemplate restTemplate = restTemplateBuilder.build();

		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		InstanceInfo info2 = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		String baseUrl = info2.getHomePageUrl();

		System.out.println("The baseurl :" + baseUrl);

		HttpEntity entity = new HttpEntity(getHeaders());

		Hotel[] hotel = new Hotel[500];

		System.out.println("The entity Body is :" + entity.getBody());

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl + "/hotelReserveion/get-request-hotel-show", HttpMethod.GET,
					entity, String.class);
			hotel = new Gson().fromJson(response.getBody(), Hotel[].class);
			System.out.println("The Response of hotel is : >>>>>>>>>>>>>>>>" + hotel[0].getName());
		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println("The Services is OK ................. :" + response.getStatusCode());
		}

		System.out.println(response.getBody());
		List<Hotel> hotels = new ArrayList<Hotel>();
		for (int k = 0; k < hotel.length; k++) {
			if (hotel[k] == null) {
				break;
			}
			hotels.add(hotel[k]);
		}

		request.setAttribute("hotels", hotels);
		return "showallhotel";
	}

	@RequestMapping(value = "/get-all-planes")
	public String getAllPlanes(@RequestParam String from, @RequestParam String to, @RequestParam String depart,
			@RequestParam String retturn, @RequestParam int child, @RequestParam int adult, @RequestParam String degree,
			HttpServletRequest request, HttpSession session) throws RestClientException, IOException, ParseException {

		System.out.println("get-all-planes degree " + degree);
		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info2 = eurakClient.getNextServerFromEureka("FLIGHTRESEVATION-SERVICE", false);
		String baseUrl = info2.getHomePageUrl();

		List<String> param = new ArrayList<String>();
		param.add(from);
		param.add(to);
		param.add(depart);
		param.add(retturn);
		param.add(degree);
	//	System.out.println("The degree is >>>>>"+degree);
		param.add(String.valueOf(child));
		param.add(String.valueOf(adult));
		int sum = child + adult;
		session.setAttribute("sum", sum);
		session.setAttribute("degree", degree);

	//	System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		Plane[] all = new Plane[200];
		Plane[] planes = new Plane[200];
		Plane[] planest = new Plane[200];

		HttpEntity<List<String>> requestEntity = new HttpEntity<>(param, headers);

		ResponseEntity<String> response = null;

		try {
			response = restTemplate.exchange(baseUrl + "/get-all-planes-new", HttpMethod.POST, requestEntity,
					String.class);
			all = new Gson().fromJson(response.getBody(), Plane[].class);
		} catch (Exception e) {
	//		System.out.println(e);
		}

		if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println("The Services is OK ................. :" + response.getStatusCode());
		}

		if (all.length != 0) {

			if (retturn.equals("")) {
				for (int i = 0; i < all.length; i++) {
					if (all[i] == null) {
						break;
					}
					planes[i] = all[i];
				
				}
				request.setAttribute("total", planes);
				request.setAttribute("degree", degree);
				session.setAttribute("showAllFlightsSession", planes);
	//			System.out.println("The degree is ...............؟؟"+degree);
				request.setAttribute("coord", new Coordinates(planes[0].getDesAirport()));
				return "showAllFlights";
			} else {
				int j = 0, k = 0;
				for (int i = 0; i < all.length; i++) {
					if (all[i] == null) {
						break;
					} else if (all[i].getLocation().equals(from)) {
						planes[j] = all[i];
						j++;
					} else if (all[i].getLocation().equals(to)) {
						planest[k] = all[i];
						k++;
					}
				}
				if (planest[0] == null) {
					request.setAttribute("total", planes);
					request.setAttribute("degree", degree);
			//		System.out.println("The degree is ..............."+degree);
					request.setAttribute("coord", new Coordinates(planes[0].getDesAirport()));
					session.setAttribute("showAllFlightsSession", planes);
					return "showAllFlights";
				}
				session.setAttribute("showAllFlightsSession2", planes);
				session.setAttribute("showAllReturnFlightsSession", planest);
				request.setAttribute("planes", planes);
				request.setAttribute("planest", planest);
				request.setAttribute("degree", degree);
			//	System.out.println("The degree is ............... !!"+degree);
				request.setAttribute("coord", new Coordinates(planes[0].getDesAirport()));
				return "showAllReturnFlights";
			}
		} else {
			return "noresult";
		}

	}
	///////////////////////////////////////////////////// here ///////////////////////////////////////////////////////
	
	@RequestMapping("/order-plane-by-price-pro")
	public String orderPlaneByPricePro(HttpServletRequest req, HttpServletResponse res, HttpServletRequest request) {

		Plane[] planes = new Plane[200];
		
		HttpSession s = req.getSession();
		planes =  (Plane[]) s.getAttribute("showAllFlightsSession");
		System.out.println("The All Hotels size is :.........." + planes.length);

		System.out.println("The Flight Before Sorting ...... :");

		for (int i = 0; i < planes.length; i++) {
			System.out.println(planes[i]);
		}
		
		List<Plane> list = Arrays.asList(planes);   //from array to arrayList

		
		Collections.sort(list, Plane.PlanePriceComparatorPro);

		System.out.println("The Hotel After Sorting ...... :");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		Plane[] planesArray = (Plane[]) list.toArray(); //from arrayList tp array
				
		System.out.println("The planesArray is .........."+planesArray.length);
		request.setAttribute("hotels", planesArray);

		return "showallhotel";
	}

	// Get all hotel from the API
	@RequestMapping(value = "/get-all-hotel-amd", method = RequestMethod.GET)
	@ResponseBody
	public String getAllHotelAPI(HttpServletRequest request) throws IOException {

		RestTemplate restTemplate = restTemplateBuilder.build();

		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
		String baseUrl = info.getHomePageUrl();

		System.out.println("The baseurl :" + baseUrl);

		HttpEntity entity = new HttpEntity(getHeaders());

		Hotel[] hotel = new Hotel[200];

		System.out.println("The entity Body is :" + entity.getBody());

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl + "/hotelReserveion/get-hotel-api", HttpMethod.GET, entity,
					String.class);
			System.out.println("The Response is " + response.getBody());
			hotel = new Gson().fromJson(response.getBody(), Hotel[].class);

			for (int i = 0; i < hotel.length; i++) {
				System.out.println("The Response of hotel is : >>>>>>>>>>>>>>>>" + hotel[i].getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println("The Services is OK ................. :" + response.getStatusCode());
		}

		System.out.println(response.getBody());

		request.setAttribute("hotels", hotel);
		return response.getBody();
	}

	@GetMapping("/index")
	public String IndexPage(HttpServletRequest request) {

		return "index";
	}
	////////////////// for login

	@GetMapping("/login-user")
	public String LoginUser(HttpServletRequest request) {
		return "loginPage";
	}

	///////////////////// for register

	@GetMapping("/register-user")
	public String registerUser() {
		return "register";
	}

	/////////////////////// for hotel

	@GetMapping("/search-hotel")
	public String SearchHotel(HttpServletRequest request) {
		return "search_hotel";
	}

	@RequestMapping(value = "/hotel-info", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getFallbackShowHotelInfo")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String ShowHotelInfo(@RequestParam int id, HttpServletRequest request, HttpSession session)
			throws IOException {

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		// InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER",
		// false);
		String baseUrl = info.getHomePageUrl();

		System.out.println("The baseurl :" + baseUrl);

		HttpEntity entity = new HttpEntity(getHeaders());

		System.out.println("id" + id);
		Hotel[] hotels = new Hotel[500];
		Hotel hotel = new Hotel();

		System.out.println("The entity Body is :" + entity.getBody());

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl + "/get-request-hotel-show", HttpMethod.GET, entity, String.class);
			hotels = new Gson().fromJson(response.getBody(), Hotel[].class);

			System.out.println("The Response of hotel is : >>>>>>>>>>>>>>>>" + hotels[0].getName());
		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println("The Services is OK ................. :" + response.getStatusCode());
		}

		System.out.println(response.getBody());

		for (int i = 0; i < hotels.length; i++) {
			if (hotels[i].getid() == id) {
				hotel = hotels[i];

				Set<SingleRoom> singleroom = new HashSet<SingleRoom>();
				singleroom = hotel.getSingleRoomList();
//
				Iterator<SingleRoom> it = singleroom.iterator();
				SingleRoom sroom = it.next();
				System.out.println("ssssssssssssssssssssssssssss" + sroom.toString());
//				System.out.println("ssssrrroommmmmmmmmm" + sroom.getUrl());
//
//				Set<DualRoom> dualroom = new HashSet<DualRoom>();
//				dualroom = hotel.getDualRoomList();
//
//				Iterator<DualRoom> itd = dualroom.iterator();
//				DualRoom droom = itd.next();
//				System.out.println("dddddddddddddddddddd" + droom.toString());
//				System.out.println("dddddrrroommmmmmmmmm" + droom.getUrl());
			}
		}

		System.out.println("heeeeeeeeeeeeerrrr" + id + "" + hotel);
		request.setAttribute("hotel", hotel);
		return "showHotelInfo";
	}

	public String getFallbackShowHotelInfo(@RequestParam int id, HttpServletRequest request, HttpSession session) {

		try {

			session = request.getSession();

			// Hotel[] hotels = new Hotel[500];

			List<Hotel> hotels = new ArrayList<Hotel>();

			Hotel hotel = new Hotel();

			hotels = (List<Hotel>) session.getAttribute("allHotels");

			if (hotels == null) {
				return "error6";
			} else {

				for (int i = 0; i < hotels.size(); i++) {
					if (hotels.get(i).getid() == id) {
						hotel = hotels.get(i);

						Set<SingleRoom> singleroom = new HashSet<SingleRoom>();
						singleroom = hotel.getSingleRoomList();
						Iterator<SingleRoom> it = singleroom.iterator();
						SingleRoom sroom = it.next();
						System.out.println("ssssssssssssssssssssssssssss" + sroom.toString());

					}
				}

				System.out.println("heeeeeeeeeeeeerrrr" + id + "" + hotel);
				request.setAttribute("hotel", hotel);
				return "showHotelInfo";

			}

		} catch (Exception e) {
			return "error6";
		}

	}

	@GetMapping("/paymentCall")
	public String paymentCall() {
		return "payment1";
	}

	@GetMapping("/receivePayment ")
	public String receivePaymentl() {
		return "index";
	}

	@GetMapping("/call_hotel_user")
	public String CallHotelUser(HttpServletRequest request) {

		return "addUser";
	}

	///////////////////////////////////// for flight

	@GetMapping("/search-flight")
	public String Searchflight(HttpServletRequest request) {
		return "search_flight";
	}

///////////////                 The Bank Request					///////////////

	@RequestMapping(value = "/after-payment", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getFallGetCheckBankInfo")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String getCheckBankInfo(@RequestParam String price, @RequestParam int hotel_id, @RequestParam int roomId,
			@RequestParam String roomType, @RequestParam String useremail, @RequestParam long accountId,
			@RequestParam String username, @RequestParam String password, @RequestParam String book,
			@RequestParam String leave,@RequestParam String city, HttpServletRequest request,HttpSession s) throws IOException, ParseException {

		System.out.println("The Username is ......"+username);
		System.out.println("The email is ......"+useremail);
		int n = 0;

		System.out.println("The Hotel Id is ....." + hotel_id);
		System.out.println("The book date is ....." + book);
		System.out.println("The Room id is :" + roomId);

		SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = formatter2.parse(book);
		Date date2 = formatter2.parse(leave);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		int dayOfMonth1 = cal.get(Calendar.DAY_OF_MONTH); // 17

		int dayOfMonth2 = cal2.get(Calendar.DAY_OF_MONTH);

		System.out.println("The dayOfMonth1 is ....... :" + dayOfMonth1);

		System.out.println("The dayOfMonth2 is ....... :" + dayOfMonth2);

		System.out.println("The Date1 day is .........................:" + dayOfMonth1);
		System.out.println("The Date2 day is .........................:" + dayOfMonth2);
		int num = dayOfMonth2 - dayOfMonth1;

		float p = Float.parseFloat(price);

		float totalPrice = Math.abs(p * num);

		String priceString = String.valueOf(totalPrice);

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("BANKSYSTEMRESEVATION-SERVICE", false);
		// InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER",
		// false);
		String baseUrl = info.getHomePageUrl();

		List<String> res = new ArrayList<String>();
		List<String> listInfo = new ArrayList<String>();
		listInfo.add(useremail);
		listInfo.add(password);
		listInfo.add(Long.toString(accountId));
		listInfo.add(priceString);

		System.out.println("The PriceString day is .........................:" + priceString);

		System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		UserBank userBank = new UserBank();

		try {
			// for request
			response = restTemplate.exchange(baseUrl + "/check-bank-account", HttpMethod.POST, requestEntity,
					String.class);
			// res = ArrayList.valueOf(response.getBody());
			// int answer =Integer.valueOf(response.getBody());

			res = new Gson().fromJson(response.getBody(), ArrayList.class);
			System.out.println("The Answer is ........" + res.toString());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return "stop"; ////////// here I need GUI
		} else if (response.getStatusCode() == HttpStatus.OK) {

			System.out.println("Value is ..... :" + Integer.valueOf(res.get(0)));

			if (Integer.valueOf(res.get(0)) != 0) {

				s = request.getSession();
				Users user = (Users) s.getAttribute("userLogin");

				UserHotel userHotel = new UserHotel();
				userHotel.setUserName(useremail);
				userHotel.setHotelId(hotel_id);
				userHotel.setRoomId(roomId);
				userHotel.setTotalCost(totalPrice);
				userHotel.setStartdate(date1);
				userHotel.setEndDate(date2);
				userHotel.setRoomType(roomType);
				userHotel.setAvalible(1);
				userHotel.setAccountId(accountId);
				userHotel.setCityName(city);

				System.out.println("The response is after: ......" + response.getBody());

				if (n == 0) {
					userHotelPost(userHotel);
				}

				n++;

				request.setAttribute("userbankinfo", userBank);
				return "index";
			} else {
				System.out.println("The Account not found ..............");
				return "noresult";
			}
		} else {

			System.out.println("The Services is OK ................. :" + response.getStatusCode());
			return "403";
		}
	}

	public String getFallGetCheckBankInfo(@RequestParam String price, @RequestParam int hotel_id, @RequestParam int roomId,
			@RequestParam String roomType, @RequestParam String useremail, @RequestParam long accountId,
			@RequestParam String username, @RequestParam String password, @RequestParam String book,
			@RequestParam String leave,@RequestParam String city, HttpServletRequest request,HttpSession s)
			throws IOException, ParseException {

		SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = formatter2.parse(book);
		Date date2 = formatter2.parse(leave);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		int dayOfMonth1 = cal.get(Calendar.DAY_OF_MONTH); // 17

		int dayOfMonth2 = cal2.get(Calendar.DAY_OF_MONTH);

		System.out.println("The dayOfMonth1 is ....... :" + dayOfMonth1);

		System.out.println("The dayOfMonth2 is ....... :" + dayOfMonth2);

		System.out.println("The Date1 day is .........................:" + dayOfMonth1);
		System.out.println("The Date2 day is .........................:" + dayOfMonth2);
		int num = dayOfMonth2 - dayOfMonth1;

		float p = Float.parseFloat(price);

		float totalPrice = Math.abs(p * num);

		UserHotel userHotel = new UserHotel();
		userHotel.setUserName(useremail);
		userHotel.setHotelId(hotel_id);
		userHotel.setRoomId(roomId);
		userHotel.setTotalCost(totalPrice);
		userHotel.setStartdate(date1);
		userHotel.setEndDate(date2);
		userHotel.setRoomType(roomType);
		userHotel.setAvalible(1);
		userHotel.setAccountId(accountId);
		userHotel.setCityName(city);

		String queueName = "userHotelPostQueue";
		String exchange = "direct-exchange";
		String routingKey = "userHotelPost";
		
		System.out.println("sadasdsadasdas");

		amqpTemplate.convertAndSend(exchange, routingKey, userHotel);

		// rabbitMQSender.sendUserHotelPost(userHotel);
		return "error6";

	}

	// for User Hotel Register in data base
	@GetMapping("/user-hotel-post")
	@HystrixCommand(fallbackMethod = "getFallbackUserHotelPost")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String userHotelPost(UserHotel userHotel) {

		UserHotel userHotel2 = new UserHotel();

		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response2 = null;
		HttpEntity<UserHotel> requestEntity2 = new HttpEntity<>(userHotel, headers2);

		// InstanceInfo info2 = eurakClient.getNextServerFromEureka("ZUUL-SERVER",
		// false);
		System.out.println("Hello, World");
		InstanceInfo info2 = eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		String baseUrl2 = info2.getHomePageUrl();

		List<String> listInfo2 = new ArrayList<String>();

		try {
			// for request
			int res2;
			response2 = restTemplate.exchange(baseUrl2 + "/save-user-hotel", HttpMethod.POST, requestEntity2,
					String.class);
			res2 = Integer.valueOf(response2.getBody());
			// int answer =Integer.valueOf(response.getBody());
			System.out.println("Modty ..............." + response2.getBody());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response2.getBody() == null) {
			System.out.println("The User Hotel  not Create ................. :" + response2.getStatusCode());
			return "403";
		} else if (response2.getStatusCode() == HttpStatus.OK) {
			return "index";
		} else {
			return "403";
		}

	}

	public String getFallbackUserHotelPost(UserHotel userHotel) {

		String queueName = "userHotelPostQueue";
		String exchange = "direct-exchange";
		String routingKey = "userHotelPost";

		amqpTemplate.convertAndSend(exchange, routingKey, userHotel);

//		rabbitMQSender.sendUserHotelPost(userHotel);
		return "error6";

	}

	@GetMapping("/get-search-page")
	public String getSearchPage() {

		System.out.println("getSearchPage Service is Called .......");
		return "search_hotel";
	}

	@RequestMapping("/search-hotel-city")
	@HystrixCommand(fallbackMethod = "getFallbackSearchHotel")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String searchHotel(@RequestParam String city, HttpServletRequest request, HttpSession session) {

		// String city = "LONDON -UK";
		System.out.println("/////////////////////////////////");
		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		// InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER",
		// false);
		String baseUrl = info.getHomePageUrl();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		List<String> res = new ArrayList<String>();
		List<String> listInfo = new ArrayList<String>();

		listInfo.add(city);

		Hotel[] hotel = new Hotel[1000];
		// Hotel hotel = new Hotel();

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		try {

			response = restTemplate.exchange(baseUrl + "/get-request-hotel-city", HttpMethod.POST, requestEntity,
					String.class);

			hotel = new Gson().fromJson(response.getBody(), Hotel[].class);
			System.out.println("The hotel size lenght is  ..............." + hotel.length);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			System.out.println("oooooooooooooooooooooo");

			return "stop"; ////////// here I need GUI
		} else if (response.getStatusCode() == HttpStatus.OK && hotel.length != 0) {

			System.out.println("qqqqqqqqqqqqqq");

			List<Hotel> hotels = new ArrayList<Hotel>();
			for (int k = 0; k < hotel.length; k++) {
				if (hotel[k] == null) {
					break;
				}
				hotels.add(hotel[k]);
			}
			session.setAttribute("allHotels", hotels);

			request.setAttribute("hotels", hotels);

			return "showallhotel";

		}

		else {
			System.out.println(".......................");
			return "noresult";
		}

	}

	@HystrixCommand(fallbackMethod = "getFallbackSearchHotelAmadues")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String getFallbackSearchHotel(@RequestParam String city, HttpServletRequest request, HttpSession session)
			throws ResponseException {
		Amadeus amadeus = Amadeus.builder("JTd1wtkpTSWdGJXdQjd62m3n1eHVIxF4", "GcQ8tPTuF5RnUcn0").build();

		// "PAR"
		// Get list of hotels by city code
		HotelOffer[] offers = amadeus.shopping.hotelOffers.get(Params.with("cityCode", city));

		if (offers[0].getResponse().getStatusCode() != 200) {
			System.out.println("Wrong status code: " + offers[0].getResponse().getStatusCode());
			System.exit(-1);
		}

		List<Hotel> hotels = new ArrayList<Hotel>();
		;
		System.out.println("88888888888888888 : " + offers[0].getHotel().getHotelDistance().getDistance());
		Offer[] offersHotel = offers[0].getOffers();
		System.out.println("88888888888888888 : " + offersHotel[0]);
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;; ababababa " + offers.length);
		int c = 0;
		for (int j = 0; j < offers.length; j++) {

			Hotel hotelObj = new Hotel();
			Contact contactObj = new Contact();
			AddressHotel addressObj = new AddressHotel();

			MediaURI[] mediaList = offers[j].getHotel().getMedia();

			addressObj.setAddress(offers[j].getHotel().getAddress().getCityName());
			addressObj.setCountryCode(offers[j].getHotel().getAddress().getCountryCode());

			contactObj.setFax(offers[j].getHotel().getContact().getFax());
			contactObj.setPhone(offers[j].getHotel().getContact().getPhone());
			contactObj.setEmail("www.nero98@gmail.com"); // *
			hotelObj.setType(offers[j].getHotel().getType());
			hotelObj.setName(offers[j].getHotel().getName());
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + offers[j].getHotel().getName());
			hotelObj.setRatting(offers[j].getHotel().getRating()); // *
			hotelObj.setCityCode(offers[j].getHotel().getCityCode());
			hotelObj.setLatitude((float) offers[j].getHotel().getLatitude());
			hotelObj.setLongtitude((float) offers[j].getHotel().getLongitude());
			hotelObj.setAddress(addressObj);
			hotelObj.setContact(contactObj);
			hotelObj.setDescription(offers[0].getHotel().getDescription().getText()); // here there is probleme
			String[] amenitiesList = new String[20];
			amenitiesList = offers[j].getHotel().getAmenities();
			for (int z = 0; z < amenitiesList.length; z++) {
				hotelObj.setAmenitiesIndex(amenitiesList[z]);
			}
			if (mediaList.length != 0) {
				for (int i = 0; i < mediaList.length; i++) {
					hotelObj.setMediaIndex(mediaList[i].getUri()); /// mistak
				}
			}
			hotelObj.setAvalibale(offers[j].isAvailable());
			System.out.println("/////////////////]]]]]]]]]]]]]]]]]]" + hotelObj.toString());
			System.out.println("++++++++++++++" + hotelObj.getName());
			hotels.add(hotelObj);

		}
		System.out.println("size: " + hotels.size());

		for (int i = 0; i < hotels.size(); i++) {
			System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL!!!!!!!!!!!!!!!!!!" + hotels.get(i));
		}

		request.setAttribute("hotels", hotels);
		return "showallhotel";

	}

	public String getFallbackSearchHotelAmadues(@RequestParam String city, HttpServletRequest request,
			HttpSession session) {

		try {

			List<Hotel> hotels = new ArrayList<Hotel>();

			session = request.getSession();

			hotels = (List<Hotel>) session.getAttribute("allHotels");

			if (hotels == null) {
				return "error6";
			} else {
				request.setAttribute("hotels", hotels);
				return "showallhotel";
			}

		} catch (Exception e) {
			return "error6";
		}

	}

	@RequestMapping("/order-hotel-by-rate-des")
	public String orderHotelByRateDes(HttpServletRequest req, HttpServletResponse res, HttpServletRequest request) {

		List<Hotel> hotels = new ArrayList<Hotel>();
		HttpSession s = req.getSession();
		hotels = (List<Hotel>) s.getAttribute("allHotels");
		System.out.println("The All Hotels size is :.........." + hotels.size());

		System.out.println("The Hotel Before Sorting ...... :");

		for (int i = 0; i < hotels.size(); i++) {
			System.out.println(hotels.get(i).getRatting());
		}

		Collections.sort(hotels, Hotel.HotelRattingComparatorDis);

		System.out.println("The Hotel After Sorting ...... :");

		for (int i = 0; i < hotels.size(); i++) {
			System.out.println(hotels.get(i).getRatting());
		}

		request.setAttribute("hotels", hotels);

		return "showallhotel";
	}

	@RequestMapping("/order-hotel-by-rate-pro")
	public String orderHotelByRatePro(HttpServletRequest req, HttpServletResponse res, HttpServletRequest request) {

		List<Hotel> hotels = new ArrayList<Hotel>();
		HttpSession s = req.getSession();
		hotels = (List<Hotel>) s.getAttribute("allHotels");
		System.out.println("The All Hotels size is :.........." + hotels.size());

		System.out.println("The Hotel Before Sorting ...... :");

		for (int i = 0; i < hotels.size(); i++) {
			System.out.println(hotels.get(i).getRatting());
		}

		Collections.sort(hotels, Hotel.HotelRattingComparatorPro);

		System.out.println("The Hotel After Sorting ...... :");

		for (int i = 0; i < hotels.size(); i++) {
			System.out.println(hotels.get(i).getRatting());
		}

		request.setAttribute("hotels", hotels);

		return "showallhotel";
	}

	@RequestMapping("/order-hotel-by-name")
	public String orderHotelByName(HttpServletRequest req, HttpServletResponse res, HttpServletRequest request) {

		List<Hotel> hotels = new ArrayList<Hotel>();
		HttpSession s = req.getSession();
		hotels = (List<Hotel>) s.getAttribute("allHotels");
		System.out.println("The All Hotels size is :.........." + hotels.size());

		System.out.println("The Hotel Before Sorting ...... :");

		for (int i = 0; i < hotels.size(); i++) {
			System.out.println(hotels.get(i).getRatting());
		}

		Collections.sort(hotels, Hotel.HotelNameComparator);

		System.out.println("The Hotel After Sorting ...... :");

		for (int i = 0; i < hotels.size(); i++) {
			System.out.println(hotels.get(i).getRatting());
		}

		request.setAttribute("hotels", hotels);

		return "showallhotel";
	}

	@RequestMapping("/order-hotel-by-price")
	public String orderHotelByPrice(HttpServletRequest req, HttpServletResponse res, HttpServletRequest request) {

		List<Hotel> hotels = new ArrayList<Hotel>();
		HttpSession s = req.getSession();

		hotels = (List<Hotel>) s.getAttribute("allHotels");
		System.out.println("The All Hotels size is :.........." + hotels.size());

		System.out.println("The Hotel Before Sorting ...... :");

		for (int j = 0; j < hotels.size(); j++) {

			ArrayList<SingleRoom> array = new ArrayList<SingleRoom>(hotels.get(j).getSingleRoomList());

			for (int i = 0; i < array.size(); i++) {

				System.out.println(array.get(i).getPrice());
			}

		}

		Collections.sort(hotels, Hotel.HotelPriceComparator);

		System.out.println("The Hotel After Sorting ...... :");

		for (int j = 0; j < hotels.size(); j++) {

			ArrayList<SingleRoom> array = new ArrayList<SingleRoom>(hotels.get(j).getSingleRoomList());

			for (int i = 0; i < array.size(); i++) {

				System.out.println(array.get(i).getPrice());
			}

		}
		request.setAttribute("hotels", hotels);

		return "showallhotel";
	}

	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public Users UserInfo(Principal principal, HttpServletRequest request, HttpSession session) {

		RestTemplate restTemplate = restTemplateBuilder.build();

		String nameUser = principal.getName();
		// String nameUser = "mohamad";

		InstanceInfo info = eurakClient.getNextServerFromEureka("USERDATARESEVATION-SERVICE", false);
		// InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER",
		// false);
		String baseUrl = info.getHomePageUrl();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		List<String> res = new ArrayList<String>();
		List<String> listInfo = new ArrayList<String>();

		listInfo.add(nameUser);

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		Users user = new Users();

		try {

			response = restTemplate.exchange(baseUrl + "/get-user-info", HttpMethod.POST, requestEntity, String.class);

			user = new Gson().fromJson(response.getBody(), Users.class);
			System.out.println("The User Come Info is :  ..............." + user.getEmail());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return null; ////////// here I need GUI
		} else {

			return user;
		}

	}

	// To get User Login Info UserPlane userPlane3 = (UserPlane)
	// s.getAttribute("userPlanePostOne");

	@RequestMapping(value = "/user-edit-page")
	@HystrixCommand(fallbackMethod = "getFallgetFallUserInfoPage")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String UserInfoPage(Principal principal, HttpServletRequest request, HttpSession session) {

		// System.out.println("The user name is:" + principal.getName());

		RestTemplate restTemplate = restTemplateBuilder.build();

		String nameUser = principal.getName();
		// String nameUser = "mohamad";

		InstanceInfo info = eurakClient.getNextServerFromEureka("USERDATARESEVATION-SERVICE", false);
		// InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER",
		// false);
		String baseUrl = info.getHomePageUrl();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		List<String> res = new ArrayList<String>();
		List<String> listInfo = new ArrayList<String>();

		listInfo.add(nameUser);

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		Users user = new Users();

		try {

			response = restTemplate.exchange(baseUrl + "/get-user-info", HttpMethod.POST, requestEntity, String.class);

			user = new Gson().fromJson(response.getBody(), Users.class);
			System.out.println("The User Come Info is :  ..............." + user.getEmail());

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());

			return "stop"; ////////// here I need GUI

		} else {

			session.setAttribute("userLogin", user);
			request.setAttribute("user", user);
			System.out.println("The session is ok ......");

			return "register2";
		}

	}

	public String getFallgetFallUserInfoPage(Principal principal, HttpServletRequest request, HttpSession session) {

		session = request.getSession();
		try {
			Users user = (Users) session.getAttribute("userLogin");

			if (user == null) {

				System.out.println("//////");
				return "error6";
			} else {

				System.out.println("??????");
				request.setAttribute("user", user);
				return "register2";
			}

		} catch (Exception e) {

			System.out.println("*********");
			return "error6";
		}

	}

	@RequestMapping(value = "/user-edit-page-set", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getFallUserSetEditInfo")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String UserSetEditInfo(@RequestParam String username, @RequestParam String lastname,
			@RequestParam String email, @RequestParam String phone, @RequestParam String password,
			HttpServletRequest request) {

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
		String baseUrl = info.getHomePageUrl();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		List<String> res = new ArrayList<String>();
		List<String> listInfo = new ArrayList<String>();

		listInfo.add(username);
		listInfo.add(lastname);
		listInfo.add(email);
		listInfo.add(phone);
		listInfo.add(password);

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		int check = 0;
		try {

			response = restTemplate.exchange(baseUrl + "/userReserveion/set-user-edit", HttpMethod.POST, requestEntity,
					String.class);

			check = new Gson().fromJson(response.getBody(), Integer.class);
			System.out.println("The User Edit is :  ..............." + check);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return "stop"; ////////// here I need GUI
		} else {

			return "index";
		}

	}

	public String getFallUserSetEditInfo(@RequestParam String username, @RequestParam String lastname,
			@RequestParam String email, @RequestParam String phone, @RequestParam String password,
			HttpServletRequest request) {

		Users user = new Users();

		user.setName(username);
		user.setLastName(lastname);
		user.setEmail(email);
		user.setPhoneNumber(phone);
		user.setPassword(password);

		String queueName = "UserSetEditInfoQueue";
		String exchange = "direct-exchange";
		String routingKey = "UserSetEditInfo";

		amqpTemplate.convertAndSend(exchange, routingKey, user);

		return "error2";

	}

	@RequestMapping("/send-email")
	@HystrixCommand(fallbackMethod = "getFallsendEmail")
	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	public String sendEmail(@RequestParam String Name, @RequestParam String Email, @RequestParam String subject,
			@RequestParam String message) throws MessagingException {

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("ZUUL-SERVER", false);
		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("USERDATARESEVATION-SERVICE", false);
		String baseUrl = info.getHomePageUrl();

		List<String> mail = new ArrayList<String>();
		mail.add(Email);
		mail.add(Name);
		mail.add(subject);
		mail.add(message);

		System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(mail, headers);

		UserBank userBank = new UserBank();

		try {
			// for request
			response = restTemplate.exchange(baseUrl + "/userReserveion/add-email", HttpMethod.POST, requestEntity,
					String.class);

			int res;
			res = new Gson().fromJson(response.getBody(), Integer.class);

		} catch (Exception e) {
			System.out.println(e);
		}

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("reemjomaa20@gmail.com");
		email.setFrom(Email);
		email.setSubject(subject);
		email.setText(message);

		javaMailSender.send(email);

		return "index";

	}
	
	public String getFallsendEmail(@RequestParam String Name, @RequestParam String Email, @RequestParam String subject,
			@RequestParam String message) {
		System.out.println("getFallsendEmail is called");
		
		return "error6";
	}

	@GetMapping("/get-singel-room")
	@ResponseBody
	public List<SingleRoom> getAllSingelRoom(long hotelId) {

		// long hotelId = 1;
		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		String baseUrl = info.getHomePageUrl();

		List<String> listInfo = new ArrayList<String>();
		listInfo.add(Long.toString(hotelId));

		System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		ArrayList<SingleRoom> listRoom = new ArrayList<SingleRoom>();
		SingleRoom singleRoomList[] = new SingleRoom[500];

		try {
			System.out.println("Befoooooooooore ///////");
			response = restTemplate.exchange(baseUrl + "/get-singleroom-price", HttpMethod.GET, requestEntity,
					String.class);
			System.out.println("Afterrrrrrrrrrrr ///////");
			singleRoomList = new Gson().fromJson(response.getBody(), SingleRoom[].class);
			System.out.println("The Number of Room is :" + singleRoomList.length);
		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return null; ////////// here I need GUI
		} else {
			System.out.println("The response is : ......" + response.getBody());
			Collections.addAll(listRoom, singleRoomList);
			// listRoom = (ArrayList<SingleRoom>) Arrays.asList(singleRoomList);
			System.out.println("The Size of list  is : ......" + listRoom.size());
			return listRoom;

		}

	}

	@GetMapping("/get-dual-room")
	@ResponseBody
	public List<DualRoom> getAllDualRoom(long hotelId) {

		// long hotelId = 1;
		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		String baseUrl = info.getHomePageUrl();

		List<String> listInfo = new ArrayList<String>();
		listInfo.add(Long.toString(hotelId));

		System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		ArrayList<DualRoom> listRoom = new ArrayList<DualRoom>();
		DualRoom dualRoomList[] = new DualRoom[500];

		try {
			System.out.println("Befoooooooooore ///////");
			response = restTemplate.exchange(baseUrl + "/get-dualeroom-price", HttpMethod.POST, requestEntity,
					String.class);
			System.out.println("After ///////");
			dualRoomList = new Gson().fromJson(response.getBody(), DualRoom[].class);
			// System.out.println("The Number of Room is :"+dualRoomList.length);
		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return null; ////////// here I need GUI
		} else {
			System.out.println("The response is : ......" + response.getBody());
			listRoom = (ArrayList<DualRoom>) Arrays.asList(dualRoomList);
			return listRoom;

		}

	}

	@GetMapping("/get-suit-room")
	@ResponseBody
	public List<SuitRoom> getAllSuitRoom(long hotelId) {

		// long hotelId = 1;
		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		String baseUrl = info.getHomePageUrl();

		List<String> listInfo = new ArrayList<String>();
		listInfo.add(Long.toString(hotelId));

		System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		ArrayList<SuitRoom> listRoom = new ArrayList<SuitRoom>();
		SuitRoom singleRoomList[] = new SuitRoom[500];

		try {
			response = restTemplate.exchange(baseUrl + "/get-suitroom-price", HttpMethod.POST, requestEntity,
					String.class);
			singleRoomList = new Gson().fromJson(response.getBody(), SuitRoom[].class);
			System.out.println("The Number of Room is :" + singleRoomList.length);
		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return null; ////////// here I need GUI
		} else {
			System.out.println("The response is : ......" + response.getBody());
			listRoom = (ArrayList<SuitRoom>) Arrays.asList(singleRoomList);
			return listRoom;

		}

	}

	@RequestMapping(value = "/get-check-info")
	public String getCheckBankInfo(@RequestParam String price, @RequestParam String useremail,
			@RequestParam long accountId, @RequestParam String password, HttpServletRequest request)
			throws IOException {

		float p = Float.parseFloat(price);

		RestTemplate restTemplate = restTemplateBuilder.build();

		// InstanceInfo info =
		// eurakClient.getNextServerFromEureka("HOTELRESEVATION-SERVICE", false);
		InstanceInfo info = eurakClient.getNextServerFromEureka("BANKSYSTEMRESEVATION-SERVICE", false);
		String baseUrl = info.getHomePageUrl();

		List<String> listInfo = new ArrayList<String>();
		listInfo.add(useremail);
		listInfo.add(password);
		listInfo.add(Long.toString(accountId));
		listInfo.add(price);

		System.out.println("The baseurl :" + baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		UserBank userBank = new UserBank();

		try {
			response = restTemplate.exchange(baseUrl + "/check-bank-account", HttpMethod.POST, requestEntity,
					String.class);
			int answer = Integer.valueOf(response.getBody());
			// userBank = new Gson().fromJson(response.getBody(), UserBank.class);
			// System.out.println("The current Bulunce is :"+userBank.getCurrentbalance());
			System.out.println("reem ..............." + answer);

			// System.out.println("The Response of hotel is : >>>>>>>>>>>>>>>>"
			// +response.getBody().toString());
			// System.out.println("The Response of hotel is : >>>>>>>>>>>>>>>>"
			// +userBank.getCurrentbalance());
		} catch (Exception e) {
//			System.out.println("respnse is :"+response.getStatusCode());
			System.out.println(e);
		}

//		HttpStatus code = response.getStatusCode();
		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return "stop"; ////////// here I need GUI
		} else if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("The response is : ......" + response.getBody());
			request.setAttribute("userbankinfo", userBank); ////////// here I need GUI
			return "index";

		} else {

			System.out.println("The Services is OK ................. :" + response.getStatusCode());
			return "403";
		}

	}

	@RequestMapping(value = "/report-user-hotel", method = RequestMethod.GET)
	public ModelAndView userListReport(HttpServletRequest req, HttpServletResponse res, HttpSession s) {

		String typeReport = req.getParameter("type");

		s = req.getSession();

		// List of User for test
		List<UserHotel> list = new ArrayList<UserHotel>();
		UserHotel[] userHotels = new UserHotel[500];
		userHotels = (UserHotel[]) s.getAttribute("userHotelExport");
		list = Arrays.asList(userHotels);

		if (typeReport != null && typeReport.equals("xls")) {

			return new ModelAndView(new ExcelListReportUserHotelView(), "userHotelList", list);
		} else if (typeReport != null && typeReport.equals("pdf")) {

			return new ModelAndView(new PDFListReportUserHotelView(), "userHotelList", list);
		}

		return new ModelAndView("userListReport", "userHotelList", list);
	}

	@RequestMapping(value = "/report-user-plane", method = RequestMethod.GET)
	public ModelAndView userListReportRole(HttpServletRequest req, HttpServletResponse res, HttpSession s) {

		String typeReport = req.getParameter("type");

		s = req.getSession();

		// List of User for test
		List<UserPlane> list = new ArrayList<UserPlane>();
		UserPlane[] userPlanes = new UserPlane[500];
		userPlanes = (UserPlane[]) s.getAttribute("userPlaneExport");
		list = Arrays.asList(userPlanes);

		if (typeReport != null && typeReport.equals("xls")) {

			return new ModelAndView(new ExcelListReportUserPlaneView(), "userPlaneList", list);
		} else if (typeReport != null && typeReport.equals("pdf")) {

			return new ModelAndView(new PDFListReportUserPlaneView(), "userPlaneList", list);
		}

		return new ModelAndView("userListReport", "userHotelList", list);
	}

//	@RequestMapping(value="/get-check-info")
//	public String getCheckBankInfo(@RequestParam String price,@RequestParam String hotel_id,@RequestParam String useremail,@RequestParam long accountId
//			, @RequestParam String password,
//			 HttpServletRequest request) throws IOException {
//
//		System.out.println("The Hotel Id is ....."+hotel_id);
////		List<SingleRoom> singel = new ArrayList<SingleRoom>();
////		List<SuitRoom> suit = new ArrayList<SuitRoom>();
////		List<DualRoom> dual = new ArrayList<DualRoom>();
////		singel = getAllSingelRoom(Long.valueOf(hotel_id));
//	//	suit = getAllSuitRoom(Long.valueOf(hotel_id));
//	//	dual = getAllDualRoom(Long.valueOf(hotel_id));
////		for(int i =0;i<singel.size();i++) {
////			System.out.println("The singel Id is ....."+singel.get(i).getPrice());
////		}
////		
////		System.out.println("The suit Id is ....."+singel.size());
//	//	System.out.println("The dual Id is ....."+dual.size());
//		float p=Float.parseFloat(price);
//		
//		RestTemplate restTemplate = restTemplateBuilder.build();
//
//		InstanceInfo info = eurakClient.getNextServerFromEureka("BANKSYSTEMRESEVATION-SERVICE", false);
//		String baseUrl = info.getHomePageUrl();
//		
//		List<String> listInfo = new ArrayList<String>();
//		listInfo.add(useremail);
//		listInfo.add(password);
//		listInfo.add(Long.toString(accountId));
//		listInfo.add(price);
//		
//		System.out.println("The baseurl :" + baseUrl);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//		ResponseEntity<String> response = null;
//		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);
//
//		
//
//		UserBank userBank = new UserBank();
//	
//		try {
//			response = restTemplate.exchange(baseUrl + "/check-bank-account", HttpMethod.POST,
//					requestEntity, String.class);
//			int answer =Integer.valueOf(response.getBody());
//	//		userBank = new Gson().fromJson(response.getBody(), UserBank.class);
//	//		System.out.println("The current Bulunce is :"+userBank.getCurrentbalance());
//			System.out.println("reem ..............."+answer);
//
//	//		System.out.println("The Response of hotel is : >>>>>>>>>>>>>>>>" +response.getBody().toString());
//	//		System.out.println("The Response of hotel is : >>>>>>>>>>>>>>>>" +userBank.getCurrentbalance());
//		} catch (Exception e) {
////			System.out.println("respnse is :"+response.getStatusCode());
//			System.out.println(e);
//		}
//		
////		HttpStatus code = response.getStatusCode();
//		if(response.getBody() == null) {
//			System.out.println("The User not found ................. :" + response.getStatusCode());
//			return "stop";  //////////here I need GUI 
//		}
//		else if(response.getStatusCode() == HttpStatus.OK){
//			System.out.println("The response is : ......"+response.getBody());
//			request.setAttribute("userbankinfo", userBank);   //////////here I need GUI 
//			return "index";
//			
//		}
//		else {
//
//			System.out.println("The Services is OK ................. :" + response.getStatusCode());
//			return "403";
//		}
//		
//				
//		
//	}

///////////////                 The Header Request					///////////////

	public static HttpEntity<?> getHeaders() throws IOException { // here I put the properties of the header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		// headers.set("Accept",MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
