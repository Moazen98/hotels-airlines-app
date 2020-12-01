package com.zeon.UserPortable.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class User {
	
	private long id;
	private String username;
	private String firstname;
	private String lastname;
	private int age;
	private String password;
	private boolean enabled;
	
	
	private int version;

	private String email;
	private int accepted;

	private String role;

	private String phone;

	public User() {

	}


	public User(long id, String username, String firstname, String lastname, int age, String password,
			String email, String phone, String role, int accepted, boolean enabled, int version) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.password = password;
		this.enabled = enabled;
		this.version = version;
		this.email = email;
		this.accepted = accepted;
		this.phone = phone;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/*
	 * public String getRole() { return role; }
	 * 
	 * public void setRole(String role) { this.role = role; }
	 * 
	 * public String getPermissions() { return permissions; }
	 * 
	 * public void setPermissions(String permissions) { this.permissions =
	 * permissions; }
	 */

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "EmpFlight [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", age=" + age + ", password=" + password + ", enabled=" + enabled
				+ ", version=" + version + ", email=" + email + ", accepted=" + accepted + ", role=" + role + ", phone="
				+ phone + "]";
	}

}