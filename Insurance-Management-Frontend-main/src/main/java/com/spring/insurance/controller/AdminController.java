package com.spring.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.insurance.model.AgentModel;
import com.spring.insurance.model.CustomerModel;
import com.spring.insurance.service.ServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BCryptPasswordEncoder passencoder;
	
	String terget="";
	
	@Autowired(required = true)
	private ServiceImpl servimple;
	
	
	//Show  Agents registration form
	@RequestMapping("/addagent")
	public String addagent() {
		
		return "Agent/AgentForm";
	}
	
	
	
	
	//back from agents registration form
	@RequestMapping("/back")
	public String backt() {
		terget="";
		return "adminpanel";
	}
	
	
	
	
	//Add a new Agents
	@PostMapping("/saveAgent")
	public String saveAgent(@ModelAttribute("agent") AgentModel agent,Model m ) {
		boolean flag=true;
		String email = agent.getAgent_email();
		
		AgentModel[] allAgent = this.servimple.getAllAgent();
		CustomerModel[] allCustomer = this.servimple.getAllCustomer();
		
		for(int i=0;i<allAgent.length;i++) {
			String allEmail = allAgent[i].getAgent_email();
			if(allEmail.equals(email))
			flag=false;
		}
		
		for(int i=0;i<allCustomer.length;i++) {
			String allEmail = allCustomer[i].getCustemail();
			if(allEmail.equals(email))
			flag=false;
		}
		
		if(flag==true) {
		agent.setRole("ROLE_AGENT");
		agent.setAgent_password(passencoder.encode(agent.getAgent_password()));
		this.servimple.saveAgent(agent);
		m.addAttribute("valid","This Agent is successfully Created!");
		return "Agent/AgentForm";
		}
		else {
			m.addAttribute("invalid","Sorry! This Email: "+email+" is already used.");
			return "Agent/AgentForm";
		}
		
	}
	
	
	
	
	//get All agents info
	@GetMapping("/agentdetails")
	public String showAgent(Model m) {
		terget="Agent Details";
		
		AgentModel[] allAgent = this.servimple.getAllAgent();
		m.addAttribute("allagent", allAgent);
		m.addAttribute("terget", terget);
		
		return "adminpanel";
	}
	
	
	
	
	//for active agent
	@GetMapping("/setactive/{id}")
	public String Active(@PathVariable("id") int id, Model m) {
	
		AgentModel agentModelById = this.servimple.getAgentModelById(id);
		agentModelById.setPosition("Active");
		this.servimple.saveAgent(agentModelById);
		m.addAttribute(agentModelById);
		return "redirect:/admin/agentdetails";
	}
	
	
	
	
	
	//for deactive agent
	@GetMapping("/deactive/{id}")
	public String Dective(@PathVariable("id") int id, Model m) {
	
		AgentModel agentModelById = this.servimple.getAgentModelById(id);
		agentModelById.setPosition("DeActive");
		this.servimple.saveAgent(agentModelById);
		m.addAttribute(agentModelById);
		return "redirect:/admin/agentdetails";
	}
	
	
	
	
	
	//getting pending account
	@GetMapping("/pending")
	public String pendingCustomer(Model m) {
		terget="Pending Customers";
		m.addAttribute("terget", terget);
 CustomerModel[] allCustomer = this.servimple.getAllCustomer();
		m.addAttribute("allcustomer",allCustomer);
	
		return "adminpanel";
	}
	
	
	
	
	//to get running customers 
	@GetMapping("/custinfo")
	public String customerdetails(Model m) {
		terget="Customer Details";
		m.addAttribute("terget", terget);
 CustomerModel[] allCustomer = this.servimple.getAllCustomer();
		m.addAttribute("allcustomer",allCustomer);
	
		return "adminpanel";
	}
	
	
	
	
	
	//to accept pending customers
	@GetMapping("/accept/{id}")
	public String Accept(@PathVariable("id") int id, Model m) {
	
		CustomerModel Custid = this.servimple.getCustomerModelByCustid(id);
		Custid.setPosition("running");
		int agId = Custid.getAgent().getId();
		AgentModel agentModelById = this.servimple.getAgentModelById(agId);
		int agent_customer = agentModelById.getAgent_customer();
		
		agentModelById.setAgent_customer(agent_customer+1);
	
		this.servimple.saveCustomer(Custid);
		this.servimple.saveAgent(agentModelById);
		m.addAttribute(Custid);
		return "redirect:/admin/pending";
	}
	
	
	
	
	//to cancel pending customer
		@GetMapping("/cencel/{id}")
		public String Cencel(@PathVariable("id") int id) {
			this.servimple.deleteCustomerModelByCustid(id);
			return "redirect:/admin/pending";
		}
		
		
		
		
	//to get Claiming request
		@GetMapping("/climeRequ")
		public String climeRequ(Model m) {
			terget="Claimer";
			CustomerModel[] allCustomer = this.servimple.getAllCustomer();
			m.addAttribute("claimer",allCustomer);
			m.addAttribute("terget",terget);
			return"adminpanel";
		}
		
		
		
		//granted controller
		@RequestMapping("/granteed/{id}")
		public String granted(@PathVariable("id") int id, Model m) {
			CustomerModel customer = this.servimple.getCustomerModelByCustid(id);
			int account =(int) customer.getAccount();
			int duration = customer.getDuration();
			String position = customer.getPosition();
			String policytype = customer.getPolicytype();
			int policyAmount = customer.getPolicyAmount();
			int value=0;
		
				if(position.equals("Claime")) {
					if(duration==8) {
						value=(account/100)*50;
					}
					else if(duration==10) {
						value=(account/100)*65;
					}
					else if(duration==12) {
						value=(account/100)*80;
					}
					else if(duration==15) {
						value=(account/100)*95;	
					}
					customer.setAccount(account+value);
					customer.setPosition("disclosed");
					this.servimple.saveCustomer(customer);
					m.addAttribute("success","This Claim is Successfully Disclosed");
					}
				
				else if(position.equals("DethClaim")){
					if(duration==8) {
						value=(policyAmount/100)*50;
					}
					else if(duration==10) {
						value=(policyAmount/100)*65;
					}
					else if(duration==12) {
						value=(policyAmount/100)*80;
					}
					else if(duration==15) {
						value=(policyAmount/100)*95;	
					}
					customer.setAccount(policyAmount+value);
					customer.setPosition("disclosed");
					this.servimple.saveCustomer(customer);
					m.addAttribute("success","This DeathClaim is Successfully Disclosed");
				}
				else {
					m.addAttribute("success","You have already claimed your Assured with bonus.");
				}
			
		
	
			return"adminpanel";
		}
		
		
		
		
		//agent search
		@GetMapping("/search")
		public String SearchAgents(@RequestParam("sarc") String id,Model m) {
			int nid=0;
			if(id=="") {
				terget="Bad Request! Due to Empty value";
				m.addAttribute("terget",terget);
				return "adminpanel";
			}
			else
				nid=Integer.parseInt(id);
			terget="Searching Result";
			m.addAttribute("terget",terget);
			AgentModel sr = this.servimple.getAgentModelById(nid);
			m.addAttribute("sr",sr);
			return "adminpanel";

		}
		
		
		
		
		//customer search
		@GetMapping("/searchCustomer")
		public String SearchCustomer(@RequestParam("sarc") String id,Model m) {
			int nid=0;
			if(id=="") {
				terget="Bad Request! Due to Empty value";
				m.addAttribute("terget",terget);
				return "adminpanel";
			}
			else
				nid=Integer.parseInt(id);
			terget="Searching Customer Result";
			m.addAttribute("terget",terget);
			 CustomerModel Custid = this.servimple.getCustomerModelByCustid(nid);
			m.addAttribute("csr",Custid);
			return "adminpanel";

		}
		
		
		
		
//		//for logout
//		@RequestMapping("/logou")
//		public String logou(@ModelAttribute("ma") AdminModel adm,Model m) {
//
//			adm.setEmail("");
//			adm.setPassword("");
//		
//			return "index";
//		}
	
}

