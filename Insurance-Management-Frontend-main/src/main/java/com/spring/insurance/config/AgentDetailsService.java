package com.spring.insurance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.insurance.model.AgentModel;
import com.spring.insurance.model.CustomerModel;
import com.spring.insurance.service.ServiceImpl;

public class AgentDetailsService implements UserDetailsService{
	
	@Autowired
	private ServiceImpl agentRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AgentModel agent = this.agentRepo.ByEmail(username);
		CustomerModel customer=this.agentRepo.customerByEmail(username);
		
		if(agent!=null & customer==null) {
			
			CustomAgentDetails cagentDetls=new CustomAgentDetails(agent);
			return cagentDetls;
		}
		else if(customer!=null & agent==null) {
			CustomCustomerDetails customerDetails=new CustomCustomerDetails(customer);
			return customerDetails;
		}
		
		else
			
			throw new UsernameNotFoundException("User Name not found!");
		
	}

}
