package com.spring.boot.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity


public class Customer {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int custid;
	private String custname;
	private String custaddress;
	private long custnumber;
	private String custgender;
	private String policytype;
	private String custpassword;
	private String custemail;
	private int duration;
	private String prim_method;
	private int payed_prim;
	private double account;
	private int policyAmount;
	private String position;
	private String role;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Agent agent;


	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public String getCustpassword() {
		return custpassword;
	}





	public void setCustpassword(String custpassword) {
		this.custpassword = custpassword;
	}

	public String getCustemail() {
		return custemail;
	}

	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}

	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCustaddress() {
		return custaddress;
	}
	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}
	public long getCustnumber() {
		return custnumber;
	}
	public void setCustnumber(long custnumber) {
		this.custnumber = custnumber;
	}
	public String getCustgender() {
		return custgender;
	}
	public void setCustgender(String custgender) {
		this.custgender = custgender;
	}
	public String getPolicytype() {
		return policytype;
	}
	public void setPolicytype(String policytype) {
		this.policytype = policytype;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getPrim_method() {
		return prim_method;
	}


	public void setPrim_method(String prim_method) {
		this.prim_method = prim_method;
	}


	public int getPayed_prim() {
		return payed_prim;
	}


	public void setPayed_prim(int payed_prim) {
		this.payed_prim = payed_prim;
	}


	public double getAccount() {
		return account;
	}


	public void setAccount(double account) {
		this.account = account;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public Agent getAgent() {
		
		return agent;
	}


	public void setAgent(Agent agent) {
		this.agent = agent;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public int getPolicyAmount() {
		return policyAmount;
	}


	public void setPolicyAmount(int policyAmount) {
		this.policyAmount = policyAmount;
	}




	
}
