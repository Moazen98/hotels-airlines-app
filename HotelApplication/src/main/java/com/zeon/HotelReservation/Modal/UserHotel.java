package com.zeon.HotelReservation.Modal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "hoteluserdb")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = UserHotel.class)
public class UserHotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long hotelId;
	private long roomId;
	private String cityName;
	private String userName;
	@Temporal(TemporalType.DATE)
	private Date startdate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String roomType;
	private int avalible;
	private float totalCost;
	private boolean canceled;
	private long accountId;

	public UserHotel() {

	}





	public UserHotel(long id, long hotelId, long roomId, String cityName, String userName, Date startdate, Date endDate,
			String roomType, int avalible, float totalCost, boolean canceled, long accountId) {
		super();
		this.id = id;
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.cityName = cityName;
		this.userName = userName;
		this.startdate = startdate;
		this.endDate = endDate;
		this.roomType = roomType;
		this.avalible = avalible;
		this.totalCost = totalCost;
		this.canceled = canceled;
		this.accountId = accountId;
	}





	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAvalible() {
		return avalible;
	}

	public void setAvalible(int avalible) {
		this.avalible = avalible;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
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





	@Override
	public String toString() {
		return "UserHotel [id=" + id + ", hotelId=" + hotelId + ", roomId=" + roomId + ", cityName=" + cityName
				+ ", userName=" + userName + ", startdate=" + startdate + ", endDate=" + endDate + ", roomType="
				+ roomType + ", avalible=" + avalible + ", totalCost=" + totalCost + ", canceled=" + canceled
				+ ", accountId=" + accountId + "]";
	}



	

}
