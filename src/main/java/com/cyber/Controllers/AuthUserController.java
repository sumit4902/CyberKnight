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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.Payload.ApiResponse;
import com.cyber.Payload.AuthUserDto;
import com.cyber.Services.AuthUserService;

@RestController()
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AuthUserController {

	@Autowired 
	private AuthUserService authUserservice;
	
	@PostMapping("/user/authUser/create")
	ResponseEntity<AuthUserDto> createAuthUser(@RequestBody AuthUserDto authUserDto,
			@RequestParam(name = "apiKey",defaultValue ="",required = true) String apiKey) // kam baki hai
	{
		AuthUserDto user  = this.authUserservice.createUser(authUserDto,apiKey);
		return new ResponseEntity<AuthUserDto>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("/authUser/update/{authUserId}")
	ResponseEntity<AuthUserDto>updateUser(@RequestBody AuthUserDto authUserDto,@PathVariable long authUserId)
	{
	   AuthUserDto updateduser = this.authUserservice.updateUser(authUserDto, authUserId);
	   return new ResponseEntity<AuthUserDto>(updateduser,HttpStatus.OK);
		
	}
	@GetMapping("/authUser/{authUserId}")
	ResponseEntity<AuthUserDto>getAuthUserById(@RequestBody @PathVariable long authUserId)
	{
		AuthUserDto user = this.authUserservice.getUserById(authUserId);
		 return new ResponseEntity<AuthUserDto>(user,HttpStatus.OK);
		
	}
	
	@GetMapping("/authUser/all")
	ResponseEntity<List<AuthUserDto>>getAuthUser()
	{
		List<AuthUserDto> user = this.authUserservice.getAllUser();
	 return new ResponseEntity<List<AuthUserDto>>(user,HttpStatus.OK);	
	}
	
	@DeleteMapping("/authUser/delete/{authUserId}")
	ResponseEntity<ApiResponse>deleteAuthUserById(@RequestBody @PathVariable long authUserId)
	{
		this.authUserservice.deleteUser(authUserId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("AuthUser Deleted Successfully...",true),HttpStatus.OK);
	}
	
	
	// it's for relationship 
	@GetMapping("user/{userId}/authUser/all")
	ResponseEntity<List<AuthUserDto>>getAuthUserByUserId(@RequestBody @PathVariable long userId)
	{
		List<AuthUserDto> user = this.authUserservice.getAuthUserByUserId(userId);
	 return new ResponseEntity<List<AuthUserDto>>(user,HttpStatus.OK);	
	}
	
	
}
