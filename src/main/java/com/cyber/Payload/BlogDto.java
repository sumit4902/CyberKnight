package com.cyber.Payload;




import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
	
	private long blogId;
	
	private String  crimeType;

	private String title;
	private String commitPlace;
	
	private String description;
    
	@OneToOne
	private ImageDto image ;
}
