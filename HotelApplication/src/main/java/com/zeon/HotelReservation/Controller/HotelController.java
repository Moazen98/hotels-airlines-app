package com.zeon.HotelReservation.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bouncycastle.math.Primes.STOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.HotelOffer.MediaURI;
import com.amadeus.resources.HotelOffer.Offer;
import com.google.gson.Gson;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.zeon.HotelReservation.Modal.AddressHotel;
import com.zeon.HotelReservation.Modal.Contact;
import com.zeon.HotelReservation.Modal.DualRoom;
import com.zeon.HotelReservation.Modal.Hotel;
import com.zeon.HotelReservation.Modal.Room;
import com.zeon.HotelReservation.Modal.SingleRoom;
import com.zeon.HotelReservation.Modal.SuitRoom;
import com.zeon.HotelReservation.Modal.UserHotel;
import com.zeon.HotelReservation.Services.HotelService;
import com.zeon.HotelReservation.Services.UserHotelService;

@Controller
public class HotelController {
	

	@Autowired
	HotelService ps;

	@Autowired
	EurekaClient eurakClient;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Autowired
	EurekaDiscoveryClient dicovery;

	@Autowired
	UserHotelService us;

	@GetMapping("/home")
	public String homePage(HttpServletRequest request) {
		return "welcomepage";
	}

	@GetMapping("/register-hotel")
	public String registerPage() {
		return "Register";
	}

	@GetMapping("/test")
	public String loginPage() {
		return "loginUn";
	}

	// description probleme
	// media problame
	@RequestMapping("/amd-hotel-post")
	public String getAmdHotel() throws ResponseException {
		Amadeus amadeus = Amadeus.builder("JTd1wtkpTSWdGJXdQjd62m3n1eHVIxF4", "GcQ8tPTuF5RnUcn0").build();

		// Get list of hotels by city code
		HotelOffer[] offers = amadeus.shopping.hotelOffers.get(Params.with("cityCode", "PAR"));

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
			// hotelObj.setRoomList(offers[0].getOffers());
			System.out.println("/////////////////]]]]]]]]]]]]]]]]]]" + hotelObj.toString());
			System.out.println("++++++++++++++" + hotelObj.getName());
			hotels.add(hotelObj);
//			ps.saveHotel(hotelObj);

		}
		System.out.println("size: " + hotels.size());

		for (int i = 0; i < hotels.size(); i++) {
			System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL!!!!!!!!!!!!!!!!!!" + hotels.get(i));
		}

//		for (int i = 0; i < hotels.size(); i++) {
//	//		ps.saveHotel(hotels.get(i));
//			System.out.println("takilaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		}

