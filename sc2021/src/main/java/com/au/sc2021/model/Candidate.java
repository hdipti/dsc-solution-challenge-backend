package com.au.sc2021.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_Id")
	private long candidateId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "description")
	private String description;
	
	@Column(name = "username")
	private String username;
	 
	@Column(name = "password")
	private String password;

	/**
	 * Constructor
	 */
	public Candidate() {
		super();
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param description
	 * @param userName
	 * @param password
	 */
	public Candidate(String firstName, String lastName, String email, String description, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.description = description;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the candidateId
	 */
	public long getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId the candidateId to set
	 */
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param userName the username to set
	 */
	public void setUserName(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Displays the string containing field and their values
	 */
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", description=" + description + ", username=" + username + ", password="
				+ password + "]";
	}

}
