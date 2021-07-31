package com.learning.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.springboot.beans.de.UserDE;

@Repository
public interface UserRepository extends CrudRepository<UserDE, Integer> {

	public Iterable<UserDE> findAllByDeletedFalse();

}
