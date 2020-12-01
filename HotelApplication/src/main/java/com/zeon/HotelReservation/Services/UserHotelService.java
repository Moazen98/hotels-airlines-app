package com.zeon.HotelReservation.Services;




import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeon.HotelReservation.Modal.UserHotel;
import com.zeon.HotelReservation.Repository.UserHotelRepository;


@Service
@Transactional
public class UserHotelService{
	
	@Autowired
	private final UserHotelRepository userRepository;
	
	public UserHotelService(UserHotelRepository userRepository) {
		this.userRepository = userRepository;
	}
	

	public void saveUser(UserHotel user) {
		System.out.println("//////////////");
		userRepository.save(user); /////////////////////////////////here error
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
	}
	
	
	public void deleteUser(long id) {
		UserHotel user=new UserHotel();
		//user =(UserHotel)userRepository.findUserHotelById(id);
		userRepository.deleteUserHotelById(id);
	}	
	
	
	@Transactional
	public List<UserHotel> FindAllUsers(){
		
		List<UserHotel> users = new ArrayList<UserHotel>();
		for (UserHotel user : userRepository.findAll()) {
			users.add(user);
			
		}	
		return users;	
	}
	
	@Transactional
	public List<UserHotel> getAllUserHotel(String userName){
		
		List<UserHotel> userHotel = new ArrayList<UserHotel>();
		
		userHotel = userRepository.findUserHotelByUserName(userName);
		
		return userHotel;
	}
	
	
	
	
	@Transactional
	public UserHotel canceledReserve(long id) {
		UserHotel uh = new UserHotel();
		uh=findByIdUser(id);
		uh.setCanceled(true);
		saveUser(uh);
		return uh;
	}
	
	@Transactional
	public List<UserHotel> getAllUserHotel(){
		
		List<UserHotel> userHotel = new ArrayList<UserHotel>();
		
		for (UserHotel user : userRepository.findAll()) {
			if(user.isCanceled()==true&&user.getAvalible()==1)
			{
				userHotel.add(user);
			}
		}
		return userHotel;
	}
	

	
	@Transactional
	public List<UserHotel> getAllUserHotelReserved(String userName){
		
		List<UserHotel> userHotel = new ArrayList<UserHotel>();
		List<UserHotel> res = new ArrayList<UserHotel>();
		userHotel = userRepository.findUserHotelByUserName(userName);
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date test = Date.from(now.toInstant(ZoneOffset.UTC));
		
		
		
		for (int i = 0; i < userHotel.size(); i++) {
			Date myDate = userHotel.get(i).getStartdate();
			if(userHotel.get(i).getAvalible()==1&& test.before(myDate))
			{
				res.add(userHotel.get(i));
			}
		}
		
		return res;
	}
	
	
	@Transactional
	public void setZeroAvailable(long id) {
		UserHotel uh = new UserHotel();
		uh=findByIdUser(id);
		uh.setAvalible(0);
		saveUser(uh);
	}
	
	@Transactional
	public UserHotel findByIdUser(long id) {
		UserHotel user=new UserHotel();
		user =(UserHotel)userRepository.findUserHotelById(id);
		return user;	
	}

}
