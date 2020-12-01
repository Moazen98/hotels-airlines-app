package com.zeon.TaxiApplication;


import java.io.FileReader; 
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Pair;

import com.google.gson.*;
import com.zeon.TaxiApplication.Model.Taxi;
public class TaxiData {
	
	private ArrayList<Taxi> taxisData=new ArrayList<Taxi>();
	
	private JsonParser jsonParser=new JsonParser();
	private	FileReader fileReader1=new FileReader("airports.json");
	
	private	FileReader fileReader2=new FileReader("taxiData.json");
	private	JsonArray jsonArray1,jsonArray2;
	private	  ArrayList<Pair<String,String>> locations =new ArrayList<Pair<String,String>>();
	  
	private	  ArrayList<ArrayList<String>> carData =new ArrayList<ArrayList<String>>();
    
	private	  String s;
	
	public TaxiData() throws IOException {
	
	
		jsonArray1=(JsonArray) jsonParser.parse(fileReader1);
		jsonArray2=(JsonArray) jsonParser.parse(fileReader2);
		
		
			
		

	 for(JsonElement je : jsonArray1) {
		
		MyJsonObjet myJsonObjet= new MyJsonObjet(je,1);
	Pair <String, String> location =new Pair<String, String>(myJsonObjet.getCountry(), myJsonObjet.getCity());
	locations.add(location);
	
	 }
		
		for(JsonElement je : jsonArray2) {
			
			MyJsonObjet myJsonObjet= new MyJsonObjet(je,"taxi");
			ArrayList<String> data=new ArrayList<String>();
			data.add(myJsonObjet.getFirstName());
			data.add(myJsonObjet.getLastName());
			data.add(myJsonObjet.getCarModel());
			data.add(myJsonObjet.getCarVIN());

			carData.add(data);
		}
		
		
		
		
		
}
	public ArrayList<Taxi> getTaxisData() {
		return taxisData;
	}
	
	public void genData(int count ) {
		Pair<String,String> location;
		for (int i = 0; i < count; i++) {
		Taxi taxi=new Taxi();
		location=locations.get(getRandomNumber ( 0,locations.size() )  );
		taxi.setCountry(location.getKey());
		taxi.setCity(location.getValue());
		ArrayList<String> data;
		data=carData.get(getRandomNumber(0, carData.size()));
		
		taxi.setFirstName(data.get(0));
		taxi.setLastName(data.get(1));
		taxi.setCarModel(data.get(2));
		taxi.setCarVIN(data.get(3));
		
		
		taxi.setAvailable(getRandomAvailable());
		s=getStartTime();
		taxi.setStartTime(s);
		taxi.setEndTime(getEndTime(s));
		taxi.setPhoneNumber(getPhoneNumber());
		
		taxisData.add(taxi);	
			
			
		}
		
		
		
	}
	
	
	boolean getRandomAvailable() {
		
	if (getRandomNumber(0, 2)==1) 
		return true;					
    else
    	return false;

	}
	
	private String getPhoneNumber() {
		String number=""+getNumberFormat(getRandomNumber(999, 9999))+"-"+getNumberFormat(getRandomNumber(0, 999))+"-"+getNumberFormat(getRandomNumber(0, 999));
		
		
	return number;	
	}
	private String getStartTime() {
		String s;
		int h,m;
		h=getRandomNumber(0, 24);
		if(h<10)
			s="0"+h;
		else
			s=""+h;
		
		m=getRandomNumber(0, 2);
		if(m==1)
			m=30;
			
		if(m<10)
			s+=":0"+m;
		else
			s+=":"+m;
		
		return s;
		
		
	}
	private String getEndTime(String startTime) {
		int x=Integer.valueOf(startTime.substring(0,2));
	
		int y=Integer.valueOf(startTime.substring(3,5));
		
		String s;
		int h,m;			
		h=x+getRandomNumber(5, 9);;	
		h=h%24;
		
		if(h<10)
			s="0"+h;
		else
			s=""+h;
		
		m=y;
		if(m<10)
			s+=":0"+m;
		else
			s+=":"+m;
		
		return s;
		
		
	}
	
	private String getNumberFormat(int x) {
		if(x>99)
			return ""+x;
	
		if (x>9) 
			return "0"+x;
		else 
			return "00"+x;
		
	}
	
	 private Integer getRandomNumber(int x, int y) {
	        return x + (int) (Math.random() * y);
	    }
	@Override
	public String toString() {
		return "TaxiData [taxisData=" + taxisData + ", fileReader1=" + fileReader1 + ", fileReader2=" + fileReader2
				+ ", locations=" + locations + ", carData=" + carData + ", s=" + s + "]";
	}

	
	 
	
}