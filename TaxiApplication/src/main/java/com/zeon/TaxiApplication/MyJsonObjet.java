package com.zeon.TaxiApplication;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MyJsonObjet {
private JsonObject jsonObject;
private JsonElement jsonElement;

private String city;
private String country;


private String iata;
private String icao;
private String x;
private String y;
private String elevation;
private String apid;
private String timeZone;
private String dst;
private String tz_id;
private String type;
private String ap_name;
private String lat;
private String lon;
private String elev;
private String code;
private String phone;
private Long woeid;
private String tz;
private String name;
private String email;
private String runway_length;
private String direct_flights;
private String carriers;
private String url;
private String state;


//taxi data


private String firstName;
private String lastName;
private String carModel;
private String carVIN;




	public MyJsonObjet(JsonElement jsonElement) {
		this.jsonElement=jsonElement;
		
		city=jsonElement.getAsJsonObject().get("city").toString();
		
		city.substring(1,city.length()-1);
		
		country=jsonElement.getAsJsonObject().get("country").toString();
		iata=jsonElement.getAsJsonObject().get("iata").toString();
		icao=jsonElement.getAsJsonObject().get("icao").toString();
		x=jsonElement.getAsJsonObject().get("x").toString();
		y=jsonElement.getAsJsonObject().get("y").toString();
		elevation=jsonElement.getAsJsonObject().get("elevation").toString();
		apid=jsonElement.getAsJsonObject().get("apid").toString();
	   	timeZone=jsonElement.getAsJsonObject().get("timezone").toString();
		dst=jsonElement.getAsJsonObject().get("dst").toString();
		tz_id=jsonElement.getAsJsonObject().get("tz_id").toString();
		type=jsonElement.getAsJsonObject().get("type").toString();
		ap_name=jsonElement.getAsJsonObject().get("ap_name").toString();
		
		

	
	
	
	}
	
	public MyJsonObjet(JsonElement jsonElement,String s) {
		this.jsonElement=jsonElement;
		
		
		 firstName=jsonElement.getAsJsonObject().get("firstName").toString();
		 lastName=jsonElement.getAsJsonObject().get("lastName").toString();
		 carModel=jsonElement.getAsJsonObject().get("carModel").toString();
		 carVIN=jsonElement.getAsJsonObject().get("carVIN").toString();
		  
		  
		
		 
		 

	
		
		
		
	
	
	
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

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarVIN() {
		return carVIN;
	}

	public void setCarVIN(String carVIN) {
		this.carVIN = carVIN;
	}

	public MyJsonObjet(JsonElement jsonElement ,int x) {
		this.jsonElement=jsonElement;
		
		city=jsonElement.getAsJsonObject().get("city").toString();
		
		
		country=jsonElement.getAsJsonObject().get("country").toString();
		icao=jsonElement.getAsJsonObject().get("icao").toString();
		lat=jsonElement.getAsJsonObject().get("lat").toString();
		lon=jsonElement.getAsJsonObject().get("lon").toString();
		elev=jsonElement.getAsJsonObject().get("elev").toString();
		code=jsonElement.getAsJsonObject().get("code").toString();
	   	phone=jsonElement.getAsJsonObject().get("phone").toString();
		woeid= Long.valueOf( jsonElement.getAsJsonObject().get("woeid").getAsString());
		tz=jsonElement.getAsJsonObject().get("tz").toString();
		type=jsonElement.getAsJsonObject().get("type").toString();
		name=jsonElement.getAsJsonObject().get("name").toString();
		email=jsonElement.getAsJsonObject().get("email").toString();
		url=jsonElement.getAsJsonObject().get("url").toString();
		runway_length=jsonElement.getAsJsonObject().get("runway_length").toString();
		direct_flights=jsonElement.getAsJsonObject().get("direct_flights").toString();
		carriers=jsonElement.getAsJsonObject().get("carriers").toString();
		state=jsonElement.getAsJsonObject().get("state").toString();
		
		
	
	
	
	}

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JsonElement getJsonElement() {
		return jsonElement;
	}

	public void setJsonElement(JsonElement jsonElement) {
		this.jsonElement = jsonElement;
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

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public String getApid() {
		return apid;
	}

	public void setApid(String apid) {
		this.apid = apid;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getTz_id() {
		return tz_id;
	}

	public void setTz_id(String tz_id) {
		this.tz_id = tz_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAp_name() {
		return ap_name;
	}

	public void setAp_name(String ap_name) {
		this.ap_name = ap_name;
	}

	



	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getElev() {
		return elev;
	}

	public void setElev(String elev) {
		this.elev = elev;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getWoeid() {
		return woeid;
	}

	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
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

	public String getRunway_length() {
		return runway_length;
	}

	public void setRunway_length(String runway_length) {
		this.runway_length = runway_length;
	}

	public String getDirect_flights() {
		return direct_flights;
	}

	public void setDirect_flights(String direct_flights) {
		this.direct_flights = direct_flights;
	}

	public String getCarriers() {
		return carriers;
	}

	public void setCarriers(String carriers) {
		this.carriers = carriers;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
public String getLat() {
	return lat;
}


public String getState() {
	return state;
}

public void setLat(String lat) {
	this.lat = lat;
}

public void setState(String state) {
	this.state = state;
}
	
	
	
	
	
}
