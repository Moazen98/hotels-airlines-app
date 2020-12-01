package com.zeon.UserPortable.Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Plane implements Serializable{

    private long id;
	private String location;
	private String destination;
	private String cityCode;
	private String srcAriport;
	private String desAirport;
	private Date date;
	private String arriveTime;
	private String takeOff;
	private String img;
	private int first_class_total;
	private int first_class_available;
	private int first_class_price;
	private int business_total;
	private int business_available;
	private int business_price;
	private int premium_economy_total;
	private int premium_economy_available;
	private int premium_economy_price;
	private int economy_total;
	private int economy_available;
	private int economy_price;

	
	
	public Plane()
	{
		
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	
		

	public String getCityCode() {
		return cityCode;
	}



	public String getSrcAriport() {
			return srcAriport;
		}



		public void setSrcAriport(String srcAriport) {
			this.srcAriport = srcAriport;
		}



		public String getDesAirport() {
			return desAirport;
		}



		public void setDesAirport(String desAirport) {
			this.desAirport = desAirport;
		}



		public String getTakeOff() {
			return takeOff;
		}



		public void setTakeOff(String takeOff) {
			this.takeOff = takeOff;
		}



		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}

		
	

	public Date getDate() {
			return date;
		}



		public void setDate(Date date) {
			this.date = date;
		}


		

	
	public int getFirst_class_total() {
			return first_class_total;
		}



		public void setFirst_class_total(int first_class_total) {
			this.first_class_total = first_class_total;
		}



		public int getFirst_class_available() {
			return first_class_available;
		}



		public void setFirst_class_available(int first_class_available) {
			this.first_class_available = first_class_available;
		}



		public int getFirst_class_price() {
			return first_class_price;
		}



		public void setFirst_class_price(int first_class_price) {
			this.first_class_price = first_class_price;
		}



		public int getBusiness_total() {
			return business_total;
		}



		public void setBusiness_total(int business_total) {
			this.business_total = business_total;
		}



		public int getBusiness_available() {
			return business_available;
		}



		public void setBusiness_available(int business_available) {
			this.business_available = business_available;
		}



		public int getBusiness_price() {
			return business_price;
		}



		public void setBusiness_price(int business_price) {
			this.business_price = business_price;
		}



		public int getPremium_economy_total() {
			return premium_economy_total;
		}



		public void setPremium_economy_total(int premium_economy_total) {
			this.premium_economy_total = premium_economy_total;
		}



		public int getPremium_economy_available() {
			return premium_economy_available;
		}



		public void setPremium_economy_available(int premium_economy_available) {
			this.premium_economy_available = premium_economy_available;
		}



		public int getPremium_economy_price() {
			return premium_economy_price;
		}



		public void setPremium_economy_price(int premium_economy_price) {
			this.premium_economy_price = premium_economy_price;
		}



		public int getEconomy_total() {
			return economy_total;
		}



		public void setEconomy_total(int economy_total) {
			this.economy_total = economy_total;
		}



		public int getEconomy_available() {
			return economy_available;
		}



		public void setEconomy_available(int economy_available) {
			this.economy_available = economy_available;
		}



		public int getEconomy_price() {
			return economy_price;
		}



		public void setEconomy_price(int economy_price) {
			this.economy_price = economy_price;
		}



	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	
    public static Comparator<Plane> PlanePriceComparatorPro = new Comparator<Plane>() {

    	public int compare(Plane p1, Plane p2) {
    	   int price1 = p1.getEconomy_price();
    	   int price2 = p2.getEconomy_price();

    	   return price2 - price1 ;

    	  }
        
        };




	@Override
	public String toString() {
		return "Plane [id=" + id + ", location=" + location + ", destination=" + destination + ", cityCode=" + cityCode
				+ ", srcAriport=" + srcAriport + ", desAirport=" + desAirport + ", date=" + date + ", arriveTime="
				+ arriveTime + ", takeOff=" + takeOff + ", img=" + img + ", first_class_total=" + first_class_total
				+ ", first_class_available=" + first_class_available + ", first_class_price=" + first_class_price
				+ ", business_total=" + business_total + ", business_available=" + business_available
				+ ", business_price=" + business_price + ", premium_economy_total=" + premium_economy_total
				+ ", premium_economy_available=" + premium_economy_available + ", premium_economy_price="
				+ premium_economy_price + ", economy_total=" + economy_total + ", economy_available="
				+ economy_available + ", economy_price=" + economy_price + "]";
	}



	
	
	

}
