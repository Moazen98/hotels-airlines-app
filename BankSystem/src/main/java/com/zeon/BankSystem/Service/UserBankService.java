package com.zeon.BankSystem.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeon.BankSystem.Model.UserBank;
import com.zeon.BankSystem.Repository.UserBankRepository;

@Service
@Transactional
public class UserBankService {

	@Autowired
	private final UserBankRepository userBankRepository;

	static Connection Mycon = null;
	static String Myurl = "jdbc:mysql://localhost:3306/userdb";
	static String username = "root";
	static String password = "123456";
	Statement staement;

	public UserBankService(UserBankRepository userBankRepository) {
		this.userBankRepository = userBankRepository;
	}

	@Transactional
	public void saveUserBank(UserBank user) {
		userBankRepository.save(user);
	}

	@Transactional
	public void deleteAccount(long id) {
		userBankRepository.deleteById(id);
	}

	@Transactional
	public UserBank getAccount(long id) {
		UserBank user = userBankRepository.findUserBankByidaccount(id);
		return user;
	}

	@Transactional
	public List<UserBank> showAllUsersBank() {
		List<UserBank> userBank = new ArrayList<UserBank>();

		for (UserBank userBanks : userBankRepository.findAll()) {
			userBank.add(userBanks);
		}
		for (int i = 0; i < userBank.size(); i++)
			System.out.println("user Bank is:" + userBank.get(i));
		return userBank;
	}

	@Transactional
	public UserBank getUserBankAccount(long id) {

		UserBank userBank = new UserBank();
		userBank = userBankRepository.findUserBankByidaccount(id);
		return userBank;
	}

	@Transactional
	public int updatingUserBank(UserBank user)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		int sqlRes;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Mycon = DriverManager.getConnection(Myurl, username, password);
		staement = Mycon.createStatement();

		sqlRes = staement.executeUpdate(" update bankaccount set idaccount='" + user.getIdaccount() + "', userid='"
				+ user.getUserid() + "'," + "currentbalance='" + user.getCurrentbalance() + "',workname='"
				+ user.getWorkname() + "' ," + "isenabled='" + user.getIsenabled() + "',registerdate='"
				+ user.getRegisterdate() + "' ,version='" + (user.getVersion() + 1) + "' where idaccount='"
				+ user.getIdaccount() + "' and version ='" + user.getVersion() + "'   ");

		return sqlRes;
	}

	@Transactional
	public UserBank checkAccountInfo(String email, String password, long accountid) {
		int check = 0;
		List<UserBank> userBank = new ArrayList<UserBank>();

		for (UserBank userBanks : userBankRepository.findAll()) {
			userBank.add(userBanks);
		}

		for (int i = 0; i < userBank.size(); i++) {
			System.out.println("the User Bank List : !!!!!" + userBank.get(i).getCurrentbalance());
			System.out.println("the User Bank List : !!!!!" + userBank.get(i).getUsers().getEmail());

		}
		System.out.println("the Email Bank  : " + email);
		System.out.println("the Size Bank List : " + userBank.size());
		
		System.out.println("bool is :"+userBank.get(1).getUsers().getEmail().equals(email));
		
		boolean isExist = false;

		UserBank account = new UserBank();
		for (int i = 0; i < userBank.size(); i++) {
			System.out.println("The size : ....."+userBank.size());
			System.out.println("the Email Bank User ...... : " + userBank.get(i).getUsers().getEmail());
			System.out.println("the Email  User ...... : " + email);
			
			if (userBank.get(i).getUsers().getEmail().equals(email)) {
				account = userBank.get(i);
				System.out.println("The user email is .......:" + account.getUsers().getEmail());
				System.out.println("/////////////////////////////////////////");
				System.out.println("The User Password is :...."+account.getUsers().getPassword());
				System.out.println("The User Id Account is :...."+account.getIdaccount());
				isExist = true;
				break;
			} else {
				isExist = false;
			}
			
			
		}
		if (isExist && !account.getUsers().getEmail().equals("")) {
			if (account.getUsers().getPassword().equals(password)) {   //account.getIdaccount() == accountid
				check = 1;
			} else {
				System.out.println("Your Password or accountoid is Wrong ......");
				check = 0;
			}
		} else {
			System.out.println("Your email is not exist ......");
			check = 0;
		}

		if (check == 1) {
			System.out.println("ababababababba ......");
			return account;
		} else {
			return null;
		}
	}

	@Transactional
	public int DiscountCache(UserBank user, float price) {

		float cache = user.getCurrentbalance();
		System.out.println("The cache is ..... :"+cache);
		System.out.println("The Price is ..... :"+price);
		System.out.println("The user is  :"+user.toString());
		if (cache <= 0) {
			System.out.println(".................");
			return 0;
		} else if (cache < price) {
			System.out.println("****************");
			return 0;
		} else {
			System.out.println("ssssssssssssssssssssss");
			float discache = user.getCurrentbalance() - price; /////// edit in user
			user.setCurrentbalance(discache);
			userBankRepository.save(user);
			return 1;
		}

	}

//	@Transactional
//	public int DiscountMoneyUser(UserBank user) {
//
//		float cache = user.getCurrentbalance();
//		if (cache <= 0) {
//			return 0;
//		} else if (cache < price) {
//			return 0;
//		} else {
//			float discache = user.getCurrentbalance() - price; /////// edit in user
//			user.setCurrentbalance(discache);
//			userBankRepository.save(user);
//			return 1;
//		}
//
//	}

	@Transactional
	public int editUserBankInfo(int id, float cache) {

		List<UserBank> userBank = new ArrayList<UserBank>();
		int ans = 0;

		for (UserBank userBanks : userBankRepository.findAll()) {
			userBank.add(userBanks);
		}

		UserBank account = new UserBank();
		for (int i = 0; i < userBank.size(); i++) {
			if (userBank.get(i).getUsers().getId() == id) {
				account = userBank.get(i);
				System.out.println("The user email is :" + account.getUsers().getEmail());
				System.out.println("/////////////////////////////////////////");
				userBank.get(i).setCurrentbalance(cache);
				userBankRepository.save(userBank.get(i));
				ans = 1;
			} else {
				ans = 0;
			}

		}
		return ans;
	}

//	@Transactional
//	public String discountMony(String email, long money) {
//
//		List<UserBank> userBank = new ArrayList<UserBank>();
//
//		for (UserBank userBanks : userBankRepository.findAll()) {
//			userBank.add(userBanks);
//		}
//
//		UserBank account = new UserBank();
//		for (int i = 0; i < userBank.size(); i++) {
//			if (userBank.get(i).getUsers().getEmail().equals(email)) {
//				account = userBank.get(i);
//				System.out.println("The user email is :" + account.getUsers().getEmail());
//				System.out.println("/////////////////////////////////////////");
//			} else {
//				return null;
//			}
//		}
//		if (!account.getUsers().getEmail().equals("")) {
//			
//		}
//	}

//	@Transactional
//	public float DiscountCache(UserBank user, float roomPrice) {
//
//		float cache = user.getCurrentbalance();
//
//		if (cache == 0) {
//			return -1;
//		} else if (cache < roomPrice) {
//			return -2;
//		} else {
//			return cache - roomPrice;
//		}
//
//	}

}
