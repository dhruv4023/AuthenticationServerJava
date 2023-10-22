package com.sunbase.sserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SserverApplication {

	public static void main(String[] args) {
		System.out.println("+-----------------------------------------------------------------------------------+");
		System.out.println("|               ctrl click to open in browser:  http://localhost:8080/              |");
		System.out.println("+-----------------------------------------------------------------------------------+");
		SpringApplication.run(SserverApplication.class, args);
	}

}
