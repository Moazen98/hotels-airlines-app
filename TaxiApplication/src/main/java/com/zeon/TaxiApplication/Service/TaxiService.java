package com.zeon.TaxiApplication.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zeon.TaxiApplication.Model.Taxi;
import com.zeon.TaxiApplication.Model.UserTaxi;
import com.zeon.TaxiApplication.Repository.TaxiRepository;

@Service
public class TaxiService {

	@Autowired
	private final TaxiRepository taxiRepo;

	@Autowired
	private UserTaxiService us;

	public TaxiService(TaxiRepository taxiRepo) {
		this.taxiRepo = taxiRepo;
	}

	@Transactional
	public void addTaxiAll(ArrayList<Taxi> taxi) {
		taxiRepo.saveAll(taxi);
	}

	@Transactional
	public void addTaxi(Taxi taxi) {
		taxiRepo.save(taxi);
	}

	@Transactional
	public ArrayList<Taxi> getAllTaxi() {
		return (ArrayList<Taxi>) taxiRepo.findAll();
	}

	@Transactional
	public ArrayList<Taxi> getAllTaxiCity(String city) {

		ArrayList<Taxi> allTaxiCity = taxiRepo.findTaxiByCity(city);

		return allTaxiCity;

	}

	@Transactional
	public Taxi getTaxiCityAvalible(ArrayList<Taxi> list, long date) throws ParseException {

		Taxi taxi = new Taxi();

		System.out.println("The Time is ................ :" + date);

		System.out.println("The Taxi size is ................ :" + list.size());

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).isAvailable()) {

				System.out.println("Ther is Taxi Avalible................");

				SimpleDateFormat format = new SimpleDateFormat("hh:mm"); // if 24 hour format

				java.util.Date d1 = (java.util.Date) format.parse(list.get(i).getStartTime());

				java.util.Date d2 = (java.util.Date) format.parse(list.get(i).getEndTime());

				Time ppstimeq1 = new Time(d1.getHours());

				Time ppstimeq2 = new Time(d2.getHours());

				long time1 = ppstimeq1.getTime();
				long time2 = ppstimeq2.getTime();

				System.out.println("The Time1 is ................ :" + time1);
				System.out.println("The Time2 is ................ :" + time2);

				if (date < time1 && date > time2) { ///////////// here edit the condition it should be date > time1 &&
													///////////// date < time2

					System.out.println("There is Taxi City ................");

					taxi = list.get(i);
					break;
				}

			}

		}

		return taxi;
	}
	
	@Transactional
	public int setTaxiAvalible(long Id) {
		
		Taxi taxi = new Taxi();
		taxi = taxiRepo.findTaxiById(Id);
		taxi.setAvailable(true);
		taxiRepo.save(taxi);
		return 1;
	}

	@Transactional
	public Taxi getTaxiById(long Id) {
		Taxi taxi = new Taxi();

		taxi = taxiRepo.findTaxiById(Id);

		return taxi;
	}

	@Transactional
	public int CheckAllUserTaxi() throws InterruptedException {

		int check = 0;

		List<UserTaxi> userTaxi = new ArrayList<UserTaxi>();

		userTaxi = us.getAllUserTaxi();

		for (int i = 0; i < userTaxi.size(); i++) {

			long TaxiId = userTaxi.get(i).getId();

			Taxi taxi = new Taxi();

			taxi = getTaxiById(TaxiId);

			if (!taxi.isAvailable()) {

				long timeTravel = userTaxi.get(i).getDateReq();
				long timeReq = userTaxi.get(i).getTimeTravle();

				System.out.println(" The timeTravel  ........" + timeTravel);
				System.out.println("The timeReq  ........" + timeReq);

				long total = timeTravel + timeReq;

				System.out.println("The total  ........" + total);

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				DateFormat outputformat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

				Date date = new Date();

				System.out.println(outputformat.format(date));

				long timeNow = date.getHours();

				System.out.println("Time real Now is .......... :" + timeNow);

				if (total < timeNow) {
					userTaxi.get(i).setAvalible(0);
					us.addUserTaxi(userTaxi.get(i));
					System.out.println("Save The User Taxi ........");

					TimeUnit.SECONDS.sleep(2);

					taxi.setAvailable(true);
					taxiRepo.save(taxi);

					System.out.println("Save The Taxi ........");

					check = 1;

				}
			}

		}

		return check;

	}

	@Component
	class DataUpdaterScheduled {

		@Scheduled(cron = "* 20 * * * ?")
		public void cronJobSch() {
			int exceptionCount = 0;

			List<UserTaxi> userTaxi = new ArrayList<UserTaxi>();

			// userTaxi = us.getAllUserTaxi();

			System.out.println("The UserTaxi is ...." + userTaxi.size());

			System.out.println("Before Scheduled ......");

			System.out.println("befor");
			// for (int i = 0; i < userTaxi.size(); i++) {
			try {

				CheckAllUserTaxi();

			} catch (Exception e) {
				exceptionCount++;
			}

			// }

			System.out.println("After Scheduled .......");
			System.out.println("Exception Count=" + exceptionCount);
		}
	}

}
