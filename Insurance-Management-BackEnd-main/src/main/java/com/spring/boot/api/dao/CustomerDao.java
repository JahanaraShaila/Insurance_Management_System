package com.spring.boot.api.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.boot.api.model.Customer;
import com.spring.boot.api.repository.CustomerRepo;



@Component
public class CustomerDao {

	@Autowired
	private CustomerRepo custrepo;
	
	

	
	//get all customer
		public List<Customer> getallcustomer(){
			List<Customer> findAll =(List<Customer>) this.custrepo.findAll();
			
			return findAll;
		}
		
		//get single customer
		public Customer getcustomer(int id){
			 
			return this.custrepo.findByCustid(id);
		}
		
		//get single customer
		public Customer getcustomerByemail(String email){
			 
			return this.custrepo.getCustomerByCustEmail(email);
		}
		
		//get single customer using agent id
		public List <Customer> customerbyagent(int id){
//			List<Customer> findbyagent=new ArrayList<>();
			List<Customer> findByAgentId = custrepo.findByAgentId(id);
			return findByAgentId;
		}
		
		//create a customer
		public Customer savecustomer(Customer customer) {
			Customer save = this.custrepo.save(customer);
			return save;
		}
		
		//delete customer
		public void delet(int id) {
			this.custrepo.deleteById(id);
		}
		
		//Update customer
		public void updatecustomer(Customer customer,int id) {
			customer.setCustid(id);
			this.custrepo.save(customer);
		}
}
