package com.spring.boot.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.api.dao.CustomerDao;
import com.spring.boot.api.model.Agent;
import com.spring.boot.api.model.Customer;

@RestController
@RequestMapping("/api")
public class CustomerControler {

	@Autowired
	private CustomerDao custdao;
	
	//get all agent
		@GetMapping("/customer")
		public List<Customer> Allcustomer(){
			List<Customer> getallcustomer = this.custdao.getallcustomer();
			return getallcustomer;
		}
		//get single Customer
		@GetMapping("/customer/{id}")
		public Customer myacustomer(@PathVariable("id") int id){
			 
			return this.custdao.getcustomer(id);
		}
		
		@GetMapping("/mycustomer/{email}")
		public Customer myCustomer(@PathVariable("email") String email) {
			
			return this.custdao.getcustomerByemail(email);
		}
		
		
		//get single Customer by agent
		@GetMapping("/customerbyagent/{id}")
		public List <Customer> customerbyagent(@PathVariable("id") int id){
			List<Customer> customerbyagent = this.custdao.customerbyagent(id);
			return customerbyagent;
		}
		
		//save an customer
		@PostMapping("/customer")
		public Customer savecustomer(@RequestBody  Customer customer) {
			
			Customer savecust = this.custdao.savecustomer(customer);
			return savecust;
		}

		//delete an customer
		@DeleteMapping("/customer/{id}")
		public void deletcustomer(@PathVariable("id") int id) {
			this.custdao.delet(id);	
		}
		//update an customer
		@PutMapping("/customer/{id}")
		public void editcustomer(@RequestBody Customer customer,@PathVariable("id") int id) {
			
			this.custdao.updatecustomer(customer, id);
		}
			
}
