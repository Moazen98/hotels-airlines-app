package com.zeon.TaxiApplication.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.zeon.TaxiApplication.Model.UserTaxi;
import com.zeon.TaxiApplication.Repository.UserTaxiRepository;

@Service
public class UserTaxiService {

	private final UserTaxiRepository userTaxiRepo;

	public UserTaxiService(UserTaxiRepository userTaxiRepo) {

		this.userTaxiRepo = userTaxiRepo;
	}

	@Transactional
	public void addUserTaxi(UserTaxi user) {
		userTaxiRepo.save(user);
	}

	@Transactional
	public void deleteUserTaxi(long id) {
		userTaxiRepo.deleteById(id);
	}

	@Transactional
	public List<UserTaxi> getAllUserTaxi() {

		return (List<UserTaxi>) userTaxiRepo.findAll();
	}

	@Transactional
	public UserTaxi getUserTaxiByEmailAndCity(String email,String city){
		
		ArrayList<UserTaxi> users = new ArrayList<UserTaxi>();
		System.out.println("ooooooooooooooooooooooooooo");
		
		for (UserTaxi user : userTaxiRepo.findUserTaxiByEmail(email)) {
			System.out.println("ssssssssssssssssssssss");
			
			System.out.println("The TaxiUser is ...."+user.toString());
			if(user.getCity().equals(city)) {
				System.out.println("LLLLLLLLLLLLLLLLLLLLLL");
				users.add(user);
			}
		}
		
		UserTaxi userNeed = new UserTaxi();
		userNeed = users.get(0);
		
		return userNeed;
	}

	@Transactional
	public List<UserTaxi> getAllUserTaxi2() {

		List<UserTaxi> userTaxi = new ArrayList<UserTaxi>();
		for (UserTaxi user : userTaxiRepo.findAll()) {
			userTaxi.add(user);
		}

		return userTaxi;
	}

}
