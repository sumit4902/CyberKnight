package com.cyber.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyber.Entities.User;

public interface  UserRepo extends JpaRepository<User,Long> {
	
	// custom methods to perform database operation // 
    User findByApiKey(String apiKey);
    
    User findByEmail(String email);
    User findByContact(String contact);
}
