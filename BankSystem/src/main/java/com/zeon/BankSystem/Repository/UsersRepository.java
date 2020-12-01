package com.zeon.BankSystem.Repository;



import org.springframework.data.repository.CrudRepository;  
import org.springframework.stereotype.Repository;

import com.zeon.BankSystem.Model.Users;



@Repository
public interface UsersRepository extends CrudRepository<Users,Integer>{
	
	public Users findUserById(int id);
	public Users findUserByEmail(String email);
	//public Set<UserBank> findUserBank
}
