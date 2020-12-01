package com.zeon.HotelReservation.Services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zeon.HotelReservation.Modal.DualRoom;
import com.zeon.HotelReservation.Modal.Hotel;
import com.zeon.HotelReservation.Modal.SingleRoom;
import com.zeon.HotelReservation.Modal.SuitRoom;
import com.zeon.HotelReservation.Modal.UserHotel;
import com.zeon.HotelReservation.Repository.HotelRepository;

@Service
@Transactional
public class HotelService {

	@Autowired
	private final HotelRepository hotelRepository;

	@Autowired
	UserHotelService userService;

	public HotelService(HotelRepository hotelRepository) {

		this.hotelRepository = hotelRepository;
	}

	public int SetRoomAvalible(long id, int roomId, String type, Boolean bool) {

		Hotel hotel = new Hotel();
		int check = 0;
		System.out.println("The type is :" + type);
		System.out.println("The roomId is :" + roomId);

		if (type.equals("single")) {

			System.out.println("The Single Room .............");
			int roomWantId = getExistSingelRoom(id, roomId);
			hotel = hotelRepository.findHotelById(id);
			Set<SingleRoom> roomList = new HashSet<SingleRoom>();
			roomList = getAllSingelRoom(id);
			List<SingleRoom> roomList2 = new ArrayList<SingleRoom>(roomList);

			System.out.println("The room id is :" + roomId);
			System.out.println("The Hotel id is :" + id);
			SingleRoom room = new SingleRoom();
			room = roomList2.get(roomId);
			System.out.println("The Room id after bring it is :" + roomList2.get(roomWantId).getId());
			roomList2.get(roomWantId).setReserved(bool);
			System.out.println("The Room Reserved is after :" + roomList2.get(roomWantId).getReserved());
			Set<SingleRoom> hSet = new HashSet<SingleRoom>(roomList2);
			hotel.setSingleRoomList(hSet);
			hotelRepository.save(hotel);
			System.out.println("After save new ...............");

			check = 1;

		} else if (type.equals("dual")) {

			System.out.println("The dual Room .............");
			int roomWantId = getExistDualRoom(id, roomId);
			hotel = hotelRepository.findHotelById(id);
			Set<DualRoom> roomList = new HashSet<DualRoom>();

			List<DualRoom> roomList2 = new ArrayList<DualRoom>(roomList);
			roomList2 = getAlldualRoom(id);

			System.out.println("The room id is :" + roomId);
			System.out.println("The Hotel id is :" + id);
			DualRoom room = new DualRoom();
			room = roomList2.get(roomId);
			System.out.println("The Room id after bring it is :" + roomList2.get(roomWantId).getId());
			roomList2.get(roomWantId).setReserved(bool);
			System.out.println("The Room Reserved is after :" + roomList2.get(roomWantId).getReserved());
			Set<DualRoom> hSet = new HashSet<DualRoom>(roomList2);
			hotel.setDualRoomList(hSet);
			hotelRepository.save(hotel);
			System.out.println("After save new ...............");
			check = 1;

		} else if (type.equals("suit")) {

			System.out.println("The suit Room .............");

			int roomWantId = getExistSuitRoom(id, roomId);
			hotel = hotelRepository.findHotelById(id);
			Set<SuitRoom> roomList = new HashSet<SuitRoom>();

			List<SuitRoom> roomList2 = new ArrayList<SuitRoom>(roomList);
			roomList2 = getAllSuitRoom(id);

			System.out.println("The room id is :" + roomId);
			System.out.println("The Hotel id is :" + id);
			SuitRoom room = new SuitRoom();
			room = roomList2.get(roomId);
			System.out.println("The Room id after bring it is :" + roomList2.get(roomWantId).getId());
			roomList2.get(roomWantId).setReserved(bool);
			System.out.println("The Room Reserved is after :" + roomList2.get(roomWantId).getReserved());
			Set<SuitRoom> hSet = new HashSet<SuitRoom>(roomList2);
			hotel.setSuitRoomList(hSet);
			hotelRepository.save(hotel);
			System.out.println("After save new ...............");

			check = 1;

		} else {
			check = 0;
		}

		return check;

	}

