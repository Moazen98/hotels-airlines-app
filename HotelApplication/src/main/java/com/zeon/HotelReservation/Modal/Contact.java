package com.zeon.HotelReservation.Modal;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {

	private String phone;
	private String fax;
	private String email;
	
	
	public Contact() {

	}
	
	public Contact(String phone, String fax, String email) {
		super();
		this.phone = phone;
		this.fax = fax;
		this.email = "www.nero98@gmail.com";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setEmailStatic() {
		this.email = "www.nero98@gmail.com";
	}

	@Override
	public String toString() {
		return "Contact [phone=" + phone + ", fax=" + fax + ", email=" + email + "]";
	}
	
	
	
	
	
}
