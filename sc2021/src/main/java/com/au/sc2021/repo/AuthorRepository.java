package com.au.sc2021.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.au.sc2021.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	
	List<Author> findByAuthorName(String authorName);
	
}
