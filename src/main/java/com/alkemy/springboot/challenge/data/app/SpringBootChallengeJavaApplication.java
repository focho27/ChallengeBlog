package com.alkemy.springboot.challenge.data.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alkemy.springboot.challenge.data.app.models.service.IUploadImageService;

@SpringBootApplication
public class SpringBootChallengeJavaApplication implements CommandLineRunner{
	@Autowired
	private IUploadImageService ImageService;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootChallengeJavaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ImageService.deleteAll();
		ImageService.init();
	}
}
