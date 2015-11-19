package com.generalproject.repo;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.generalproject.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
	List<Customer> findByLastName(String lastName);
}