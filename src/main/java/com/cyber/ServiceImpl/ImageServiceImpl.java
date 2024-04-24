package com.cyber.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cyber.Entities.Blog;
import com.cyber.Entities.Image;
import com.cyber.Entities.User;
import com.cyber.Payload.ImageDto;
import com.cyber.Repositories.BlogRepo;
import com.cyber.Repositories.UserRepo;
import com.cyber.Repositories.imageRepo;
import com.cyber.Services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private imageRepo imageRepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private BlogRepo blogrepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ImageDto createImageByuserId(String filename, long userId) {
		User user = this.userrepo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
		Image image = new Image();
		image.setImageName(filename); //fileservcename
		image.setUser(user);
		Image savedimage = this.imageRepo.save(image);
		return this.modelMapper.map(savedimage, ImageDto.class);
	}

	@Override
	public ImageDto createImageByblogId(String filename, long blogId) {
	   Blog blog = this.blogrepo.findById(blogId).orElseThrow(()-> new RuntimeException("Blog Not Found"));
	   Image image = new Image();
	   image.setImageName(filename);
	   image.setBlog(blog);
	   Image savedImage = this.imageRepo.save(image);
		return this.modelMapper.map(savedImage,ImageDto.class);
	}

	@Override
	public ImageDto updateImage(String filename, long imageId) {
		  Image image = this.imageRepo.findById(imageId).orElseThrow(()-> new RuntimeException("Image Not Found"));
		  image.setImageName(filename);
		  Image updatedImage = this.imageRepo.save(image);
		return this.modelMapper.map(updatedImage, ImageDto.class);
	}

	@Override
	public ImageDto getByuserId(long userId) {
		User user = this.userrepo.findById(userId).orElseThrow(()->new RuntimeException("User Not found"));
		 Image image  = this.imageRepo.findByUser(user);
		 
		return this.modelMapper.map(image,ImageDto.class);
	}

	@Override
	public ImageDto getImageByblogId(long blogId) {
		  Blog blog = this.blogrepo.findById(blogId).orElseThrow(()-> new RuntimeException("Blog Not Found"));
		  Image image = this.imageRepo.findByBlog(blog);
		return this.modelMapper.map(image,ImageDto.class);
	}

	@Override
	public void deleteImage(long imageId) {
		
		  Image image = this.imageRepo.findById(imageId).orElseThrow(()-> new RuntimeException("Image Not Found"));
			 this.imageRepo.delete(image);
	}
	
	

	
}






