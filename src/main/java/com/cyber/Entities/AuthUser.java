package com.cyber.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  long    authUserId;
	@Column(nullable = true ,unique = true)
	private  String  userName;
	@Column(nullable = true ,unique = true)
	private String email;
	@Column(nullable = true ,unique = true)
	private String contact;
	@Column(nullable = true)
	private  String  password;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
}
