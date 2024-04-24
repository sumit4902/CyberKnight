package com.cyber.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyber.Entities.Blog;
import com.cyber.Entities.Image;
import com.cyber.Entities.User;

public interface imageRepo extends JpaRepository<Image,Long> {
	
  // we can create custom to find the  data from databases //
	
	Image findByUser(User user);
	
	Image findByBlog(Blog blog);
	
}
