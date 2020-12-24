package com.au.sc2021.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.au.sc2021.model.Post;
import com.au.sc2021.repo.PostRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	PostRepository repository;

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> posts = new ArrayList<>();
		try {
			repository.findAll().forEach(posts::add);

			if (posts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(posts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable("postId") long postId) {
		Optional<Post> PostData = repository.findById(postId);

		if (PostData.isPresent()) {
			return new ResponseEntity<>(PostData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/posts")
	public ResponseEntity<Post> postPost(@RequestBody Post post) {
		try {
			Post _Post = repository.save(new Post(post.getAuthorId(), post.getPostDate(), post.getContent(), post.getStatus(), post.getPostType(), post.getLikeCount()));
			return new ResponseEntity<>(_Post, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("postId") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/posts")
	public ResponseEntity<HttpStatus> deleteAllPosts() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping(value = "posts/age/{age}")
	public ResponseEntity<List<Post>> findByName(@PathVariable String authorName) {
		try {
			List<Post> posts = repository.findByAuthorId(authorName);

			if (posts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(posts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable("postId") long id, @RequestBody Post post) {
		Optional<Post> PostData = repository.findById(id);

		if (PostData.isPresent()) {
			Post _Post = PostData.get();
			_Post.setContent(post.getContent());
			_Post.setLikeCount(post.getLikeCount());
			_Post.setPostDate(post.getPostDate());
			_Post.setPostType(post.getPostType());
			_Post.setPostType(post.getPostType());
			_Post.setStatus(post.getStatus());
			return new ResponseEntity<>(repository.save(_Post), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
