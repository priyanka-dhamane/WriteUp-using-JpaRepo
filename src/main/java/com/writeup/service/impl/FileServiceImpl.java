package com.writeup.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.writeup.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	
	@Override
	public String uploadImage(String path, MultipartFile image) throws IOException {
		//file name
		String name=image.getOriginalFilename();
		
		//random name generate file
		String randomID=UUID.randomUUID().toString();
		String imageName= randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//fullpath
		String filePath=path + File.separator +imageName;
		
		//Create folder if not created
		File files= new File(path);
		if(!files.exists()) {
			files.mkdir();
		}
		
		
		//copy file
		Files.copy(image.getInputStream(), Paths.get(filePath));
		return imageName;
	}

	@Override
	public InputStream getResorce(String path, String imageName) throws FileNotFoundException {
		String fullPath=path + File.separator +imageName;
		InputStream is=new FileInputStream(fullPath);
		//when want from db database logic is here
		return is;
	}

}
