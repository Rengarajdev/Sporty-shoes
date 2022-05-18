package com.ss.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//The @Entity annotation specifies that the class is an entity and is mapped to a database table.
//The @Table annotation specifies the name of the database table to be used for mapping.
public class Admin {
	@Id
	//The @Id annotation specifies the primary key of an entity and the @GeneratedValue provides for the specification of generation strategies for the values of primary keys.
	private String username;
	private String password;
//default constructor
	public Admin() {
		super();
	}
	//parameterized constructor or constructor using fields
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	//getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
