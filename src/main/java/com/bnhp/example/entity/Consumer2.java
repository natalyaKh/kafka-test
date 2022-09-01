package com.bnhp.example.entity;


public class Consumer2 {

	Integer id;
	String name;
	
	public Consumer2(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Consumer2() {
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
