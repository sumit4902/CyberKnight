package com.cyber.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.Entities.AuthUser;
import com.cyber.Entities.User;
import com.cyber.Payload.AuthUserDto;
import com.cyber.Repositories.AuthUserRepo;
import com.cyber.Repositories.UserRepo;
import com.cyber.Services.AuthUserService;

@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired
	private AuthUserRepo authuserrepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public AuthUserDto createUser(AuthUserDto authUser,String apiKey) {
	 
		User user = this.userRepo.findByApiKey(apiKey);
		if(user==null)
		{
			return null;
		}
		AuthUser authuser = this.modelmapper.map(authUser,AuthUser.class);
		authuser.setUser(user);
		AuthUser saveduser = this.authuserrepo.save(authuser);
		return this.modelmapper.map(saveduser,AuthUserDto.class);
	}

	@Override
	public AuthUserDto updateUser(AuthUserDto authUser, long authUserId) {
		AuthUser saveduser = this.authuserrepo.findById(authUserId).orElseThrow(()-> new RuntimeException("AuthUser Not Found"));
		saveduser.setUserName(authUser.getUserName());
		saveduser.setPassword(authUser.getPassword());
		saveduser.setEmail(authUser.getEmail());
		saveduser.setContact(authUser.getContact());
		AuthUser updateduser = this.authuserrepo.save(saveduser);
		return this.modelmapper.map(updateduser,AuthUserDto.class);
	}

	@Override
	public AuthUserDto getUserById(long authUserId) {
		 
		AuthUser saveduser = this.authuserrepo.findById(authUserId).orElseThrow(()-> new RuntimeException("AuthUser Not Found"));
		  
		return this.modelmapper.map(saveduser, AuthUserDto.class);
	}

	@Override
	public List<AuthUserDto> getAllUser() {
		List<AuthUser> allusers = this.authuserrepo.findAll();
		List<AuthUserDto> converteduser = allusers.stream().map((user)->this.modelmapper.map(allusers,AuthUserDto.class)).collect(Collectors.toList());
		return converteduser;
	}

	@Override
	public void deleteUser(long authUserId) {
		AuthUser saveduser = this.authuserrepo.findById(authUserId).orElseThrow(()-> new RuntimeException("AuthUser Not Found"));
		 this.authuserrepo.delete(saveduser);
	}

	@Override
	public List<AuthUserDto> getAuthUserByUserId(long userId) {
		User saveduser = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
		List<AuthUser> allusers = this.authuserrepo.findByUser(saveduser);
		List<AuthUserDto> converteduser = allusers.stream().map((user)->this.modelmapper.map(allusers,AuthUserDto.class)).collect(Collectors.toList());
		return converteduser;
	}

}
