package com.zeon.TaxiApplication.Repository;

import java.util.ArrayList;
import java.util.List; 

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zeon.TaxiApplication.Model.Taxi;

@Repository
public interface TaxiRepository extends CrudRepository<Taxi,Long>{

	public ArrayList<Taxi> findTaxiByCity(String city);
	
	public Taxi findTaxiById(long Id);
}
