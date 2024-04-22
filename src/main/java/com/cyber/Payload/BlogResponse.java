package com.cyber.Payload;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {
	
	private List<BlogDto> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
    private boolean  Lastpage;

}