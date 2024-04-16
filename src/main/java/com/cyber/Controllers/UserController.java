package com.cyber.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.Payload.ApiResponse;
import com.cyber.Payload.UserDto;
import com.cyber.Services.UserService;

@RestController()
@RequestMapping("/api/v1")
@CrossOrigin("*")  // Apis Allowed for All Domain 
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user/create")
	ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto user = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(user,HttpStatus.CREATED);
	}
	@PutMapping("/user/update/{userId}")
	ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable long userId)
	{
	    UserDto updatedUser = this.userService.updateUser(userDto, userId);
	    return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	ResponseEntity<UserDto> getByUserId(@RequestBody @PathVariable long userId)
	{
		UserDto user = this.userService.getUserById(userId);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@GetMapping("user/all")
	ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> alluser = this.userService.getAllUser();
		return new ResponseEntity<List<UserDto>>(alluser,HttpStatus.OK);	
	}
	
	@DeleteMapping("/user/delete/{userId}")
	ResponseEntity<ApiResponse> deleteUser(@RequestBody @PathVariable long userId)
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully..",true),HttpStatus.OK);
	}
	
	// issue Api Key //
	@GetMapping("/user/{userId}/apiKey")
	ResponseEntity<ApiResponse> getApiKey(@RequestBody  @PathVariable long userId)
	{
		ApiResponse response = this.userService.issueApiKey(userId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
}
