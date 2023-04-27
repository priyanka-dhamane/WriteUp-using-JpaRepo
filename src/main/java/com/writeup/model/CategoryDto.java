package com.writeup.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
		
		private Integer categoryId;
		@NotBlank
		@Size(min=3, message="min size of title is 3")
		private String categoryTitle;
		@NotBlank
		@Size(min=10, message="min size of title is 10")
		private String categoryDiscription;
		

	


}
