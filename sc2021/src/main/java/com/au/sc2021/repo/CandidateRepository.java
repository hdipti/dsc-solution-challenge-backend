package com.au.sc2021.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.au.sc2021.model.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

	List<Candidate> findByFirstName(String firstName);
	
}
