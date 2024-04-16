package com.cyber.Services;

import java.util.List;

import com.cyber.Payload.AuthUserDto;

public interface AuthUserService {

	AuthUserDto createUser(AuthUserDto authUser ,String apiKey);
	AuthUserDto updateUser(AuthUserDto authUser ,long  authUserId);
	AuthUserDto getUserById(long authUserId);
	List<AuthUserDto> getAllUser();
	void deleteUser(long authUserId);
	
	// this is for one to many relationship
	List<AuthUserDto>getAuthUserByUserId(long userId);
	
	
}
