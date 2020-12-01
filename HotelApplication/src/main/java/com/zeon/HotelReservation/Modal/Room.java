package com.zeon.HotelReservation.Modal;

import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Room {

	private String type;
	private String category;
	@Lob
	private String description;
	private int adult;
	private float price; //if you want type currency
	private Boolean reserved;
	@Temporal(TemporalType.DATE)
	private Date checkIn;
	@Temporal(TemporalType.DATE)
	private Date checkOut;
	
	
	
	public Room(String type, String category, String description, int adult, float price, Boolean reserved,
			Date checkIn, Date checkOut) {
		super();
		this.type = type;
		this.category = category;
		this.description = description;
		this.adult = adult;
		this.price = price;
		this.reserved = reserved;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	
	
}
