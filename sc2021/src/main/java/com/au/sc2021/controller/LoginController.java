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

import com.au.sc2021.model.Login;
import com.au.sc2021.repo.LoginRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	LoginRepository repository;

	@GetMapping("/logins")
	public ResponseEntity<List<Login>> getAllLogins() {
		List<Login> logins = new ArrayList<>();
		try {
			repository.findAll().forEach(logins::add);

			if (logins.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(logins, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/logins/{loginId}")
	public ResponseEntity<Login> getLoginById(@PathVariable("loginId") long loginId) {
		Optional<Login> loginData = repository.findById(loginId);

		if (loginData.isPresent()) {
			return new ResponseEntity<>(loginData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/logins")
	public ResponseEntity<Login> postLogin(@RequestBody Login login) {
		try {
			Login _Login = repository.save(new Login(login.getCandidateId(), login.getUserName(), login.getPassword()));
			return new ResponseEntity<>(_Login, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/logins/{loginId}")
	public ResponseEntity<HttpStatus> deleteLogin(@PathVariable("loginId") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/logins")
	public ResponseEntity<HttpStatus> deleteAllLogins() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping(value = "logins/firstName/{firstName}")
	public ResponseEntity<List<Login>> findByFirstName(@PathVariable long candidateId) {
		try {
			List<Login> Logins = repository.findByCandidateId(candidateId);

			if (Logins.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Logins, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/logins/{loginId}")
	public ResponseEntity<Login> updateLogin(@PathVariable("loginId") long id, @RequestBody Login login) {
		Optional<Login> loginData = repository.findById(id);

		if (loginData.isPresent()) {
			Login _Login = loginData.get();
			_Login.setCandidateId(login.getCandidateId());
			_Login.setPassword(login.getPassword());
			_Login.setUserName(login.getUserName());
			return new ResponseEntity<>(repository.save(_Login), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}




}
