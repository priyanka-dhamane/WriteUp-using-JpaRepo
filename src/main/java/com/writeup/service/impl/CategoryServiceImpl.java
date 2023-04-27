package com.writeup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.writeup.dao.CategoryDao;
import com.writeup.entity.Category;
import com.writeup.exceptions.ResorceNotFoundException;
import com.writeup.model.CategoryDto;
import com.writeup.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createcategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryDao.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updatecategory(CategoryDto categoryDto, Integer categoryId) {
			Category cat = this.categoryDao.findById(categoryId)
			.orElseThrow(()-> new ResorceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDiscription(categoryDto.getCategoryDiscription());
		Category updatedCat = this.categoryDao.save(cat);
			return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto getcategoryById(Integer categoryId) {
		Category cat = this.categoryDao.findById(categoryId)
				.orElseThrow(()->new ResorceNotFoundException("category", "category id", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllcategories() {
		List<Category> categories = this.categoryDao.findAll();
		List<CategoryDto> catdtos = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catdtos;
	}

	@Override
	public void deletecategory(Integer categoryId) {
		Category cat = this.categoryDao.findById(categoryId).orElseThrow(()->new ResorceNotFoundException("Category", "Category Id", categoryId) );
		this.categoryDao.delete(cat);
	}

}
