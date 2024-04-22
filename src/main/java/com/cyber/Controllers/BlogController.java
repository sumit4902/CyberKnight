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
import com.cyber.Payload.BlogDto;
import com.cyber.Payload.BlogResponse;
import com.cyber.Services.BlogService;

@RestController()
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@PostMapping("/user/{userId}/blog/create")
	ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto,@PathVariable long userId)
	{
		BlogDto savedblog = this.blogService.createBlog(blogDto, userId);
		return new ResponseEntity<BlogDto>(savedblog,HttpStatus.CREATED);
	}
	@PutMapping("/blog/update/{blogId}")
	ResponseEntity<BlogDto> updateBlog(@RequestBody BlogDto blogDto ,@PathVariable long blogId)
	{
		BlogDto updatedBlog = this.blogService.updateBlog(blogDto, blogId);
		return new ResponseEntity<BlogDto>(updatedBlog,HttpStatus.OK);
	}
	
	@GetMapping("/blog/all")
	ResponseEntity<BlogResponse> getAllBlogs(@RequestBody 
			@RequestParam(name = "crimeType",defaultValue ="",required = false) String crimeType,
			@RequestParam(name = "title",defaultValue ="",required = false) String title,
			@RequestParam(name = "commitPlace",defaultValue ="",required = false) String commitPlace,
			@RequestParam(name = "pageNo",defaultValue ="0",required = false) int PageNo,
			@RequestParam(name = "pageSize",defaultValue ="20",required = false) int pageSize,
			@RequestParam(name = "sortBy",defaultValue ="blogId",required = false) String sortBy,
			@RequestParam(name = "direc",defaultValue ="Desc",required = false) String direc
			)
	{
	  BlogResponse blogresponse = this.blogService.getAllBlogs(crimeType, title, commitPlace, PageNo, pageSize, sortBy, direc);
		return new ResponseEntity<BlogResponse>(blogresponse,HttpStatus.OK);
	}
	@GetMapping("/blog/{blogId}")
	ResponseEntity<BlogDto> getBlogById(@RequestBody @PathVariable long blogId)
	{
		BlogDto blog = this.blogService.getBlogById(blogId);
		return new ResponseEntity<BlogDto>(blog,HttpStatus.OK);
	}
	
	@DeleteMapping("/blog/delete/{blogId}")
	ResponseEntity<ApiResponse> deleteBlog(@RequestBody @PathVariable long blogId)
	{
		this.blogService.deleteBlog(blogId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Blog Deleted Successfully..",true),HttpStatus.OK);
	}
	
	// get blog by userId //
	
	@GetMapping("/user/{userId}/blog/all")
	ResponseEntity<List<BlogDto>> getBlogsByUserId( @RequestBody @PathVariable long userId)
	{
		List<BlogDto> blogs = this.blogService.getlAllBlogsByUserId(userId);
		return new ResponseEntity<List<BlogDto>>(blogs,HttpStatus.OK);
	}
	
	
	
}
