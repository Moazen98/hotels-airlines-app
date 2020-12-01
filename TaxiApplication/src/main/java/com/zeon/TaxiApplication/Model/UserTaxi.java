package com.zeon.TaxiApplication.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "usertaxi")
public class UserTaxi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String email;
	private String phone;
	private int userId;
	private long taxiId;
	private long dateReq;
	private int avalible;
	String city;
	private int timeTravle;

	public UserTaxi() {

	}

	public UserTaxi(long id, String name, String email, String phone, int userId, long taxiId, long dateReq,
			int avalible, String city, int timeTravle) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.userId = userId;
		this.taxiId = taxiId;
		this.dateReq = dateReq;
		this.avalible = avalible;
		this.city = city;
		this.timeTravle = timeTravle;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getTaxiId() {
		return taxiId;
	}

	public void setTaxiId(long taxiId) {
		this.taxiId = taxiId;
	}

	public long getDateReq() {
		return dateReq;
	}

	public void setDateReq(long dateReq) {
		this.dateReq = dateReq;
	}

	public int getAvalible() {
		return avalible;
	}

	public void setAvalible(int avalible) {
		this.avalible = avalible;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTimeTravle() {
		return timeTravle;
	}

	public void setTimeTravle(int timeTravle) {
		this.timeTravle = timeTravle;
	}

	@Override
	public String toString() {
		return "UserTaxi [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", userId=" + userId
				+ ", taxiId=" + taxiId + ", dateReq=" + dateReq + ", avalible=" + avalible + ", city=" + city
				+ ", timeTravle=" + timeTravle + "]";
	}

}
