package com.bnhp.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnhp.example.entity.Producer1;
import com.bnhp.example.entity.Producer2;
import com.bnhp.example.service.ProduceServ;

@RestController
@RequestMapping("/producer")
public class ProducerController {

	
	@Autowired
	ProduceServ produceService;
	
	
	@PostMapping("/{type}")
	public String senfMessageFromProducer(@PathVariable String type, 
			@RequestBody Producer1 messsage) {
		System.out.println("Hi, I am Producer with one field");
		produceService.sendMessageFromProducer1(type, messsage);
		System.out.println("Producer with one field --> finished");
		return "OK";
	}
	
	@PostMapping("/{type}/new")
	public String senfMessageFromNewProducer(@PathVariable String type, 
			@RequestBody Producer2 messsage) {
		System.out.println("Hi, I am Producer with two field");
		produceService.sendMessageFromProducer2(type, messsage);
		System.out.println("Producer with two field --> finished");
		return "OK";
	}
	
}
	
