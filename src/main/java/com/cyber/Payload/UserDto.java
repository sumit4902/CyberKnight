package com.cyber.Payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cyber.Entities.AuthUser;
import com.cyber.Entities.Blog;
import com.cyber.Entities.Image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private long userId;
	
	private String userName;
	
	private String contact;
	
	private String email;
	
	private String password;
	
	private String address;
	private String apiKey;
	private Date date;
	
	private List<Blog> blogs = new ArrayList<Blog>(); 
	
	private List<AuthUser> authUsers = new ArrayList<>();
	
	private Image image = new Image();
}
