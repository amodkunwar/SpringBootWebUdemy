package com.example.demo.entity;

import java.time.Instant;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class User {

	private Integer id;

	@NotNull
	@Size(min = 2, message = "Name should have atleast two character")
	private String name;

	@NotNull
	private Instant birthDate;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String name, Instant birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

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

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
