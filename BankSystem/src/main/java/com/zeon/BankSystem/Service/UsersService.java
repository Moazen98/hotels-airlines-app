package com.zeon.BankSystem.Service;



import java.sql.Connection;   
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeon.BankSystem.Model.Users;
import com.zeon.BankSystem.Repository.UsersRepository;



@Service
public class UsersService {

	static Connection Mycon = null;
	static String Myurl = "jdbc:mysql://localhost:3306/userdb";
	static String username = "root";
	static String password = "123456";
	Statement staement;

	@Autowired
	private final UsersRepository usersRepository;

	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Transactional
	public void userAdd(Users user) {
		System.out.println("The user is :" + user);
		usersRepository.save(user);
	}

	@Transactional
	public List<Users> getAllUser() {

		List<Users> users = new ArrayList<Users>();
		for (Users p : usersRepository.findAll()) {
			users.add(p);
			System.out.println("The plane is :" + p.getEmail());
		}
		return users;
	}

	@Transactional
	public void deleteUserById(int id) {
		usersRepository.deleteById(id);
	}
	
	public void editUserId(Users user) {
		usersRepository.deleteById(user.getId());
		usersRepository.save(user);
	}

	@Transactional
	public Users findUserById(int id) {
		
		return usersRepository.findUserById(id);
	}
	
	@Transactional
	public Users findUserByEmail(String email) {
		
		Users user = usersRepository.findUserByEmail(email);
		//System.out.println("The UserBank size is :......"+user.getUserBank().size());
		return user;
	}
	

	
//	@Transactional
//	public int editUserIddb(Users user)
//			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//
//		int sqlRes;
//		Class.forName("com.mysql.jdbc.Driver").newInstance();
//		Mycon = DriverManager.getConnection(Myurl, username, password);
//		staement = Mycon.createStatement();
//
//		
//		sqlRes = staement.executeUpdate(" update userstable set id='" + user.getId() + "', username='" + user.getUsername()
//				+ "'," + "firstname='" + user.getFirstname() + "',lastname='" + user.getLastname() + "' ," + "age='"
//				+ user.getAge() + "',password='" + user.getPassword() + "',email='" + user.getEmail() + "',phone='"
//				+ user.getPhone() + "',accepted='" + user.getAccepted() + "',enabled='" + user.isEnabled()
//				 + "' ,version='" + (user.getVersion() + 1) + "' where id='"
//				+ user.getId() + "' and version ='" + user.getVersion() + "'   ");
//
//		return sqlRes;
//	}

}
