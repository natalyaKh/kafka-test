package com.example.demo.entity;

import java.time.LocalDate;


public class RezEntity {
	
	Integer partition; 
	Long offset;
	Long timestamp;
	Integer id;
String value;	
	public RezEntity() {
		super();
	}
	public RezEntity(Integer partition, Long offset, Long timestamp, Integer id, String value) {
		super();
		this.partition = partition;
		this.offset = offset;
		this.timestamp = timestamp;
		this.id = id;
		this.value = value;
	}

	public Integer getPartition() {
		return partition;
	}
	public void setPartition(Integer partition) {
		this.partition = partition;
	}
	public Long getOffset() {
		return offset;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setOffset(Long offset) {
		this.offset = offset;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
