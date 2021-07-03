package com.learning.springboot.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.springboot.beans.de.TransactionDE;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionDE, Integer> {

	public Iterable<TransactionDE> findAllByDeletedFalse();
	
	public Iterable<TransactionDE> findAllByCategoryId(int categoryId);

}
