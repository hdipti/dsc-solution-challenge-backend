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

import com.au.sc2021.model.Author;
import com.au.sc2021.repo.AuthorRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthorController {
	
	 @Autowired
	  AuthorRepository repository;
	 
	  @GetMapping("/Authors")
	  public ResponseEntity<List<Author>> getAllAuthors() {
	    List<Author> Authors = new ArrayList<>();
	    try {
	      repository.findAll().forEach(Authors::add);
	      
	      if (Authors.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(Authors, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/Authors/{authorId}")
	  public ResponseEntity<Author> getAuthorById(@PathVariable("authorId") long id) {
	    Optional<Author> AuthorData = repository.findById(id);
	 
	    if (AuthorData.isPresent()) {
	      return new ResponseEntity<>(AuthorData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	  @PostMapping(value = "/Authors")
	  public ResponseEntity<Author> postAuthor(@RequestBody Author Author) {
	    try {
	      Author _Author = repository.save(new Author(Author.getAuthorName(), Author.getAuthorDescription()));
	      return new ResponseEntity<>(_Author, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	  @DeleteMapping("/Authors/{authorId}")
	  public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("authorId") long id) {
	    try {
	      repository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	  @DeleteMapping("/Authors")
	  public ResponseEntity<HttpStatus> deleteAllAuthors() {
	    try {
	      repository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	 
	  }
	 
	  @GetMapping(value = "Authors/authorName/{authorName}")
	  public ResponseEntity<List<Author>> findByAuthorName(@PathVariable String authorName) {
	    try {
	      List<Author> Authors = repository.findByAuthorName(authorName);
	 
	      if (Authors.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(Authors, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	  @PutMapping("/Authors/{authorId}")
	  public ResponseEntity<Author> updateAuthor(@PathVariable("authorId") long id, @RequestBody Author Author) {
	    Optional<Author> AuthorData = repository.findById(id);
	 
	    if (AuthorData.isPresent()) {
	      Author _Author = AuthorData.get();
	      _Author.setAuthorName(Author.getAuthorName());
	      _Author.setAuthorDescription(Author.getAuthorDescription());
	      return new ResponseEntity<>(repository.save(_Author), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
