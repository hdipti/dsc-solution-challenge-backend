package com.au.sc2021.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.sc2021.model.Candidate;
import com.au.sc2021.repo.CandidateRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CandidateController {
	
	@Autowired
	  CandidateRepository repository;
	 
	  @GetMapping("/Candidates")
	  public ResponseEntity<List<Candidate>> getAllCandidates() {
	    List<Candidate> Candidates = new ArrayList<>();
	    try {
	      repository.findAll().forEach(Candidates::add);
	      
	      if (Candidates.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(Candidates, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/Candidates/{candidateId}")
	  public ResponseEntity<Candidate> getCandidateById(@PathVariable("candidateId") long candidateId) {
	    Optional<Candidate> CandidateData = repository.findById(candidateId);
	 
	    if (CandidateData.isPresent()) {
	      return new ResponseEntity<>(CandidateData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	  @PostMapping(value = "/Candidates")
	  public ResponseEntity<Candidate> postCandidate(@RequestBody Candidate Candidate) {
	    try {
	      Candidate _Candidate = repository.save(new Candidate(Candidate.getFirstName(), 
	    		  Candidate.getLastName(), Candidate.getEmail(), Candidate.getDescription()));
	      return new ResponseEntity<>(_Candidate, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	  @DeleteMapping("/Candidates/{candidateId}")
	  public ResponseEntity<HttpStatus> deleteCandidate(@PathVariable("candidateId") long id) {
	    try {
	      repository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	  @DeleteMapping("/Candidates")
	  public ResponseEntity<HttpStatus> deleteAllCandidates() {
	    try {
	      repository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	 
	  }
	 
	  @GetMapping(value = "Candidates/age/{age}")
	  public ResponseEntity<List<Candidate>> findByFirstName(@PathVariable String candidateName) {
	    try {
	      List<Candidate> Candidates = repository.findByFirstName(candidateName);
	 
	      if (Candidates.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(Candidates, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	  @PutMapping("/Candidates/{candidateId}")
	  public ResponseEntity<Candidate> updateCandidate(@PathVariable("candidateId") long id, @RequestBody Candidate Candidate) {
	    Optional<Candidate> CandidateData = repository.findById(id);
	 
	    if (CandidateData.isPresent()) {
	      Candidate _Candidate = CandidateData.get();
	      _Candidate.setFirstName(Candidate.getFirstName());
	      _Candidate.setLastName(Candidate.getLastName());
	      _Candidate.setEmail(Candidate.getEmail());
	      _Candidate.setDescription(Candidate.getDescription());
	      return new ResponseEntity<>(repository.save(_Candidate), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }


}
