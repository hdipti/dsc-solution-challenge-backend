package com.au.sc2021.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_Id")
	private long authorId;
	
    @Column(name = "author_name")
	private String authorName;
	
    @Column(name = "author_description")
	private String authorDescription;
    
	/**
	 * Constructor
	 */
	public Author() {
		super();
	}

	/**
	 * @param authorId
	 * @param authorName
	 * @param authorDescription
	 */
	public Author(String authorName, String authorDescription) {
		super();
		this.authorName = authorName;
		this.authorDescription = authorDescription;
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
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the authorDescription
	 */
	public String getAuthorDescription() {
		return authorDescription;
	}

	/**
	 * @param authorDescription the authorDescription to set
	 */
	public void setAuthorDescription(String authorDescription) {
		this.authorDescription = authorDescription;
	}
	
	/**
	 * Displays the string containing field and their values
	 */
	@Override
	  public String toString() {
	    return "Author [id = " + authorId + ", Author name = " + authorName + ", Author description = " + authorDescription + "]";
	  } 
	
}
