package com.spring.insurance.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AgentModel {

	private int id;
	
	@NotBlank(message = "Name is mandatory")
	private String agent_name;
	
	@NotBlank(message = "Address is mandatory")
	private String agen_address;
	
	@Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",message = "Email invalid ")
	@NotBlank(message = "Email is mandatory")
	private String agent_email;
	
	@NotBlank(message = "password is mandatory")
	private String agent_password;
	
	private long agent_number;
	private int tk_percustomer;
	private int  agent_customer;
//	private double agent_account;
	private double primium_bonus;
	private String position;
	private String role;
	public AgentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getAgen_address() {
		return agen_address;
	}
	public void setAgen_address(String agen_address) {
		this.agen_address = agen_address;
	}
	public String getAgent_email() {
		return agent_email;
	}
	public void setAgent_email(String agent_email) {
		this.agent_email = agent_email;
	}
	public String getAgent_password() {
		return agent_password;
	}
	public void setAgent_password(String agent_password) {
		this.agent_password = agent_password;
	}
	public long getAgent_number() {
		return agent_number;
	}
	public void setAgent_number(long agent_number) {
		this.agent_number = agent_number;
	}
	public int getTk_percustomer() {
		return tk_percustomer;
	}
	public void setTk_percustomer(int tk_percustomer) {
		this.tk_percustomer = tk_percustomer;
	}
	public int getAgent_customer() {
		return agent_customer;
	}
	public void setAgent_customer(int agent_customer) {
		this.agent_customer = agent_customer;
	}


	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	public double getPrimium_bonus() {
		return primium_bonus;
	}
	public void setPrimium_bonus(double primium_bonus) {
		this.primium_bonus = primium_bonus;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AgentModel [id=" + id + ", agent_name=" + agent_name + ", agen_address=" + agen_address
				+ ", agent_email=" + agent_email + ", agent_password=" + agent_password + ", agent_number="
				+ agent_number + ", tk_percustomer=" + tk_percustomer + ", agent_customer=" + agent_customer
				+ ", primium_bonus=" + primium_bonus + ", position=" + position + "]";
	}
	


	
}
