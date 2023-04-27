package com.writeup.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostResponse {
	private int pageNumber;
	private int pageSize;
	private Long totalRecords;
	private int totalPages;
	private boolean lastPage;
	private List<PostDto> Content;

	

}
