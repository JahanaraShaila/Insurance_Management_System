package com.spring.boot.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.boot.api.model.Agent;
import com.spring.boot.api.repository.MyRepository;

@Component
public class AgentDao {

	@Autowired
	private MyRepository repo;
	
	//get all agent
	public List<Agent> getallagent(){
		List<Agent> findAll =(List<Agent>) this.repo.findAll();
		
		return findAll;
	}
	
	//get single agent
	public Agent getagent(int id){
		 
		return this.repo.findById(id);
	}
	
	//get  agent by email
	public Agent getAgentByEmail(String email) {
		Agent agentByEmail = this.repo.getAgentByAgentEmail(email);
		return agentByEmail;
	}
	
	//create a agent
	public Agent saveagent(Agent agent) {
		Agent save = this.repo.save(agent);
		return save;
	}
	
	//delet agent
	public void delet(int id) {
		this.repo.deleteById(id);
	}
	
	//Update agent
	public void updateagent(Agent agent,int id) {
		agent.setId(id);
		this.repo.save(agent);
	}
}

