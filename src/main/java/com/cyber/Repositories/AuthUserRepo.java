package com.cyber.Repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyber.Entities.AuthUser;
import com.cyber.Entities.User;

public interface AuthUserRepo extends JpaRepository<AuthUser,Long> {
	// custom methods to find the details from database //
	
	List<AuthUser>findByUser(User user);

}
