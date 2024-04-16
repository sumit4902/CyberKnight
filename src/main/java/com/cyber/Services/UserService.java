package com.cyber.Services;

import java.util.List;

import com.cyber.Payload.ApiResponse;
import com.cyber.Payload.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto ,long userId);
	UserDto getUserById (long userId);
	List<UserDto> getAllUser();
	void deleteUser (long userId);
	
	// issue Api Key to the developer for using our services //
	ApiResponse issueApiKey(long userId);
}
