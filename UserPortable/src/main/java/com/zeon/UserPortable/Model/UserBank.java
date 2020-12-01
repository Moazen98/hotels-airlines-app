package com.zeon.UserPortable.Model;


import java.util.Date; 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class UserBank {

	private long idaccount;
	private long userid;
	private float currentbalance;
	private String workname;
	private int isenabled;
	@Temporal(TemporalType.DATE)
	private Date registerdate;
	private int version;
	
	Users users = new Users();

	public UserBank() {

	}

	public UserBank(long idaccount, long userid, float currentbalance, String workname, int isenabled,
			Date registerdate, int version,Users users) {
		super();
		this.idaccount = idaccount;
		this.userid = userid;
		this.currentbalance = currentbalance;
		this.workname = workname;
		this.isenabled = isenabled;
		this.registerdate = registerdate;
		this.version = version;
		this.users = users;
	}
	
	public UserBank(long userid, float currentbalance, String workname, int isenabled,
			Date registerdate, int version,Users users) {
		super();
		this.userid = userid;
		this.currentbalance = currentbalance;
		this.workname = workname;
		this.isenabled = isenabled;
		this.registerdate = registerdate;
		this.version = version;
		this.users = users;
	}
	
	
	public UserBank(long idaccount, long userid, float currentbalance, String workname, int isenabled,
			Date registerdate, int version) {
		super();
		this.idaccount = idaccount;
		this.userid = userid;
		this.currentbalance = currentbalance;
		this.workname = workname;
		this.isenabled = isenabled;
		this.registerdate = registerdate;
		this.version = version;
	}

	public long getIdaccount() {
		return idaccount;
	}

	public void setIdaccount(long idaccount) {
		this.idaccount = idaccount;
	}



	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public float getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(float currentbalance) {
		this.currentbalance = currentbalance;
	}

	public String getWorkname() {
		return workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
	}

	public int getIsenabled() {
		return isenabled;
	}

	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}

	public Date getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	

	public Users getUsers() {
		return users; 
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UserBank [idaccount=" + idaccount + ", userid=" + userid + ", currentbalance=" + currentbalance
				+ ", workname=" + workname + ", isenabled=" + isenabled + ", registerdate=" + registerdate
				+ ", version=" + version + ", users=" + users + "]";
	}


	
	
}
