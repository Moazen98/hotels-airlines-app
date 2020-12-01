package com.zeon.UserPortable.Model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.micrometer.core.annotation.Counted;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = UserPlane.class)
public class UserPlane {

	private long id;
	private long planelId;
	private String userName;
	private String planeType;
	private int avalible;
	private float totalCost;
	private boolean canceled;
	private long accountId;
	private Date startdate;
	private String degree;
	private int num;
	private String location;
	private String destination;
	private Date date;
	private String arriveTime;
	private int taxiReserve;

	public UserPlane() {

	}

	public UserPlane(long id, long planelId, String userName, String planeType, int avalible, float totalCost,
			boolean canceled, long accountId, Date startdate, String degree, int num, String location,
			String destination, Date date, String arriveTime, int taxiReserve) {
		super();
		this.id = id;
		this.planelId = planelId;
		this.userName = userName;
		this.planeType = planeType;
		this.avalible = avalible;
		this.totalCost = totalCost;
		this.canceled = canceled;
		this.accountId = accountId;
		this.startdate = startdate;
		this.degree = degree;
		this.num = num;
		this.location = location;
		this.destination = destination;
		this.date = date;
		this.arriveTime = arriveTime;
		this.taxiReserve = taxiReserve;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlanelId() {
		return planelId;
	}

	public void setPlanelId(long planelId) {
		this.planelId = planelId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public int getAvalible() {
		return avalible;
	}

	public void setAvalible(int avalible) {
		this.avalible = avalible;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getTaxiReserve() {
		return taxiReserve;
	}

	public void setTaxiReserve(int taxiReserve) {
		this.taxiReserve = taxiReserve;
	}

	@Override
	public String toString() {
		return "UserPlane [id=" + id + ", planelId=" + planelId + ", userName=" + userName + ", planeType=" + planeType
				+ ", avalible=" + avalible + ", totalCost=" + totalCost + ", canceled=" + canceled + ", accountId="
				+ accountId + ", startdate=" + startdate + ", degree=" + degree + ", num=" + num + ", location="
				+ location + ", destination=" + destination + ", date=" + date + ", arriveTime=" + arriveTime
				+ ", taxiReserve=" + taxiReserve + "]";
	}

}
