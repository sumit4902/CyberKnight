package com.cyber.Entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long blogId;
	@Column(nullable = true)
	private String  crimeType;
	@Column(nullable = true)
	private String title;
	@Column(nullable = true)
	private String commitPlace;
	@Column(length = 10000000)
	private String description;
	  
	//@JsonBackReference
	@ManyToOne()
	private User user;
	
	//@JsonManagedReference
	@OneToOne(mappedBy = "blog",cascade=CascadeType.ALL ,fetch = FetchType.EAGER)
	private Image image ;
	
}
