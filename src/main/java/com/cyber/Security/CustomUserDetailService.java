package com.cyber.Security;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.cyber.Payload.UserDto;


@Component
public class CustomUserDetailService implements UserDetailsService  {

	@Autowired
	private com.cyber.Repositories.UserRepo userrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	private JwtAuthResponse response = new JwtAuthResponse();
	
	 com.cyber.Entities.User user = null;
	 
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
		
	   
		System.out.println(username);
	    
		if(username.contains("@")&&username.contains(".com"))
		{
			 // there is a method to fetch user from database by email id //
			 user =	this.userrepo.findByEmail(username);
		}
		else {
			// there is a method to fetch user from database by contact number //
			 user =	this.userrepo.findByContact(username);
		}
		
		
		
			UserDetails userdetails = User.withUsername(user.getEmail()) 
				    .password(user.getPassword())
				    .build();
				   
		return userdetails;
	}
	
	    public JwtAuthResponse loadToken()
	   {
	       	response.setId(user.getUserId());
	       	response.setName(user.getUserName());
	       	response.setUserdata(this.modelmapper.map(user, UserDto.class));
	       	return  response;
	   }

		
}
