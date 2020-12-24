package com.au.sc2021.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.au.sc2021.model.Login;

public interface LoginRepository extends CrudRepository<Login, Long> {
	
	List<Login> findByCandidateId(Long candidateId);

}
