package com.cyber.Services;

import com.cyber.Payload.ImageDto;

public interface ImageService {

	ImageDto createImageByuserId(String filename,long userId);
	
	ImageDto createImageByblogId(String filename,long blogId );
	
	ImageDto updateImage(String filename ,long imageId);
	
	ImageDto getByuserId(long userId);
	
	ImageDto getImageByblogId(long blogId);
	
	void deleteImage(long imageId);
}
