package com.spring.insurance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfigaration extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDtailServic() {
		
		return new AgentDetailsService();
	}
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider dauthProvider=new DaoAuthenticationProvider();
		dauthProvider.setUserDetailsService(this.userDtailServic());
		dauthProvider.setPasswordEncoder(this.passwordEncoder());
		return dauthProvider;
	}

	//configure Method
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/agent/**").hasRole("AGENT")
		.antMatchers("/customer/**").hasRole("USER")
		
		.antMatchers("/**").permitAll().and().formLogin().loginPage("/signin")
		
		.and().csrf().disable();
		
	}
	
	
	
	
	
}
