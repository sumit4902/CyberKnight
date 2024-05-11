package com.cyber.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
	
	private long blogId;
	
	private String  crimeType;

	private String title;
	private String commitPlace;
	
	private String description;
    private UserDto user;
	
	private ImageDto image ;
}
