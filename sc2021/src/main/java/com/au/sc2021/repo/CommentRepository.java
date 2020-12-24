package com.au.sc2021.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.au.sc2021.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	List<Comment> findByPostId(long postId);
	
}
