package com.zeon.HotelReservation.Repository;





import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zeon.HotelReservation.Modal.UserHotel;

@Repository
public interface UserHotelRepository extends CrudRepository<UserHotel,Long>{

	
	//public User findUserByName(String firstname);	
	public UserHotel findUserHotelById(long id);	
	
	public List<UserHotel> findUserHotelByUserName(String userName);
	
	
	public void deleteUserHotelById(long id);
	
	/*
	// @Query("select u from Patient u where firstname=?")
	//public List<Patient> findAllPatientByFirst(String firstname);
	 
	 @Query("select u from Patient u where u.firstname=?1")
	    List<Patient> getAllUserByFirstName(String firstname);
	
	public List<Patient> findByFirstname(String firstname);*/

}
