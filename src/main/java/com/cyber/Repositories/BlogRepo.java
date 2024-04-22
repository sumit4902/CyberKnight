package com.cyber.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cyber.Entities.Blog;
import com.cyber.Entities.User;

public interface BlogRepo  extends JpaRepository<Blog, Long>{

	//cutom methods to find the blogs //
	
	List<Blog> findByUser(User user);
	Page<Blog>findByCrimeTypeContainingIgnoreCaseAndTitleContainingIgnoreCaseAndCommitPlaceContainingIgnoreCase(String crimeType,String title,String commitPlace,Pageable p  );
	
}
