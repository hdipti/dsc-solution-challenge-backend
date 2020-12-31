package com.au.sc2021.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.au.sc2021.model.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

	List<Candidate> findByFirstName(String firstName);
	
	@Query(value = "select candi from Candidate candi where candi.username = :username and candi.password = :password", nativeQuery = true)
	Candidate getCandidateByLoginDetails(String username, String password);
}
