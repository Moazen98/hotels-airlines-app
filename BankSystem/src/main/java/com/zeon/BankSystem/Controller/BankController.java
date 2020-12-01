package com.zeon.BankSystem.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zeon.BankSystem.Model.Role;
import com.zeon.BankSystem.Model.UserBank;
import com.zeon.BankSystem.Model.Users;
import com.zeon.BankSystem.Service.UserBankService;
import com.zeon.BankSystem.Service.UsersService;

@Controller
public class BankController {

	@Autowired
	private UserBankService bs;

	@Autowired
	private UsersService us;

	@GetMapping("/home")
	public String homePage(HttpServletRequest request) {
		return "welcomepage";
	}

	@GetMapping("/page-user-bank")
	public String getUserBankPage() {
		return "userbankadd";
	}

	@PostMapping("/save-user-bank")
	public String addPatient(@ModelAttribute UserBank userBank, BindingResult bindingResult,
			HttpServletRequest request) {

		UserBank newBank = new UserBank();
		newBank = userBank;
		int userAccountId = (int) newBank.getUserid();
		Users user = us.findUserById(userAccountId);
		System.out.println("/////////////////" + user.getEmail());
		if (!user.getEmail().equals("")) {
			userBank.setUsers(user);
			userBank.setRegisterdate(new Date());
			bs.saveUserBank(userBank);
			System.out.println("_______________________________________");
			return "welcomepage";
		} else {
			return "stop";
		}
	}

	@GetMapping("/edit-page-bank")
	public String getEditPage(@RequestParam long id, HttpServletRequest request) {

		request.setAttribute("userbankedit", bs.getAccount(id));
		return "editpage";
	}

	@GetMapping("/bank-info")
	public String getPageInfo(HttpServletRequest request) {
		request.setAttribute("userBank", bs.showAllUsersBank());
		return "showalluserbank";
	}

//	@GetMapping("/addtest")
//	public String addUser() {
//		Users user = new Users();
//
//		Role role = new Role();
//		user.setActive(1);
//		user.setEmail("www.nero98.com");
//		user.setLastName("al moazen");
//		user.setName("mohamad");
//		user.setPassword("123");
////	    role.setRoleId(10);
//		role.setRole("ADMIN");
//		user.getRoles().add(role);
//		UserBank bank = new UserBank(1, 20000, "IT", 1, new Date(), 0, user);
//		bs.saveUserBank(bank);
//		return "welcomepage";
//	}

	@PostMapping("/addtest")
	public ResponseEntity<Integer> addUser(@RequestBody List<String> param) {
		Users user = new Users();

		user.setName(param.get(0));
		user.setLastName(param.get(1));
		user.setEmail(param.get(2));
		user.setPhoneNumber(param.get(3));
		user.setPassword(param.get(4));
//
//		Role role = new Role();
//		user.setActive(1);
//		user.setEmail("www.nero98.com");
//		user.setLastName("al moazen");
//		user.setName("mohamad");
//		user.setPassword("123");
////	    role.setRoleId(10);
//		role.setRole("ADMIN");
//		user.getRoles().add(role);
//		UserBank bank = new UserBank(1, 20000, "IT", 1, new Date(), 0, user);
//		bs.saveUserBank(bank);
		us.userAdd(user);
		int res = 1;
		return new ResponseEntity(res, HttpStatus.OK);

	}

	@RequestMapping("/delete-user-bank")
	public void deleteUserBank(@RequestParam long id) {
		bs.deleteAccount(id);
	}

	@RequestMapping("/update-user-bank")
	public String update(@RequestParam long idaccount, @RequestParam long userid, @RequestParam long currentbalance,
			@RequestParam String workname, @RequestParam int isenabled, @RequestParam Date registerdate,
			@RequestParam int version)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("update was called");

		UserBank bank = new UserBank(idaccount, userid, currentbalance, workname, isenabled, registerdate, version);

