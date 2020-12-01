package com.zeon.TaxiApplication.Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeon.TaxiApplication.TaxiData;
import com.zeon.TaxiApplication.Model.Taxi;
import com.zeon.TaxiApplication.Model.UserTaxi;
import com.zeon.TaxiApplication.Service.TaxiService;
import com.zeon.TaxiApplication.Service.UserTaxiService;

@Controller
public class TaxiApplicationController {

	@Autowired
	TaxiService ts;

	@Autowired
	UserTaxiService us;

	@RequestMapping("/read-taxi-json")
	@ResponseBody
	public String readJsonTaxi() throws IOException {

		TaxiData t = new TaxiData();
		ArrayList<Taxi> taxi = new ArrayList<Taxi>();
		t.genData(5000);
		taxi = t.getTaxisData();

		for (int i = 0; i < taxi.size(); i++) {
			System.out.println("The data is ....." + taxi.get(i));
		}

		// ts.addTaxiAll(taxi);

		return "work ... !";
	}

	@RequestMapping("/get-all-taxi")
	@ResponseBody
	public String getAllTaxi() throws IOException {

		ArrayList<Taxi> taxi = new ArrayList<Taxi>();

		taxi = ts.getAllTaxi();

		for (int i = 0; i < taxi.size(); i++) {
			System.out.println("The data is ....." + taxi.get(i));
		}

		return "work ... !";
	}

	@RequestMapping("/delete-taxi")
	@ResponseBody
	public ResponseEntity<Integer> deleteTaxi(@RequestBody List<String> data) throws IOException {

		int check = 0;

		System.out.println("The List data is ......" + data.toString());

		String userName = data.get(0);
		String location = data.get(1);
		String destination = data.get(2);

		UserTaxi user = new UserTaxi();
		user = us.getUserTaxiByEmailAndCity(userName, destination);

		long taxiId = user.getTaxiId();

		System.out.println("The User is ...... :" + user.toString());

		long id = user.getId();

		us.deleteUserTaxi(id);

		ts.setTaxiAvalible(taxiId);

		if (user == null) {
			check = 0;
			return new ResponseEntity(check, HttpStatus.OK);
		} else {
			check = 1;
			return new ResponseEntity(check, HttpStatus.OK);
		}
	}

	@RequestMapping("/check-taxi")
	@ResponseBody
	public ResponseEntity<Taxi> checkTaxi(@RequestBody List<String> data) throws ParseException {

		Taxi taxi = new Taxi();

		ArrayList<Taxi> allTaxi = new ArrayList<Taxi>();

		String city = "Zhengzhou"; //////////////////////asdasdsadsadsa

		allTaxi = ts.getAllTaxiCity(city);

		System.out.println("All Taxi is :" + allTaxi.size());

		SimpleDateFormat format = new SimpleDateFormat("hh:mm"); // if 24 hour format

		java.util.Date d1 = (java.util.Date) format.parse(data.get(1));

		Time ppstime = new Time(d1.getHours());

		long time = ppstime.getTime();

		taxi = ts.getTaxiCityAvalible(allTaxi, time);

		if (taxi != null) {
			taxi.setAvailable(false);

			ts.addTaxi(taxi);
		}

		System.out.println("The Taxi is ..... :" + taxi.toString());
		
		if (taxi == null) {
			Taxi taxiNull = new Taxi();
			return new ResponseEntity(taxiNull, HttpStatus.OK);
		} else {

			return new ResponseEntity(taxi, HttpStatus.OK);
		}

	}

	@RequestMapping("/check-old-data")
	@ResponseBody
	public String editData() throws InterruptedException {
		// ArrayList<Taxi> allTaxi = new ArrayList<Taxi>();
		List<UserTaxi> allUserTaxi = new ArrayList<UserTaxi>();
		allUserTaxi = us.getAllUserTaxi();

		// for (int i = 0; i < allUserTaxi.size(); i++) {
		int check = ts.CheckAllUserTaxi();
		// System.out.println("The check is :. ..... :"+check);
		// }

		return "Work ...... !";
	}

	@RequestMapping("/get-all-user")
	@ResponseBody
	public String getAllUser() {
		List<UserTaxi> userTaxi = new ArrayList<UserTaxi>();

		userTaxi = us.getAllUserTaxi();

		System.out.println("The UserTaxi is ...." + userTaxi.size());

		return "Work ..... !";
	}

	@RequestMapping("/save-user-taxi")
	@ResponseBody
	public ResponseEntity<Integer> saveUserTaxi(@RequestBody List<String> data) throws ParseException {

		System.out.println("saveUserTaxi Service is called ......");

		UserTaxi userTaxi = new UserTaxi();

		userTaxi.setName(data.get(0));
		userTaxi.setEmail(data.get(1));
		userTaxi.setPhone(data.get(2));
		userTaxi.setUserId(Integer.valueOf(data.get(3)));
		userTaxi.setTaxiId(Integer.valueOf(data.get(4)));
		userTaxi.setAvalible(Integer.valueOf(data.get(5)));

		SimpleDateFormat format = new SimpleDateFormat("hh:mm"); // if 24 hour format

		java.util.Date d1 = (java.util.Date) format.parse(data.get(6));

		Time ppstimeq1 = new Time(d1.getHours());

		long time1 = ppstimeq1.getTime();

		System.out.println("The Time of Taxi User is :" + time1);

		userTaxi.setDateReq(time1);

		userTaxi.setCity(data.get(7));

		System.out.println("The User Taxi is : ......" + userTaxi.toString());

		int timeTravel = getRandomNumber(1, 4);

		userTaxi.setCity(data.get(7));

		userTaxi.setTimeTravle(timeTravel);

		us.addUserTaxi(userTaxi);

		int result = 1;

		if (result == 0) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity(result, HttpStatus.OK);
		}

	}

	private Integer getRandomNumber(int x, int y) {
		return x + (int) (Math.random() * y);

	}
}
