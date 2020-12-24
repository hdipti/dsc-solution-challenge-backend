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

import com.au.sc2021.model.Comment;
import com.au.sc2021.repo.CommentRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentRepository repository;

	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> comments = new ArrayList<>();
		try {
			repository.findAll().forEach(comments::add);

			if (comments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(comments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/comments/{commentId}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") long CommentId) {
		Optional<Comment> commentData = repository.findById(CommentId);

		if (commentData.isPresent()) {
			return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/comments")
	public ResponseEntity<Comment> postComment(@RequestBody Comment comment) {
		try {
			Comment _Comment = repository.save(new Comment(comment.getPostId(), comment.getCommentCount(), 
					comment.getCommentAuthor(), comment.getCommentDate(), comment.getContent(), 
					comment.getApproved(), comment.getLikeCount()));
			return new ResponseEntity<>(_Comment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<HttpStatus> deleteComment(@PathVariable("commentId") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/comments")
	public ResponseEntity<HttpStatus> deleteAllComments() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping(value = "comments/postId/{postId}")
	public ResponseEntity<List<Comment>> findByFirstName(@PathVariable long postId) {
		try {
			List<Comment> comments = repository.findByPostId(postId);

			if (comments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(comments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/comments/{commentId}")
	public ResponseEntity<Comment> updateComment(@PathVariable("CommentId") long id, @RequestBody Comment comment) {
		Optional<Comment> commentData = repository.findById(id);

		if (commentData.isPresent()) {
			Comment _Comment = commentData.get();
			_Comment.setCommentAuthor(comment.getCommentAuthor());
			_Comment.setCommentCount(comment.getCommentCount());
			_Comment.setCommentDate(comment.getCommentDate());
			_Comment.setContent(comment.getContent());
			_Comment.setPostId(comment.getPostId());
			_Comment.setLikeCount(comment.getLikeCount());
			_Comment.setApproved(comment.getApproved());
			return new ResponseEntity<>(repository.save(_Comment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
