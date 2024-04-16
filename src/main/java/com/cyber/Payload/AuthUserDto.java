package com.cyber.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {

	private  long    authUserId;
	private  String  userName;
	private String email;
	private String contact;
	private  String  password;
}
