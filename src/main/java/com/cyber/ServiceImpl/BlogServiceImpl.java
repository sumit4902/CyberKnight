package com.cyber.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cyber.Entities.Blog;
import com.cyber.Entities.User;
import com.cyber.Payload.BlogDto;
import com.cyber.Repositories.BlogRepo;
import com.cyber.Repositories.UserRepo;
import com.cyber.Services.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public BlogDto createBlog(BlogDto blogDto, long userId) {
		User user =   this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
		Blog blog = this.modelmapper.map(blogDto,Blog.class);
		blog.setUser(user);
		Blog savedblog = this.blogRepo.save(blog);
		return this.modelmapper.map(savedblog, BlogDto.class);
	}

	@Override
	public BlogDto updateBlog(BlogDto blogDto, long blogId) {
		Blog blog = this.blogRepo.findById(blogId).orElseThrow(()-> new RuntimeException("Blog Not Found"));
		blog.setCrimeType(blogDto.getCrimeType());
		blog.setTitle(blogDto.getTitle());
		blog.setCommitPlace(blogDto.getCommitPlace());
		blog.setDescription(blogDto.getDescription());
		Blog updatedBlog = this.blogRepo.save(blog);
		return this.modelmapper.map(updatedBlog, BlogDto.class);
	}

	@Override
	public List<BlogDto> getAllBlogs() {
		List<Blog> allblogs = this.blogRepo.findAll();
		
		List<BlogDto> convertedBlog = allblogs.stream().map((blog)->this.modelmapper.map(blog,BlogDto.class)).collect(Collectors.toList());
		return convertedBlog;
	}

	@Override
	public BlogDto getBlogById(long blogId) {
		Blog blog = this.blogRepo.findById(blogId).orElseThrow(()-> new RuntimeException("Blog Not Found "));
		
		return this.modelmapper.map(blog, BlogDto.class);
	}

	@Override
	public void deleteBlog(long blogId) {
		Blog blog = this.blogRepo.findById(blogId).orElseThrow(()-> new RuntimeException("Blog Not Found"));
		this.blogRepo.delete(blog);
	}

	@Override
	public List<BlogDto> getlAllBlogsByUserId(long userId) {
		   User user = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found "));
		   List<Blog> allblogs = this.blogRepo.findByUser(user);
		   List<BlogDto> convertblog = allblogs.stream().map((blog)-> this.modelmapper.map(blog,BlogDto.class)).collect(Collectors.toList());
		return convertblog;
	}
	
	
	
}
