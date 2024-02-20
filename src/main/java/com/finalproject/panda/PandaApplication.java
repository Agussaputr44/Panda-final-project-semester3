	package com.finalproject.panda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class PandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandaApplication.class, args);
	}

	@RequestMapping("/")
	public String home(){
		return "Index";
	}

	
}
