package com.selfLearnBitan.rest.webservices.restfulwebservices.entity;

import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


public class User {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public User(Integer id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	public User() {
		super();
	}
	public User(String name, Date dob) {
		super();
		this.name = name;
		this.dob = dob;
	}
	private Integer id;
	@Size(min = 2, max = 10)
	private String name;
	@Past
	private Date dob;
	
}
