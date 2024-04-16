package com.cyber.Services;

import java.util.List;

import com.cyber.Payload.BlogDto;

public interface BlogService {

	BlogDto createBlog(BlogDto blogDto,long userId);
	BlogDto updateBlog(BlogDto blogDto,long blogId);
	List<BlogDto>getAllBlogs();
	BlogDto getBlogById(long blogId);
	void deleteBlog(long blogId);
	// it's for one to many relationship
	List<BlogDto> getlAllBlogsByUserId( long userId);
	
}
