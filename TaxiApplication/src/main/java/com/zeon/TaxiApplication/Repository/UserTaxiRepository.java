package com.zeon.TaxiApplication.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zeon.TaxiApplication.Model.UserTaxi;


@Repository
public interface UserTaxiRepository extends CrudRepository<UserTaxi,Long>{
	

	public ArrayList<UserTaxi> findUserTaxiByEmail(String email);

}
