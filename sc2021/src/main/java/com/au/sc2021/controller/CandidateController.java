package com.au.sc2021.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.au.sc2021.model.Candidate;
import com.au.sc2021.repo.CandidateRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CandidateController {

	@Autowired
	CandidateRepository repository;

	@GetMapping("/candidates")
	public ResponseEntity<List<Candidate>> getAllCandidates() {
		List<Candidate> candidates = new ArrayList<>();
		try {
			repository.findAll().forEach(candidates::add);

			if (candidates.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(candidates, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/candidates/{candidateId}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable("candidateId") long candidateId) {
		Optional<Candidate> CandidateData = repository.findById(candidateId);

		if (CandidateData.isPresent()) {
			return new ResponseEntity<>(CandidateData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/candidates")
	public ResponseEntity<Candidate> postCandidate(@RequestBody Candidate Candidate) {
		try {
			Candidate _Candidate = repository.save(new Candidate(Candidate.getFirstName(), 
					Candidate.getLastName(), Candidate.getEmail(), Candidate.getDescription(), Candidate.getUsername(), Candidate.getPassword()));
			return new ResponseEntity<>(_Candidate, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	

//	@GetMapping(value = "/login/username/{username}/password/{password}") 
//	@RequestMapping(
//			  value = "/login", 
//			  params = { "username", "password" }, 
//			  method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200/api/")
	@RequestMapping(path = "/login")
	public ResponseEntity<Candidate> getCandidateByLoginDetails(@RequestParam(required = true) String username, 
			@RequestParam(required = true) String password) {
		// http://localhost:8080/api/login?username=myname&password=123dws
		try {
			Candidate candidate = repository.getCandidateByLoginDetails(username, password);

			if (candidate == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(candidate, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/candidates/{candidateId}")
	public ResponseEntity<HttpStatus> deleteCandidate(@PathVariable("candidateId") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/candidates")
	public ResponseEntity<HttpStatus> deleteAllCandidates() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping(value = "candidates/firstName/{firstName}")
	public ResponseEntity<List<Candidate>> findByFirstName(@PathVariable String firstName) {
		try {
			List<Candidate> candidates = repository.findByFirstName(firstName);

			if (candidates.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(candidates, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/candidates/{candidateId}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable("candidateId") long id, @RequestBody Candidate candidate) {
		Optional<Candidate> candidateData = repository.findById(id);

		if (candidateData.isPresent()) {
			Candidate _Candidate = candidateData.get();
			_Candidate.setFirstName(candidate.getFirstName());
			_Candidate.setLastName(candidate.getLastName());
			_Candidate.setEmail(candidate.getEmail());
			_Candidate.setDescription(candidate.getDescription());
			_Candidate.setUserName(candidate.getUsername());
			_Candidate.setPassword(candidate.getPassword());
			return new ResponseEntity<>(repository.save(_Candidate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
