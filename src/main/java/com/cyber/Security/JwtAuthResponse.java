package com.cyber.Security;


import com.cyber.Payload.UserDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {

	private long id;
	private String name;
	private String JwtToken;
	private UserDto userdata;
	
	
}