package com.cyber.Entities;





import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Image {
 
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long imageId;
	private String imageName;
	
	//@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	//@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	private Blog blog;
}
