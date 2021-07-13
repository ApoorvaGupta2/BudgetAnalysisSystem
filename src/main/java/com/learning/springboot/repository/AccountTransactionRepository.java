package com.learning.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springboot.beans.de.AccountTransactionDE;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransactionDE,Integer>{
	
	// 		Get latest record for given account id
	public Iterable<AccountTransactionDE> findTopByAccountIdOrderByIdDesc(int id);
}