		return hotels.toString();
	}

	@RequestMapping("/get-hotel-api")
	@ResponseBody
	public ResponseEntity<List<Hotel>> hotelApi() {

		List<Hotel> hotelsget = new ArrayList<Hotel>();
		hotelsget = ps.FindAllHotels();

		for (int i = 0; i < hotelsget.size(); i++) {
			System.out.println("ggggggggggggggggggggggggggggggggggggg" + hotelsget.get(i));
		}
		System.out.println(":::::::::::::::::::::::::" + hotelsget);
		if (hotelsget == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity(hotelsget, HttpStatus.OK);
		}

	}

	@GetMapping("/get-singleroom-price")
	@ResponseBody
	public ResponseEntity<Set<SingleRoom>> getAllSingleRoomByPrice(@RequestBody List<String> listInfo) {

		Set<SingleRoom> roomList = new HashSet<SingleRoom>();
		System.out.println("The size is :" + listInfo.get(0));
		roomList = ps.getAllSingelRoom(Long.valueOf(listInfo.get(0)));

//		for (int i = 0; i < roomList.size(); i++) {
//			System.out.println("The Room List is :" + roomList.get(i));
//		}
		if (roomList == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity(roomList, HttpStatus.OK);
		}

	}

	@RequestMapping("/get-dualeroom-price")
	@ResponseBody
	public ResponseEntity<List<DualRoom>> getAllDualRoomByPrice(@RequestBody List<String> listInfo) {

		List<DualRoom> roomList = new ArrayList<DualRoom>();
		roomList = ps.getAlldualRoom(Long.valueOf(listInfo.get(0)));

		for (int i = 0; i < roomList.size(); i++) {
			System.out.println("The Room List is :" + roomList.get(i));
		}
		if (roomList == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity(roomList, HttpStatus.OK);
		}

	}

	@RequestMapping("/get-suitroom-price")
	@ResponseBody
	public ResponseEntity<List<SuitRoom>> getAllSuitRoomByPrice(@RequestBody List<String> listInfo) {

		List<SuitRoom> roomList = new ArrayList<SuitRoom>();
		roomList = ps.getAllSuitRoom(Long.valueOf(listInfo.get(0)));

		for (int i = 0; i < roomList.size(); i++) {
			System.out.println("The Room List is :" + roomList.get(i));
		}
		if (roomList == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity(roomList, HttpStatus.OK);
		}

	}

	@PostMapping("/save-hotel")
	public String addHotel(@ModelAttribute Hotel hotel, BindingResult bindingResult, HttpServletRequest request) {

		ps.saveHotel(hotel);
		return "welcomepage";
	}

	@GetMapping("/get-all-hotel")
	public String getAllHotel(HttpServletRequest request) {

		request.setAttribute("hotels", ps.FindAllHotels());
		return "showallhotel";
	}

	@GetMapping("/delete-hotel")
	public String deletepatient(@RequestParam int id, HttpServletRequest request) {

		ps.deleteHotel(id);

		request.setAttribute("hotels", ps.FindAllHotels());
		return "showallusers";
	}

	@GetMapping("/edit-hotel")
	public String editpatient(@RequestParam int id, HttpServletRequest request) {

		request.setAttribute("hotel", ps.findByIdhotel(id));
		return "updatehotel";
	}

	@RequestMapping("/save-user-hotel")
	@ResponseBody
	public ResponseEntity<Integer> saveUserHotel(@RequestBody UserHotel userHotel) {

		ps.CheckAllRoomResById(userHotel.getHotelId());

		int res = 0;
		System.out.println("The User Hotel Details is :" + userHotel.getUserName());
		us.saveUser(userHotel);
		ps.SetRoomAvalible(userHotel.getHotelId(), (int) userHotel.getRoomId(), userHotel.getRoomType(), true);

		if (userHotel == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			res = 1;
			return new ResponseEntity(res, HttpStatus.OK);
		}

	}

	@RequestMapping("/get-date")
	@ResponseBody
	public String getDateNow() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return "dsssss";
	}

	@RequestMapping
	public String CheckFinishResverdRoom(long hotelId) {

		return "Asdasd";
	}

	// here I get ALL User Reserve
	@RequestMapping("/get-all-reserved-request")
	@ResponseBody
	public String getAllReserved() {

		List<UserHotel> userHotel = new ArrayList<UserHotel>();
		userHotel = us.FindAllUsers();
		System.out.println("getAllReserved was called .......");
		return userHotel.toString();
	}

	// here I get Specific User Reserve
	@RequestMapping("/get-all-reserved-user")
	@ResponseBody
	public ResponseEntity<List<UserHotel>> getAllUserReserved(@RequestBody List<String> param) {

		String userName = param.get(0);
		List<UserHotel> userHotel = new ArrayList<UserHotel>();
		System.out.println("Abbababa .......");
		userHotel = us.getAllUserHotelReserved(userName);
		System.out.println("Amamama .......");

		if (userHotel == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity(userHotel, HttpStatus.OK);
		}
	}

//	@PostMapping("/save-user")
//	public String addUser(@ModelAttribute HotelUser user, BindingResult bindingResult, HttpServletRequest request)
//			throws ParseException {
//
//		us.saveUser(user);
//
////		HotelUser u =new HotelUser();
////		u=(HotelUser) us.findByIdUser(1);
////		
////		
////		SimpleDateFormat format =new SimpleDateFormat("YY,MM,dd", Locale.ENGLISH);
////		String string = u.getLast_day();
////		
////		Date date = format.parse(string);
////		System.out.println(date);
//		return "welcomepage";
//	}

