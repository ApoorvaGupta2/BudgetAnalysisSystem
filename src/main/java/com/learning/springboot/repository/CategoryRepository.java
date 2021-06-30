package com.learning.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.springboot.beans.de.CategoryDE;
import com.learning.springboot.type.TransactionType;

	
@Repository
public interface CategoryRepository extends CrudRepository<CategoryDE, Integer> {

	public Iterable<CategoryDE> findAllByDeletedFalse();
	public Iterable<CategoryDE> findAllByDeletedFalseAndType(TransactionType type);
}

