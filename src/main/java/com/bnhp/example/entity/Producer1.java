package com.bnhp.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Producer1 {
	@JsonProperty(required = true)
	Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Producer1(Integer id) {
		super();
		this.id = id;
	}

	public Producer1() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Producer1 [id=" + id + "]";
	}
	
	
}
