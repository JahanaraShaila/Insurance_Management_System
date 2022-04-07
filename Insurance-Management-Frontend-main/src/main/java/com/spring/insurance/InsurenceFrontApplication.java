package com.spring.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InsurenceFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurenceFrontApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	  @Bean
	    public HttpFirewall allowUrlSemicolonHttpFirewall() {
	        StrictHttpFirewall firewall = new StrictHttpFirewall();
	        firewall.setAllowSemicolon(true);
	        firewall.setAllowUrlEncodedDoubleSlash(true);
	        return firewall;
	    }

}
