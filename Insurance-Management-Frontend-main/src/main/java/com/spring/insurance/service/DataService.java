package com.spring.insurance.service;
import com.spring.insurance.model.AgentModel;
import com.spring.insurance.model.CustomerModel;

public interface DataService {

	//agent jpa service
	AgentModel[] getAllAgent();
    void saveAgent(AgentModel agent);
   
    
    AgentModel getAgentModelById(int id);
    AgentModel ByEmail(String email);
    void deleteAgentModeById(int id);
    void updateAgent(AgentModel agent, int id);
    
    
    //Customer jpa service
    CustomerModel[] getAllCustomer();
    CustomerModel[] customerByAgent(int id);
    void saveCustomer(CustomerModel customer);
    CustomerModel getCustomerModelByCustid(int id);
    CustomerModel customerByEmail(String email);
    void deleteCustomerModelByCustid(int id);
    void updateCustomer(CustomerModel customer, int id);
    
    
}
