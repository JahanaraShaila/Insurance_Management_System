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

import com.spring.boot.api.dao.AgentDao;
import com.spring.boot.api.model.Agent;

@RestController
@RequestMapping("/api")
public class AgentControler {
	@Autowired
	private AgentDao agentdao;
	
	
	//get all agent
	@GetMapping("/agent")
	public List<Agent> Allagent(){
		List<Agent> getallagent = this.agentdao.getallagent();
		return getallagent;
	}
	
	
	//get single agent
	@GetMapping("/agent/{id}")
	public Agent myagent(@PathVariable("id") int id){
		 
		return this.agentdao.getagent(id);
	}
	
	//get agent by email
	@GetMapping("/myagent/{email}")
	public Agent ByEmail(@PathVariable("email") String email) {
		
		Agent agentByEmail = this.agentdao.getAgentByEmail(email);
		return agentByEmail;
	}
	
	
	//save an agent
	@PostMapping("/agent")
	public Agent saveagent(@RequestBody  Agent agent) {
		
		Agent saveagent = this.agentdao.saveagent(agent);
		return saveagent;
	}

	
	//delete an agent
	@DeleteMapping("/agent/{id}")
	public void deletagent(@PathVariable("id") int id) {
		this.agentdao.delet(id);	
	}
	
	
	//update an agent
	@PutMapping("/agent/{id}")
	public void editagent(@RequestBody Agent agent,@PathVariable("id") int id) {
		
		this.agentdao.updateagent(agent, id);
	}
		
}