	public Set<SingleRoom> getAllSingelRoom(long id) {

		Hotel hotel = new Hotel();
		hotel = hotelRepository.findHotelById(id);
		System.out.println("The Id hotel :" + hotel.getid());
		// List<SingleRoom> roomList = new ArrayList<SingleRoom>(); //Convert Set to
		// ArrayList
		// roomList.addAll(hotel.getSingleRoomList());
		Set<SingleRoom> roomList = new HashSet<SingleRoom>();
		roomList = hotel.getSingleRoomList();
		System.out.println("The Serveice list size is :" + roomList.size());
		// roomList = (List<SingleRoom>) hotel.getSingleRoomList();
		return roomList;

	}

	public int getExistSingelRoom(long id, int roomId) {

		int myId = 0;
		Hotel hotel = new Hotel();
		hotel = hotelRepository.findHotelById(id);
		System.out.println("The Id hotel :" + hotel.getid());
		// List<SingleRoom> roomList = new ArrayList<SingleRoom>(); //Convert Set to
		// ArrayList
		// roomList.addAll(hotel.getSingleRoomList());
		Set<SingleRoom> roomList = new HashSet<SingleRoom>();
		roomList = hotel.getSingleRoomList();
		List<SingleRoom> roomList2 = new ArrayList<SingleRoom>(roomList);
		System.out.println("qqqqqqqqqqqqqqqqqqqq");
		System.out.println("the size is :" + roomList2.size());
		for (int i = 0; i < roomList2.size(); i++) {

			System.out.println("The id .......... :" + roomList2.get(i).getId());
			if (roomList2.get(i).getId() == roomId) {
				myId = i;
			}
		}
		System.out.println("The id Come is :" + roomId);
		System.out.println("The id in database is :" + myId);
		System.out.println("The Serveice list size is :" + roomList.size());
		// roomList = (List<SingleRoom>) hotel.getSingleRoomList();
		return myId;

	}

	public List<DualRoom> getAlldualRoom(long id) {

		Hotel hotel = new Hotel();
		hotel = hotelRepository.findHotelById(id);
		List<DualRoom> roomList = new ArrayList<DualRoom>(hotel.getDualRoomList());
		// roomList = (List<DualRoom>) hotel.getDualRoomList();
		return roomList;

	}

	public int getExistDualRoom(long id, int roomId) {

		int myId = 0;
		Hotel hotel = new Hotel();
		hotel = hotelRepository.findHotelById(id);
		System.out.println("The Id hotel :" + hotel.getid());
		// List<SingleRoom> roomList = new ArrayList<SingleRoom>(); //Convert Set to
		// ArrayList
		// roomList.addAll(hotel.getSingleRoomList());
		Set<DualRoom> roomList = new HashSet<DualRoom>();
		roomList = hotel.getDualRoomList();
		List<DualRoom> roomList2 = new ArrayList<DualRoom>(roomList);
		System.out.println("qqqqqqqqqqqqqqqqqqqq");
		System.out.println("the size is :" + roomList2.size());
		for (int i = 0; i < roomList2.size(); i++) {

			System.out.println("The id .......... :" + roomList2.get(i).getId());
			if (roomList2.get(i).getId() == roomId) {
				myId = i;
			}
		}
		System.out.println("The id Come is :" + roomId);
		System.out.println("The id in database is :" + myId);
		System.out.println("The Serveice list size is :" + roomList.size());
		// roomList = (List<SingleRoom>) hotel.getSingleRoomList();
		return myId;

	}

	public List<SuitRoom> getAllSuitRoom(long id) {

		Hotel hotel = new Hotel();
		hotel = hotelRepository.findHotelById(id);
		List<SuitRoom> roomList = new ArrayList<SuitRoom>(hotel.getSuitRoomList());
		// roomList = (List<SuitRoom>) hotel.getSuitRoomList();
		return roomList;

	}

