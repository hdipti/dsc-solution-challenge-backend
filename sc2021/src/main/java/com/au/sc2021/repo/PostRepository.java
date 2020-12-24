package com.au.sc2021.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.au.sc2021.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	
	List<Post> findByAuthorId(String authorName);

}
