package com.cyber.Services;

import java.util.List;

import com.cyber.Payload.BlogDto;
import com.cyber.Payload.BlogResponse;

public interface BlogService {

	BlogDto createBlog(BlogDto blogDto,long userId);
	BlogDto updateBlog(BlogDto blogDto,long blogId);
	BlogResponse getAllBlogs(String crimeType,String title ,String commitPlace,int PageNo ,int pageSize,String sortBy,String direc);
	BlogDto getBlogById(long blogId);
	void deleteBlog(long blogId);
	// it's for one to many relationship
	List<BlogDto> getlAllBlogsByUserId( long userId);
	
}