//	@PostMapping("/save-user")
//	public String addUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
//        
//		
//		
//		
//		 Hotel hotel=new Hotel();
//	     
//	     hotel=(Hotel)request.getSession().getAttribute("hotel");
//		
//	    int start =user.getStart_day();
//	    int end=start+user.getNumber_of_days();
//		user.setLast_day(end);
//		user.setHotel_id(hotel.getId());
//		String type=user.getRoom_type();
//		if(type.equals("single"))
//		{
//			int cost=user.getNumber_of_days()*hotel.getPrice_of_personal_room();
//			user.setTotalcost(cost);
//			user.setNumberofperson(1);
//			hotel.setNumber_of_room_for_own_person_booking(hotel.getNumber_of_room_for_own_person_booking()+1);
//			ps.saveHotel(hotel);
//			
//		}else if(type.equals("dual"))
//		{
//			int cost=user.getNumber_of_days()*hotel.getPrice_of_dual_room();
//			user.setTotalcost(cost);
//			user.setNumberofperson(2);
//			hotel.setNumber_of_room_for_two_person_booking(hotel.getNumber_of_room_for_two_person_booking()+1);
//			ps.saveHotel(hotel);
//		}
//		else
//		{
//			
//			int cost=user.getNumber_of_days()*hotel.getPrice_of_threeperson_room();
//			user.setTotalcost(cost);
//			user.setNumberofperson(3);
//			hotel.setNumber_of_room_for_three_person_booking(hotel.getNumber_of_room_for_three_person_booking()+1);
//			ps.saveHotel(hotel);
//		}
//		
//		
//		
//		us.saveUser(user);
//		return "welcomepage";
//	}
//	

	@GetMapping(value = "/get-request-hotel-show")
	@ResponseBody
	public ResponseEntity<List<Hotel>> getRequestHotelShow() {

		System.out.println("getHotelRequest Service was called ..........");

		List<Hotel> hotels = ps.FindAllHotels();

		if (hotels == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity(hotels, HttpStatus.OK);
		}

	}

	@PostMapping(value = "/get-request-hotel-city")
	@ResponseBody
	public ResponseEntity<List<Hotel>> getRequestHotelCity(@RequestBody List<String> city) {

		System.out.println("getRequestHotelCity Service was called ..........");

		String address = city.get(0);

		List<Hotel> hotels = ps.findHotelByCityName(address);

		System.out.println("After ..........");

		if (hotels == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity(hotels, HttpStatus.OK);
		}

	}

	@RequestMapping("/create-rooma")
	public String createrooma() {
		System.out.println("here in creat room 1");
		List<Hotel> hotelsget = new ArrayList<Hotel>();
		hotelsget = ps.FindAllHotels();

		String desc1 = "This room contains one bed, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";
		String desc2 = "This room contains one large bed, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";
		String desc3 = "This room contains three beds, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";

		///////////////////////// single room 1
		SingleRoom sr1 = new SingleRoom(desc1, 1, (float) 40.9, false);

		Set<SingleRoom> singleRoomList1 = new HashSet<SingleRoom>();
		for (int i = 0; i < 8; i++) {
			System.out.println("s1");
			singleRoomList1.add(new SingleRoom(i, desc1, "static/img/EiffelCapitol4.jpg", 1, (float) 40.9, false));
		}

		///////////////////////////////////////////////////////////////////////////

		/////////////////////////////// dual room 1
		DualRoom dr1 = new DualRoom(desc2, 2, (float) 60.4, false);
		Set<DualRoom> dualRoomList1 = new HashSet<DualRoom>();
		for (int i = 0; i < 11; i++) {
			System.out.println("d1");
			dualRoomList1.add(new DualRoom(i, desc2, "static/img/EiffelCapitol4.jpg", 2, (float) 60.4, false));
		}
		/////////////////////////////////////////////////////////

		///////////////////////////// suit room 1
		SuitRoom sur1 = new SuitRoom(desc3, 5, (float) 70.1, false);
		Set<SuitRoom> suitRoomList1 = new HashSet<SuitRoom>();
		for (int i = 0; i < 7; i++) {
			System.out.println("su1");
			suitRoomList1.add(new SuitRoom(i, desc3, "static/img/EiffelCapitol4.jpg", 5, (float) 70.1, false));
		}

		List<Hotel> hotels = new ArrayList<Hotel>();

		Hotel hotel = new Hotel();

		for (int i = 0; i < 181;i++) {

		//	if (i % 3 == 0) {
				hotel = hotelsget.get(i);
				hotel.setSingleRoomList(singleRoomList1);
				hotel.setDualRoomList(dualRoomList1);
				hotel.setSuitRoomList(suitRoomList1);
				hotels.add(hotel);
				
		//	}
		
			
			// hotels.add(hotel);

		}
		// ps.saveHotel(hotel);
		ps.saveHotel2(hotels);

		return "welcomepage";

	}

	@RequestMapping("/create-roomb")
	public String createroomb() {

		List<Hotel> hotelsget = new ArrayList<Hotel>();
		hotelsget = ps.FindAllHotels();

		String desc1 = "This room contains one bed, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";
		String desc2 = "This room contains one large bed, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";
		String desc3 = "This room contains three beds, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";

		////////////////////////// single room 2
		SingleRoom sr2 = new SingleRoom(desc1, 1, (float) 30.5, false);
		Set<SingleRoom> singleRoomList2 = new HashSet<SingleRoom>();
		for (int i = 0; i < 6; i++) {
			System.out.println("s2");
			singleRoomList2.add(new SingleRoom(i + 1, desc1, "static/img/EiffelCapitol4.jpg", 1, (float) 30.5, false));
		}

		///////////////////////////////////////////////////////////////////////////

		///////////////////////////// dual room 2
		DualRoom dr2 = new DualRoom(desc2, 2, (float) 42.1, false);
		Set<DualRoom> dualRoomList2 = new HashSet<DualRoom>();
		for (int i = 0; i < 12; i++) {
			System.out.println("d2");
			dualRoomList2.add(new DualRoom(i + 1, desc2, "static/img/EiffelCapitol4.jpg", 2, (float) 42.1, false));
		}

		/////////////////////////////////////////////////////////

		///////////////////////////// suit room 2
		SuitRoom sur2 = new SuitRoom(desc3, 5, (float) 59, false);
		Set<SuitRoom> suitRoomList2 = new HashSet<SuitRoom>();
		for (int i = 0; i < 8; i++) {
			System.out.println("su2");
			suitRoomList2.add(new SuitRoom(i + 1, desc3, "static/img/EiffelCapitol4.jpg", 5, (float) 59, false));
		}

		List<Hotel> hotels = new ArrayList<Hotel>();

		Hotel hotel = new Hotel();

		for (int i = 0; i < 181; i = i++) {

			if (i % 3 == 1) {
				hotel = hotelsget.get(i);
				hotel.setSingleRoomList(singleRoomList2);
				hotel.setDualRoomList(dualRoomList2);
				hotel.setSuitRoomList(suitRoomList2);
				hotels.add(hotel);
				System.out.println("......................");
			}
			
			System.out.println("ccccccccccccccccccccccc");
		}
		ps.saveHotel2(hotels);
		return "welcomepage";

	}

	@RequestMapping("/create-roomc")
	public String createroomc() {

		List<Hotel> hotelsget = new ArrayList<Hotel>();
		hotelsget = ps.FindAllHotels();

		String desc1 = "This room contains one bed, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";
		String desc2 = "This room contains one large bed, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";
		String desc3 = "This room contains three beds, and also contains a lot of luxurious and comfortable furniture and all amenities for guests, and the visitor can also enjoy the beautiful views and the natural scenery.\r\n"
				+ "";

		////////////////////////// single room 3
		SingleRoom sr3 = new SingleRoom(desc1, 1, (float) 42.3, false);
		Set<SingleRoom> singleRoomList3 = new HashSet<SingleRoom>();
		for (int i = 0; i < 9; i++) {
			System.out.println("s3");
			singleRoomList3.add(new SingleRoom(i + 1, desc1, "static/img/EiffelCapitol4.jpg", 1, (float) 42.3, false));
		}

		///////////////////////////// dual room 3
		DualRoom dr3 = new DualRoom(desc2, 2, (float) 55.2, false);
		Set<DualRoom> dualRoomList3 = new HashSet<DualRoom>();
		for (int i = 0; i < 9; i++) {
			System.out.println("d3");
			dualRoomList3.add(new DualRoom(i + 1, desc2, "static/img/EiffelCapitol4.jpg", 2, (float) 55.2, false));
		}

		///////////////////////////// suit room 1
		SuitRoom sur1 = new SuitRoom(desc3, 5, (float) 70.1, false);
		Set<SuitRoom> suitRoomList1 = new HashSet<SuitRoom>();
		for (int i = 0; i < 7; i++) {
			System.out.println("su1");
			suitRoomList1.add(new SuitRoom(i + 1, desc3, "static/img/EiffelCapitol4.jpg", 5, (float) 70.1, false));
		}

		///////////////////////////// suit room 3
		SuitRoom sur3 = new SuitRoom(desc3, 5, (float) 63.7, false);
		Set<SuitRoom> suitRoomList3 = new HashSet<SuitRoom>();
		for (int i = 0; i < 10; i++) {
			System.out.println("su3");
			suitRoomList3.add(new SuitRoom(i + 1, desc3, "static/img/EiffelCapitol4.jpg", 5, (float) 63.7, false));
		}

		List<Hotel> hotels = new ArrayList<Hotel>();

		Hotel hotel = new Hotel();

		for (int i = 0; i < 181; i = i++) {

			if (i % 3 == 2) {
				hotel = hotelsget.get(i);
				hotel.setSingleRoomList(singleRoomList3);
				hotel.setDualRoomList(dualRoomList3);
				hotel.setSuitRoomList(suitRoomList3);
				hotels.add(hotel);
			}

		}
		ps.saveHotel2(hotels);
		return "welcomepage";

	}
	@RequestMapping("/canceled-reserved")
	@ResponseBody
	public ResponseEntity<Integer> canceledReserved(@RequestBody Long param, HttpServletRequest request,
			HttpSession session) {
		UserHotel uh = new UserHotel();
		System.out.println("beeeeefor canceled .............");
		uh = us.canceledReserve(param);
		System.out.println("afffffter canceled .............");
//		session.setAttribute("userHotel", uh);
		System.out.println("The user is .........." + uh.getUserName());
		System.out.println("The user is .........." + uh.getEndDate());
		return new ResponseEntity(1, HttpStatus.OK);

	}

	@GetMapping("/get-all-request")
	public String getAllCancelRequest(HttpServletRequest request) {

		List<UserHotel> userHotel = new ArrayList<UserHotel>();
		userHotel = us.getAllUserHotel();

		request.setAttribute("users", userHotel);
		return "showallcancelrequest";
	}

	@RequestMapping("/set-zero")
	public String setAvailableZero(@RequestParam int id, HttpServletRequest request) {
//		HttpSession s = request.getSession();
		UserHotel uh = us.findByIdUser(id);
		System.out.println("The user is .........." + uh.getUserName());
		System.out.println("The user is .........." + uh.getEndDate());
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");


		Date date1 = uh.getStartdate();	// Fri Jun 17 14:54:28 PDT 2016
		Date date2 = uh.getEndDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		
		int dayOfMonth1 = cal.get(Calendar.DAY_OF_MONTH); // 17
		
		int dayOfMonth2 = cal2.get(Calendar.DAY_OF_MONTH);
		
		System.out.println("The dayOfMonth1 is ....... :"+dayOfMonth1);
		
		System.out.println("The dayOfMonth2 is ....... :"+dayOfMonth2);


		int dayNum = dayOfMonth2 - dayOfMonth1;

		float priceoOriginal = uh.getTotalCost() / dayNum;
		float totalValue = uh.getTotalCost();
		
		System.out.println("The priceoOriginal is ....... :"+priceoOriginal);
		System.out.println("The totalValue is ....... :"+totalValue);
		

		RestTemplate restTemplate = restTemplateBuilder.build();

		InstanceInfo info = eurakClient.getNextServerFromEureka("BANKSYSTEMRESEVATION-SERVICE", false);
		String baseUrl = info.getHomePageUrl();

		String useremail = uh.getUserName();
		String priceString = String.valueOf(totalValue);

		int res = 0;
		List<String> listInfo = new ArrayList<String>();
		listInfo.add(useremail);
		listInfo.add(priceString); // The Price Total of the Client
		listInfo.add(String.valueOf(priceoOriginal)); // The price Orginal og the Room
		listInfo.add(String.valueOf(uh.getAccountId()));
		// listInfo.add()

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<String> response = null;
		HttpEntity<List<String>> requestEntity = new HttpEntity<>(listInfo, headers);

		try {
			// for request
			System.out.println("ABABABABABABAA...........");
			response = restTemplate.exchange(baseUrl + "/discount-account", HttpMethod.POST, requestEntity,
					String.class);
			System.out.println("AMAMAMAMAMAMAM...........");
			// res = ArrayList.valueOf(response.getBody());
			int answer = Integer.valueOf(response.getBody());

			res = Integer.valueOf(response.getBody());
			System.out.println("The res answer is : ..............." + res);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (response.getBody() == null) {
			System.out.println("The User not found ................. :" + response.getStatusCode());
			return "stop"; ////////// here I need GUI
		} else if (response.getStatusCode() == HttpStatus.OK) {

			us.setZeroAvailable(id);
			ps.SetRoomAvalible(uh.getHotelId(), (int) uh.getRoomId(), uh.getRoomType(),false);
			
			return "welcomepage";

		} else {
			return "welcomepage";
		}

	}

}
