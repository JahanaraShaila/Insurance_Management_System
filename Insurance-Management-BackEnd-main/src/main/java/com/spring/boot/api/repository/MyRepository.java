package com.spring.boot.api.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.boot.api.model.Agent;



public interface MyRepository extends CrudRepository<Agent, Integer>{

	public Agent findById(int id); 

	@Query("select a from Agent a where a.agent_email=:email")
	public Agent getAgentByAgentEmail(@Param("email") String email); 
}
