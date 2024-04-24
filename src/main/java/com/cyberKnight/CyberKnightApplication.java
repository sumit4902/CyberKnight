package com.cyberKnight;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cyber.Payload.BlogResponse;

@SpringBootApplication
@EntityScan("com.cyber.Entities")
@ComponentScan("com.cyber")
@EnableJpaRepositories("com.cyber.Repositories")
public class CyberKnightApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyberKnightApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper()
	{
	  return new ModelMapper();
	}
	
	@Bean
	BlogResponse blogResponse()
	{
		return new BlogResponse();
	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	
	 
	 
}
