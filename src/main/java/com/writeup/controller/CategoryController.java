package com.writeup.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.writeup.model.ApiResponse;
import com.writeup.model.CategoryDto;
import com.writeup.service.CategoryService;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	//create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createcategoryDto=categoryService.createcategory(categoryDto);
		return new ResponseEntity<>(createcategoryDto, HttpStatus.CREATED);
	}
	
	//Update category
		@PutMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
			CategoryDto updatedcategory=this.categoryService.updatecategory(categoryDto,categoryId);
			return  ResponseEntity.ok(updatedcategory);
		}
		
		//delete category
		@DeleteMapping("/{categoryId}")
		public ResponseEntity<?> deletecategory(@PathVariable Integer categoryId){
			this.categoryService.deletecategory(categoryId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully", true),HttpStatus.OK);
		}
		
		//get all category
		@GetMapping
		public ResponseEntity<List<CategoryDto>> getAllcategory(){
			return ResponseEntity.ok(this.categoryService.getAllcategories());
		}
		
		//get category by id
		@GetMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> getSinglecategory(@PathVariable Integer categoryId){
			return ResponseEntity.ok(this.categoryService.getcategoryById(categoryId));
		}
		
		
}
