package com.bnhp.example.entity;


public class Consumer1 {

	Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Consumer1(Integer id) {
		super();
		this.id = id;
	}

	public Consumer1() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Producer1 [id=" + id + "]";
	}
	
	
}
