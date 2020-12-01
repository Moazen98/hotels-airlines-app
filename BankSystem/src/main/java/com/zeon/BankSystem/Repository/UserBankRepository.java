package com.zeon.BankSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zeon.BankSystem.Model.UserBank;

@Repository
public interface UserBankRepository extends CrudRepository<UserBank, Long>{
	

	public UserBank findUserBankByidaccount(long id);
	
}
