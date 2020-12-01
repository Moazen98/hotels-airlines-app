package com.zeon.UserPortable.Model;


import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airplan")
public class AirPlan {
	
	@Id
	private long id;
	private int number_of_Economy_seats;
	private int number_of_Perimume_Economy_seats;
	private int number_of_Business_seats;
	private int number_of_First_Class_seats;
	
	
	public AirPlan() {
		
	}


	public AirPlan(long id, int number_of_Economy_seats, int number_of_Perimume_Economy_seats,
			int number_of_Business_seats, int number_of_First_Class_seats) {
		super();
		this.id = id;
		this.number_of_Economy_seats = number_of_Economy_seats;
		this.number_of_Perimume_Economy_seats = number_of_Perimume_Economy_seats;
		this.number_of_Business_seats = number_of_Business_seats;
		this.number_of_First_Class_seats = number_of_First_Class_seats;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getNumber_of_Economy_seats() {
		return number_of_Economy_seats;
	}


	public void setNumber_of_Economy_seats(int number_of_Economy_seats) {
		this.number_of_Economy_seats = number_of_Economy_seats;
	}


	public int getNumber_of_Perimume_Economy_seats() {
		return number_of_Perimume_Economy_seats;
	}


	public void setNumber_of_Perimume_Economy_seats(int number_of_Perimume_Economy_seats) {
		this.number_of_Perimume_Economy_seats = number_of_Perimume_Economy_seats;
	}


	public int getNumber_of_Business_seats() {
		return number_of_Business_seats;
	}


	public void setNumber_of_Business_seats(int number_of_Business_seats) {
		this.number_of_Business_seats = number_of_Business_seats;
	}


	public int getNumber_of_First_Class_seats() {
		return number_of_First_Class_seats;
	}


	public void setNumber_of_First_Class_seats(int number_of_First_Class_seats) {
		this.number_of_First_Class_seats = number_of_First_Class_seats;
	}

	

}