		int res = bs.updatingUserBank(bank);
		if (res == 1) {
			return "welcomepage";
		} else {
			return "stop";
		}

	}

	@PostMapping("/check-cache-exist")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<UserBank> checkCacheBank(@RequestBody UserBank userBank, @RequestBody float roomPrice,
			HttpServletRequest request) {

		System.out.println("The checkCacheBank service is work ........ ");
		UserBank cloneUserBank = new UserBank();
		float cacheAll = bs.DiscountCache(userBank, roomPrice);
		if (cacheAll == 0) {

			return new ResponseEntity(cloneUserBank, HttpStatus.OK);

		} else if (cacheAll == -1) {

			return new ResponseEntity(cloneUserBank, HttpStatus.OK); // put a new varible in UserBank to check the
																		// operation state

		} else {

			cloneUserBank.setCurrentbalance(cacheAll);
			return new ResponseEntity(cloneUserBank, HttpStatus.OK);

		}

	}

	@PostMapping("/discount-account")
	@ResponseBody
	public ResponseEntity<Integer> discountUserAccount(@RequestBody List<String> param) {

		System.out.println("ssssssssssssssssssssssssssss");
		String email = param.get(0);
		float dis = Float.valueOf(param.get(1));
		float priceOrginal = Float.valueOf(param.get(2));
		long userId = Long.valueOf(param.get(3));
		System.out.println("The dis is : .............." + dis);
		System.out.println("The priceOrginal is : .............." + priceOrginal);
		Users user = us.findUserByEmail(email);
		System.out.println("The user is : .............." + user.getEmail());
		System.out.println("The getUserBank is : .............." + user.getUserBank());
		int check = 0;

		float disValue = Math.abs(dis - priceOrginal);

		System.out.println("The End Money of the user is :" + disValue);

		Set<UserBank> userAccount = user.getUserBank();
		ArrayList<UserBank> userBank = new ArrayList<UserBank>(userAccount);
		UserBank userBanks = new UserBank();
		userBanks = bs.getUserBankAccount(userId);
		float totalBalacneUser = userBanks.getCurrentbalance();
		System.out.println("The userAccount Size is ................:" + userAccount.size());

		userBanks.setCurrentbalance(totalBalacneUser + disValue); ///////////////////////////////// asdas;dasdasdsa//////////////////////////////here
		bs.saveUserBank(userBanks);
//		UserBank account = new UserBank();
//		for (int i = 0; i < userBank.size(); i++) {
//			if(userBank.get(i).getIdaccount() == userId) {
//				userBank.get(i).setCurrentbalance(disValue);
//				bs.saveUserBank(userBank.get(i));
//				account = userBank.get(i);
//				check = 1;
//			} 
//		}
		check = 1;

		System.out.println("The discountUserAccount service is work ........ ");
		if (check == 1) {

			return new ResponseEntity(check, HttpStatus.OK);

		} else {

			return new ResponseEntity(check, HttpStatus.NOT_FOUND); // put a new varible in UserBank to check the
																	// operation state

		}

	}

	@RequestMapping("/check-bank-account")
	@ResponseBody
	public ResponseEntity<List<String>> checkBankAccountExist(@RequestBody List<String> listInfo) {

		System.out.println("/////////////////////////////" + listInfo.get(0));
		Users user = new Users();
		String email = listInfo.get(0);
		String password = listInfo.get(1);
		long accountid = Long.valueOf(listInfo.get(2));
		float p = Float.parseFloat(listInfo.get(3));
		System.out.println("The price is :" + p);
		int ans = 0;

		List<String> res = new ArrayList<String>();

		UserBank checkAccount = bs.checkAccountInfo(email, password, accountid);

		if (checkAccount != null) {
		    user = us.findUserByEmail(email);
			System.out.println("The User Details is ....." + user.getEmail());

			

			if (checkAccount != null) {
				System.out.println("The currenct cache before ....." + checkAccount.getCurrentbalance());
				ans = bs.DiscountCache(checkAccount, p);
				if (ans == 1) {
					UserBank checkAccountnew = bs.checkAccountInfo(email, password, accountid);
					System.out.println("The currenct cache after ....." + checkAccountnew.getCurrentbalance());
					// bs.editUserBankInfo(checkAccount.getUserid(), cache)
				}
			}

		}
//		System.out.println("result :"+checkAccount.getCurrentbalance());
//		System.out.println("result :"+checkAccount.getWorkname());

		if (checkAccount == null) {
			// UserBank user = new UserBank();
			res.add(Integer.toString(ans));
			
			return new ResponseEntity(res, HttpStatus.OK);
		} else {

			res.add(Integer.toString(ans));
			
			
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}

	@PostMapping("/discount-account-user-plane")
	@ResponseBody
	public ResponseEntity<Integer> discountUserPlaneAccount(@RequestBody List<String> param) {

		System.out.println("ssssssssssssssssssssssssssss");

		String email = param.get(0);
		float totalCost = Float.valueOf(param.get(1));
		int num = Integer.valueOf(param.get(2));
		String degree = param.get(3);
		long userId = Long.valueOf(param.get(4));

		System.out.println("The totalCost is : .............." + totalCost);
		System.out.println("The email is : .............." + email);

		Users user = us.findUserByEmail(email);

		float disValue = totalCost / 2;

		System.out.println("The user is : .............." + user.getEmail());
		System.out.println("The getUserBank is : .............." + user.getUserBank());
		int check = 0;

		System.out.println("The End Money of the user is :" + disValue);

		Set<UserBank> userAccount = user.getUserBank();
		ArrayList<UserBank> userBank = new ArrayList<UserBank>(userAccount);
		UserBank userBanks = new UserBank();
		userBanks = bs.getUserBankAccount(userId);
		float totalBalacneUser = userBanks.getCurrentbalance();
		System.out.println("The userAccount Size is ................:" + userAccount.size());

		userBanks.setCurrentbalance(totalBalacneUser + disValue); ///////////////////////////////// asdas;dasdasdsa//////////////////////////////here
		bs.saveUserBank(userBanks);

		check = 1;

		System.out.println("The discountUserAccount service is work ........ ");
		if (check == 1) {

			return new ResponseEntity(check, HttpStatus.OK);

		} else {

			return new ResponseEntity(check, HttpStatus.NOT_FOUND); // put a new varible in UserBank to check the
																	// operation state

		}

	}

	@RequestMapping("/discount-bank")
	@ResponseBody
	public ResponseEntity<UserBank> DiscountBankAccount(@RequestBody List<String> param) {

		System.out.println("///////////////////////////// Size :" + param.size());

		String email = param.get(0);
		float dis = Float.valueOf(param.get(1));
		long userId = Long.valueOf(param.get(2));
		Users user = us.findUserByEmail(email);
		Set<UserBank> userAccount = user.getUserBank();
		ArrayList<UserBank> userBank = new ArrayList<UserBank>(userAccount);
		System.out.println("The userAccount Size is ................:" + userAccount.size());

		UserBank account = new UserBank();
		for (int i = 0; i < userBank.size(); i++) {
			if (userBank.get(i).getIdaccount() == userId) {
				account = userBank.get(i);
			}
		}

		return null;
	}

}
