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
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_Id")
	private long postId;
	
	@Column(name = "author_Id")
	private long authorId;
	
	@Column(name = "post_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @NotNull
	private Date postDate;
	
	@Column(name = "content")
	private StringBuffer content;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "post_type")
	private String postType;
	
	@Column(name = "like_count")
	private String likeCount;

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
	 * @return the authorId
	 */
	public long getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the postDate
	 */
	public Date getPostDate() {
		return postDate;
	}

	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	/**
	 * @return the content
	 */
	public StringBuffer getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(StringBuffer content) {
		this.content = content;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the postType
	 */
	public String getPostType() {
		return postType;
	}

	/**
	 * @param postType the postType to set
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}

	/**
	 * @return the likeCount
	 */
	public String getLikeCount() {
		return likeCount;
	}

	/**
	 * @param likeCount the likeCount to set
	 */
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", authorId=" + authorId + ", postDate=" + postDate + ", content=" + content
				+ ", status=" + status + ", postType=" + postType + ", likeCount=" + likeCount + "]";
	}

}
