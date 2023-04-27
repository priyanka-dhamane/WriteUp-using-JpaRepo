package com.writeup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.writeup.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
