package com.cyber.ServiceImpl;

import java.util.Date;


import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cyber.Entities.User;
import com.cyber.Payload.ApiResponse;
import com.cyber.Payload.UserDto;
import com.cyber.Repositories.UserRepo;
import com.cyber.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		userDto.setDate(new Date());  
		User user = this.modelmapper.map(userDto,User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User saveduser = this.userRepo.save(user);
	    return this.modelmapper.map(saveduser,UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, long userId) {
		User user  = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
		if(userDto.getUserName()!=null)
		{
			user.setUserName(userDto.getUserName());
		}
		if(userDto.getContact()!=null)
		{
			user.setContact(userDto.getContact());
		}
		if(userDto.getEmail()!=null)
		{
			user.setEmail(userDto.getEmail());
		}
		if(userDto.getPassword()!=null)
		{
			user.setPassword(passwordEncoder.encode(userDto.getPassword() ));
		}
		if(userDto.getAddress()!=null)
		{
			user.setAddress(userDto.getAddress());
		}
		User updatedUser = this.userRepo.save(user);
		return this.modelmapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(long userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
		return this.modelmapper.map(user,UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> allUser = this.userRepo.findAll();
		List<UserDto> convertedUser = allUser.stream().map((user)-> this.modelmapper.map(user,UserDto.class)).collect(Collectors.toList());
		return convertedUser;
	}

	@Override
	public void deleteUser(long userId) {
		User user  = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
		this.userRepo.delete(user);
	}

	
	
	// Issue Api key //
	
	
	@Override
	public ApiResponse issueApiKey(long userId) {
		User user  = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
		user.setApiKey(generateRandomString(20));
		User updatedUser = this.userRepo.save(user);
		
		return new ApiResponse(updatedUser.getApiKey(),true);
	}
	
	 private  String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	 public  String generateRandomString(int length) {
	        Random random = new Random();
	        StringBuilder sb = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            int randomIndex = random.nextInt(CHARACTERS.length());
	            sb.append(CHARACTERS.charAt(randomIndex));
	        }
	        return sb.toString();
	    }

}
