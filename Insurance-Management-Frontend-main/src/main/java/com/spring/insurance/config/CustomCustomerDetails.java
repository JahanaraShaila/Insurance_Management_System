package com.spring.insurance.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.insurance.model.CustomerModel;

public class CustomCustomerDetails implements UserDetails{

	private CustomerModel customer;
	
	
	
	public CustomCustomerDetails(CustomerModel customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrAuthority=new SimpleGrantedAuthority(customer.getRole());
		return List.of(simpleGrAuthority);
	}

	@Override
	public String getPassword() {
		
		return customer.getCustpassword();
	}

	@Override
	public String getUsername() {
		
		return customer.getCustemail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
