package com.au.sc2021.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_Id")  
	private long commentId;
	 
	@Column(name = "post_Id") 
	private long postId;
	
	@Column(name = "comment_count")
	private int commentCount;
	
	@Column(name = "comment_author")
	private String commentAuthor;
	
	@Column(name = "comment_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @NotNull
	private Date commentDate;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "approved")
	private int approved;
	
	@Column(name = "like_count")
	private int likeCount;

	/**
	 * Constructor
	 */
	public Comment() {
		super();
	}

	/**
	 * @param postId
	 * @param commentCount
	 * @param commentAuthor
	 * @param commentDate
	 * @param content
	 * @param approved
	 * @param likeCount
	 */
	public Comment(long postId, int commentCount, String commentAuthor, Date commentDate, String content, int approved,
			int likeCount) {
		super();
		this.postId = postId;
		this.commentCount = commentCount;
		this.commentAuthor = commentAuthor;
		this.commentDate = commentDate;
		this.content = content;
		this.approved = approved;
		this.likeCount = likeCount;
	}

	/**
	 * @return the commentId
	 */
	public long getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the postId
	 */
	public long getPostId() {
		return postId;
	}

	/**
	 * @param postId the postId to set
	 */
	public void setPostId(long postId) {
		this.postId = postId;
	}

	/**
	 * @return the commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return the commentAuthor
	 */
	public String getCommentAuthor() {
		return commentAuthor;
	}

	/**
	 * @param commentAuthor the commentAuthor to set
	 */
	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	/**
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the approved
	 */
	public int getApproved() {
		return approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(int approved) {
		this.approved = approved;
	}

	/**
	 * @return the likeCount
	 */
	public int getLikeCount() {
		return likeCount;
	}

	/**
	 * @param likeCount the likeCount to set
	 */
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", postId=" + postId + ", commentCount=" + commentCount
				+ ", commentAuthor=" + commentAuthor + ", commentDate=" + commentDate + ", content=" + content
				+ ", approved=" + approved + ", likeCount=" + likeCount + "]";
	}
	
}
