package com.writeup.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String uploadImage(String path,MultipartFile image)throws IOException;
	InputStream getResorce(String path,String imageName)throws FileNotFoundException;

}
