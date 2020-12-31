package com.au.sc2021;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.au.sc2021.controller.CandidateController;
import com.au.sc2021.model.Candidate;

@SpringBootApplication
@RestController
@Configuration
@ComponentScan(basePackages = { "com.au.sc2021" })
@EnableAutoConfiguration
public class Sc2021Application {

	public static void main(String[] args) {
		SpringApplication.run(Sc2021Application.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	@GetMapping("/candidateTest")
	public ResponseEntity<List<Candidate>> candidate(@RequestParam(value = "firstName", defaultValue = "Dummy") String firstName) {
		CandidateController candControl = new CandidateController();
		ResponseEntity<List<Candidate>> candidate = candControl.findByFirstName(firstName);
		Candidate[] c = (Candidate[])candidate.getBody().toArray();
		return candidate;//String.format("Hello %s!", c[0].getFirstName());
	}
}