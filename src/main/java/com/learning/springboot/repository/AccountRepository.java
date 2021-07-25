package com.learning.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.springboot.beans.de.AccountDE;

@Repository
public interface AccountRepository extends CrudRepository<AccountDE, Integer>{
	
	public Iterable<AccountDE> findAllByDeletedFalse();

}
