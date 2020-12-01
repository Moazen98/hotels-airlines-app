package com.zeon.UserPortable.Service;

import com.zeon.UserPortable.Model.User;

public class Oparations {
	
	
	public Oparations() {
		
	}
	
	public User findUserById(User[] user , long id) {
		
		User userObj = new User();
		
		for(int i = 0 ; i < user.length ; i++) {
			
			if(user[i].getId() == id) {
				System.out.println("I Find The User :"+id);
				System.out.println(user[i].toString());
				userObj = user[i];
				break;
			}else {
				System.out.println("The user not here .......");
			}
		}
		return userObj;
	}
	

}
