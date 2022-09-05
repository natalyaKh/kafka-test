package com.bnhp.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Producer2 {

	@JsonProperty(required = true)
	Integer id;
	String name;
	
	public Producer2(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Producer2() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "Producer2 [id=" + id + ", name=" + name + "]";
	}
	
}
