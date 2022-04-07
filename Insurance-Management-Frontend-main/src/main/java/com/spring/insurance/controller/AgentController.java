package com.spring.insurance.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.insurance.model.AgentModel;
import com.spring.insurance.model.CustomerModel;
import com.spring.insurance.service.ServiceImpl;

@Controller
@RequestMapping("/agent")
public class AgentController {

	String terget="";
	
	
//service class	
	@Autowired(required = true)
	private ServiceImpl servimple;
	
	
//Password security	
	@Autowired
	private BCryptPasswordEncoder passencoder;
	
	
	
	
//common method for all
	@ModelAttribute
	public void addCommonData(Model m, Principal princpl) {
		String name = princpl.getName();
		AgentModel byEmail = this.servimple.ByEmail(name);
		m.addAttribute("id", byEmail.getId());
	}
	
	
	
	
//agent Login Handler
	@RequestMapping("/agentlogin")
	public String agentLogin(Model m, Principal princpl) {
		String name = princpl.getName();
		System.out.println(name);
		AgentModel byEmail = this.servimple.ByEmail(name);
		m.addAttribute("id", byEmail.getId());
		return "Agent/agentPanel";
	}
	
	
	
	
//getting own all Data
	@RequestMapping("/manage/{id}")
	public String manageAccount(@PathVariable("id") int id, Model m, Principal princpl) {
		AgentModel agentModelById = this.servimple.getAgentModelById(id);
		String position = agentModelById.getPosition();
		if(position.equals("Active")) {
		terget = agentModelById.getAgent_name();
		m.addAttribute("myAccount", agentModelById);
		m.addAttribute("terget", terget);
		return "Agent/agentPanel";
		}
		else {
			
			m.addAttribute("sorry", "Sorry! Your Id is Deactivated. To activate your id, Please Contact with admin.");
			return "Agent/agentPanel";
		
		}
	
	}
	
	
//showing Edit form
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, Model m, Principal princpl) {
		AgentModel agentModelById = this.servimple.getAgentModelById(id);
		m.addAttribute("myagent", agentModelById);
		return "Agent/updateForm";
	}
	
	
	
	
//updating agent data
	@PostMapping("/update")
	public String saveAgent(@ModelAttribute("agent") AgentModel agent, Model m, Principal princpl) {

		// agent.setAgent_password(passencoder.encode(agent.getAgent_password()));
		this.servimple.updateAgent(agent, agent.getId());
		terget = agent.getAgent_name();
		m.addAttribute("myAccount", agent);
		m.addAttribute("terget", terget);
		return "Agent/agentPanel";
	}
	
	
	
	
//to Show new Customer Form
	@RequestMapping("/addCustomers/{id}")
	public String customerForm(@PathVariable("id") int id,Model m, Principal princpl) {
		AgentModel agentById = this.servimple.getAgentModelById(id);
		if(agentById.getPosition().equals("Active")) {
	//	AgentModel[] allAgent = this.servimple.getAllAgent();
		
			m.addAttribute("allagntid", agentById.getId());
			return "Customer/customerForm";
			
		}
		else {
			m.addAttribute("sorry", "Sorry! Your Id is Deactivated. To activate your id Please Contact with admin.");
			return "Agent/agentPanel";
		}
		
	}
	
	
	
//to submit Customer Form
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @RequestBody @ModelAttribute("customer") CustomerModel customer, Model m,
			Principal princpl) {
		boolean flag = true;
		String email = customer.getCustemail();
		AgentModel[] allAgent = this.servimple.getAllAgent();
		CustomerModel[] allCustomer = this.servimple.getAllCustomer();

		for (int i = 0; i < allAgent.length; i++) {
			String allEmail = allAgent[i].getAgent_email();
			if (allEmail.equals(email))
				flag = false;
		}

		for (int i = 0; i < allCustomer.length; i++) {
			String allEmail = allCustomer[i].getCustemail();
			if (allEmail.equals(email))
				flag = false;
		}

		if (flag == true) {
			if (customer.getPolicyAmount() > 50000) {
				customer.setRole("ROLE_USER");
				customer.setCustpassword(passencoder.encode(customer.getCustpassword()));
				this.servimple.saveCustomer(customer);
				m.addAttribute("allagntid", allAgent);
				m.addAttribute("valid", "This customer is successfully Created!");
				return "Customer/customerForm";
			} else {
				m.addAttribute("allagntid", allAgent);
				m.addAttribute("invalid", "Sorry! Your Policy Amount less then 50000 tk");
				return "Customer/customerForm";
			}
		}

		else {
			m.addAttribute("allagntid", allAgent);
			m.addAttribute("invalid", "Sorry this Email: " + email + " is already used");
			return "Customer/customerForm";
		}

	}
	
	
	
	
//customer Details by agent Id	
	@RequestMapping("/myCustomers/{id}")
	public String myCustomers(@PathVariable("id") int id, Model m, Principal princpl) {
		
		AgentModel agentById = this.servimple.getAgentModelById(id);
		if(agentById.getPosition().equals("Active")) {
			
		CustomerModel[] allCustomer = this.servimple.customerByAgent(id);
		terget = "Customer Details";
		m.addAttribute("mycustomr", allCustomer);
		m.addAttribute("terget", terget);
		return "Agent/CustomerByAgent";
		}
		
		else {
			m.addAttribute("sorry", "Sorry! Your Id is Deactivated. To activate your id Please Contact with Admin.");
			return "Agent/agentPanel";
		}
		
	}
	
	
	
//back button 
	@RequestMapping("/back")
	public String back(Model m, Principal princpl) {
		return "Agent/agentPanel";

	}

	
}