	public int getExistSuitRoom(long id, int roomId) {

		int myId = 0;
		Hotel hotel = new Hotel();
		hotel = hotelRepository.findHotelById(id);
		System.out.println("The Id hotel :" + hotel.getid());
		// List<SingleRoom> roomList = new ArrayList<SingleRoom>(); //Convert Set to
		// ArrayList
		// roomList.addAll(hotel.getSingleRoomList());
		Set<SuitRoom> roomList = new HashSet<SuitRoom>();
		roomList = hotel.getSuitRoomList();
		List<SuitRoom> roomList2 = new ArrayList<SuitRoom>(roomList);
		System.out.println("qqqqqqqqqqqqqqqqqqqq");
		System.out.println("the size is :" + roomList2.size());
		for (int i = 0; i < roomList2.size(); i++) {

			System.out.println("The id .......... :" + roomList2.get(i).getId());
			if (roomList2.get(i).getId() == roomId) {
				myId = i;
			}
		}
		System.out.println("The id Come is :" + roomId);
		System.out.println("The id in database is :" + myId);
		System.out.println("The Serveice list size is :" + roomList.size());
		// roomList = (List<SingleRoom>) hotel.getSingleRoomList();
		return myId;

	}

	public void saveHotel(Hotel hotel) {
		hotelRepository.save(hotel);
	}

	public void saveHotel2(List<Hotel> hotel) {
		hotelRepository.saveAll(hotel);
	}

	public void deleteHotel(long id) {
		Hotel hotell = new Hotel();
		hotell = (Hotel) hotelRepository.findHotelById(id);
		hotelRepository.delete(hotell);
	}

	@Transactional
	public List<Hotel> FindAllHotels() {

		List<Hotel> hotels = new ArrayList<Hotel>();
		for (Hotel hotell : hotelRepository.findAll()) {
			hotels.add(hotell);

		}
		return hotels;
	}

	@Transactional
	public Set<Hotel> FindAllHotels2() {

		Set<Hotel> hotels = new HashSet<Hotel>();
		for (Hotel hotell : hotelRepository.findAll()) {
			hotels.add(hotell);

		}
		return hotels;
	}

	@Transactional
	public Hotel findByIdhotel(long id) {

		System.out.println("service 1111111111");
		Hotel hotel = new Hotel();
		hotel = (Hotel) hotelRepository.findHotelById(id);
		return hotel;

	}

	@Transactional
	public List<Hotel> findHotelByCityName(String address) {

		List<Hotel> hotels = new ArrayList<Hotel>();
		for (Hotel hotell : hotelRepository.findHotelByAddressAddress(address)) {
			hotels.add(hotell);

		}
		return hotels;
	}

