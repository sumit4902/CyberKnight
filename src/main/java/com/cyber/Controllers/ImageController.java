package com.cyber.Controllers;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.cyber.Payload.ApiResponse;
import com.cyber.Payload.ImageDto;
import com.cyber.Services.FileService;
import com.cyber.Services.ImageService;
import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
		
		
	    
	    @PostMapping("/image/upload/user/{userId}")
	    ResponseEntity<ImageDto> createImageByuserId(@RequestBody @RequestParam("image") MultipartFile image,@PathVariable long userId) throws IOException
	    {
	    	String fileName = this.fileService.uploadImage(path, image);
	    	ImageDto imagesaved  = this.imageService.createImageByuserId(fileName, userId);
	    	return new ResponseEntity<ImageDto>(imagesaved,HttpStatus.CREATED);
	    }
	    
	    @PostMapping("/image/upload/blog/{blogId}")
	    ResponseEntity<ImageDto> createImageByblogId(@RequestParam("image") MultipartFile image,@PathVariable long blogId) throws IOException
	    {
	    	String fileName = this.fileService.uploadImage(path, image);
	    	ImageDto imagesaved  = this.imageService.createImageByblogId(fileName, blogId);
	    	return new ResponseEntity<ImageDto>(imagesaved,HttpStatus.CREATED);
	    }
	    
	    @PutMapping("/image/update/{imageId}")
	    ResponseEntity<ImageDto> updateImage(@RequestParam("image") MultipartFile image, @PathVariable long imageId) throws IOException
	    {
	    	String fileName = this.fileService.uploadImage(path, image);
	    	ImageDto imagesaved  = this.imageService.updateImage(fileName, imageId);
	    	return new ResponseEntity<ImageDto>(imagesaved,HttpStatus.OK);
	    }
	    
	    
	    

		// Download image By UserId //
		
	    @GetMapping(value="/image/user/{userId}",produces = MediaType.IMAGE_JPEG_VALUE)
	    void downloadByUserId(@RequestBody @PathVariable long userId, HttpServletResponse response) throws IOException
	    {
	    	ImageDto image = this.imageService.getByuserId(userId);
	    	
	    	
	    	 InputStream resource = this.fileService.getResource(path, image.getImageName());
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(resource,response.getOutputStream())   ;
	    }
	    
	    // Download image By BlogId //
	    
	    @GetMapping(value="/image/blog/{blogId}",produces = MediaType.IMAGE_JPEG_VALUE)
	    void downloadByblogId(@RequestBody @PathVariable long blogId, HttpServletResponse response) throws IOException
	    {
	    	ImageDto image = this.imageService.getImageByblogId(blogId);
	    	
	    	
	    	 InputStream resource = this.fileService.getResource(path, image.getImageName());
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(resource,response.getOutputStream())   ;
	    }
	    
	    
	    
	    //delete Image 
	    @DeleteMapping("/image/delete/{imageId}")
	    ResponseEntity<ApiResponse> deleteImage(@RequestBody  @PathVariable long  imageId)
	    {
	    	this.imageService.deleteImage(imageId);
	    	return new ResponseEntity<ApiResponse>(new ApiResponse("image successfully deleted",true),HttpStatus.OK);
	    }
	    
       
}
