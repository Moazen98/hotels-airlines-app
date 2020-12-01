package com.zeon.UserPortable.Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Coordinates {

	private float latitude;
	private float longtitude;

	public Coordinates()
	{
		
	}
	public Coordinates(String airPorts) throws FileNotFoundException {
		String filepath = "airports.json";
		ArrayList<String> data = new ArrayList<String>();
		FileReader fileReader;
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray;

		fileReader = new FileReader(filepath);
		jsonArray = (JsonArray) jsonParser.parse(fileReader);

		String lat="35";
		String lon="38";
		for (JsonElement je : jsonArray) {
			
			String name = je.getAsJsonObject().get("name").toString();
			name=name.substring(1,name.length()-1);
			if(name.equals(airPorts))
			{
				lat = je.getAsJsonObject().get("lat").toString();
				lat=lat.substring(1,lat.length()-1);
				lon = je.getAsJsonObject().get("lon").toString();
				lon=lon.substring(1,lon.length()-1);
				
			}
//			System.out.println("laaaaaaaaaaaaaat"+lat);
//			System.out.println("looooooooooooon"+lon);
//			System.out.println("naaaaaaaaaaaaam"+name);
		}
		this.longtitude = Float.valueOf(lon);
		this.latitude = Float.valueOf(lat);
	}

	public float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longtitude=" + longtitude + "]";
	}

}
