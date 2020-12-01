package com.zeon.HotelReservation.Repository;


import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zeon.HotelReservation.Modal.Hotel;
import com.zeon.HotelReservation.Modal.SingleRoom;




@Repository
public interface HotelRepository extends CrudRepository<Hotel,Long>{

	
	public Hotel findHotelByName(String name);	
	public Hotel findHotelById(long id);	
	public List<Hotel> findHotelByAddressAddress(String address);
	
	
	/*
	// @Query("select u from Patient u where firstname=?")
	//public List<Patient> findAllPatientByFirst(String firstname);
	 
	 @Query("select u from Patient u where u.firstname=?1")
	    List<Patient> getAllUserByFirstName(String firstname);
	
	public List<Patient> findByFirstname(String firstname);*/

}