	public void CheckAllRoomRes() {
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

		hotelList = (ArrayList<Hotel>) hotelRepository.findAll();

		for (int i = 0; i < hotelList.size(); i++) {

			Hotel hotel = findByIdhotel(hotelList.get(i).getid());

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			int year = LocalDateTime.now().getYear();
			int month = LocalDateTime.now().getMonth().getValue();
			int day = LocalDateTime.now().getDayOfMonth();

			System.out.println("The Year is............... :" + year);
			System.out.println("The Month is............... :" + month);
			System.out.println("The Day is............... :" + day);

			System.out.println("The Time now :" + dtf.format(now));

			Set<DualRoom> roomListDual = new HashSet<DualRoom>();
			List<DualRoom> roomList2 = new ArrayList<DualRoom>(roomListDual);

			Set<SuitRoom> roomListSuit = new HashSet<SuitRoom>();
			List<SuitRoom> roomList3 = new ArrayList<SuitRoom>(roomListSuit);

			Set<SingleRoom> roomListSingle = new HashSet<SingleRoom>();
			List<SingleRoom> roomList4 = new ArrayList<SingleRoom>(roomListSingle);

//		roomList2 = getAlldualRoom(hotelId);  //here if you put it the database will increase I don't know why
//		roomList3 = getAllSuitRoom(hotelId);
//		roomListSingle = getAllSingelRoom(hotelId);

			List<UserHotel> userHotels = userService.FindAllUsers();

			System.out.println("The userHotel Size is : ..............." + userHotels.size());

			for (int j = 0; j < userHotels.size(); j++) {

				System.out.println("The data is .............." + userHotels.get(i).getEndDate());
				Date myDate = userHotels.get(i).getEndDate();

//			int roomYear = myDate.compareTo(arg0)
//			int roomMonth = myDate.getMonth();
//			int roomDay = myDate.getDay();
//			System.out.println("The Date is............... :"+userHotels.get(i).getEndDate().getDate());
//			System.out.println("The roomYear is............... :"+roomYear);
//			System.out.println("The roomMonth is............... :"+roomMonth);
//			System.out.println("The roomDay is............... :"+roomDay);

				System.out.println("Before The condition ...............");
				Date test = Date.from(now.toInstant(ZoneOffset.UTC));
				int n = myDate.compareTo(test);

				if (myDate.before(test)) {
					UserHotel user = new UserHotel();
					user = userHotels.get(i);
					System.out.println("The new user is 1:" + user.getUserName());
					System.out.println("The new user is 2:" + user.getRoomId());
					long idReserve = user.getId();
					int roomId = (int) user.getRoomId();
					String typeRoom = user.getRoomType();
					user.setAvalible(0);
					// userService.deleteUser(idReserve);
					userService.saveUser(user);
					SetRoomAvalible(hotelList.get(i).getid(), roomId, typeRoom, false);
					System.out.println("The End of Process ...............");
				}

			}

		}

	}
	
	
	public void CheckAllRoomResById(long hotelId) {

		Hotel hotel = findByIdhotel(hotelId);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		int year = LocalDateTime.now().getYear();
		int month = LocalDateTime.now().getMonth().getValue();
		int day = LocalDateTime.now().getDayOfMonth();

		
		System.out.println("The Year is............... :"+year);
		System.out.println("The Month is............... :"+month);
		System.out.println("The Day is............... :"+day);
		
		System.out.println("The Time now :" + dtf.format(now));

		Set<DualRoom> roomListDual = new HashSet<DualRoom>();
		List<DualRoom> roomList2 = new ArrayList<DualRoom>(roomListDual);

		Set<SuitRoom> roomListSuit = new HashSet<SuitRoom>();
		List<SuitRoom> roomList3 = new ArrayList<SuitRoom>(roomListSuit);

		Set<SingleRoom> roomListSingle = new HashSet<SingleRoom>();
		List<SingleRoom> roomList4 = new ArrayList<SingleRoom>(roomListSingle);

//		roomList2 = getAlldualRoom(hotelId);  //here if you put it the database will increase I don't know why
//		roomList3 = getAllSuitRoom(hotelId);
//		roomListSingle = getAllSingelRoom(hotelId);

		List<UserHotel> userHotels = userService.FindAllUsers();

		System.out.println("The userHotel Size is : ..............."+userHotels.size());
		
		for (int i = 0; i < userHotels.size(); i++) {

			System.out.println("The data is .............."+userHotels.get(i).getEndDate());
			Date myDate = userHotels.get(i).getEndDate();
			
//			int roomYear = myDate.compareTo(arg0)
//			int roomMonth = myDate.getMonth();
//			int roomDay = myDate.getDay();
//			System.out.println("The Date is............... :"+userHotels.get(i).getEndDate().getDate());
//			System.out.println("The roomYear is............... :"+roomYear);
//			System.out.println("The roomMonth is............... :"+roomMonth);
//			System.out.println("The roomDay is............... :"+roomDay);

			System.out.println("Before The condition ...............");
			Date test = Date.from(now.toInstant(ZoneOffset.UTC));
			int n = myDate.compareTo(test);
			
			if (myDate.before(test)) {
				UserHotel user = new UserHotel();
				user = userHotels.get(i);
				System.out.println("The new user is 1:"+user.getUserName());
				System.out.println("The new user is 2:"+user.getRoomId());
				long idReserve = user.getId();
				int roomId = (int)user.getRoomId();
				String typeRoom = user.getRoomType();
				user.setAvalible(0);
				//userService.deleteUser(idReserve);
				userService.saveUser(user);
				SetRoomAvalible(hotelId,roomId,typeRoom,false);
				System.out.println("The End of Process ...............");
			}

		}

	}


	@Transactional
	public Hotel findHotelByHotelName(String name) {

		Hotel hotel = new Hotel();
		hotel = (Hotel) hotelRepository.findHotelByName(name);
		return hotel;
	}

	@Component
	class DataUpdaterScheduled {

		@Scheduled(cron = "* 20 * * * ?")
		public void cronJobSch() {
			int exceptionCount = 0;
		//	ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

		//	hotelList = (ArrayList<Hotel>) hotelRepository.findAll();

			System.out.println("Before Scheduled ......");

			System.out.println("befor");
			//for (int i = 0; i < hotelList.size(); i++) {
				try {

					CheckAllRoomRes();

				} catch (Exception e) {
					exceptionCount++;
				}

		//	}

			System.out.println("After Scheduled .......");
			System.out.println("Exception Count=" + exceptionCount);
		}
	}

}
