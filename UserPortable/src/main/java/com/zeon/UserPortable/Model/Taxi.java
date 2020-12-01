package com.zeon.UserPortable.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Taxi {

	private long id;
	private boolean available;
	private String city;
	private String country;
	private String startTime;
	private String endTime;
	private String carModel;
	private String firstName;
	private String lastName;
	private String carVIN;
	private String phoneNumber;

	public Taxi() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCarVIN() {
		return carVIN;
	}

	public void setCarVIN(String carVIN) {
		this.carVIN = carVIN;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	
	
	@Override
	public String toString() {
		return "Taxi [available=" + available + ", city=" + city + ", country=" + country + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", carModel=" + carModel + ", firstName=" + firstName + ", lastName="
				+ lastName + ", carVIN=" + carVIN + ", phoneNumber=" + phoneNumber + "]";
	}

}
