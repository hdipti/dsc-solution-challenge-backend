package com.au.sc2021.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "login_Id")
	private long  loginId;
	 
	@Column(name = "candidate_Id")
	private long candidateId;
	 
	@Column(name = "username")
	private String userName;
	 
	@Column(name = "password")
	private String password;
	

	/**
	 * Constructor
	 */
	public Login() {
		super();
	}

	/**
	 * @param login_Id
	 * @param candidate_Id
	 * @param userName
	 * @param password
	 */
	public Login( long candidateId, String userName, String password) {
		super();
		this.candidateId = candidateId;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * @return the loginId
	 */
	public long getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	/**
	 * @param candidateId the candidateId to set
	 */
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * @return the candidateId
	 */
	public long getCandidateId() {
		return candidateId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * Displays the string containing field and their values
	 */
	@Override
	  public String toString() {
	    return "Candidate [id = " + loginId + ", Candidate_id = " + candidateId + ", User name = " + userName + 
	    	 ", password = " + password + "]";
	  } 
	 
}
