package com.writeup;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WriteUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(WriteUpApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
		
//		@Bean
//		public CommonsMultipartResolver commonsMultipartResolver() {
//			return new CommonsMultipartResolver();
//			
//		}

}
