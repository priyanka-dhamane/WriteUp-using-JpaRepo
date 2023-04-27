package com.writeup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.writeup.dao.UserDao;


@SpringBootTest
class WriteUpApplicationTests {

	@Autowired
	private UserDao userDao;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest() {
		 String className = this.userDao.getClass().getName();
		
		 System.err.println(className );
		 ;
		
	}
}
