package com.writeup.service;

import java.util.List;

import com.writeup.model.CategoryDto;


public interface CategoryService {
	CategoryDto createcategory(CategoryDto categoryDto);
	CategoryDto updatecategory(CategoryDto categoryDto,Integer categoryId);
	CategoryDto getcategoryById(Integer categoryId );
	List<CategoryDto> getAllcategories();
	void deletecategory(Integer categoryId);
	

}
