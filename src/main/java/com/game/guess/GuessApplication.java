package com.game.guess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuessApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuessApplication.class, args);
		System.out.println("Run App Successfully");
	}

}
