package com.spring.boot.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.boot.api.model.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
	public Customer findByCustid(int custid); 
	public List <Customer> findByAgentId(int agntid);
	
	@Query("select c from Customer c where  c.custemail=:email")
	public Customer getCustomerByCustEmail(@Param("email") String email);
	
}
