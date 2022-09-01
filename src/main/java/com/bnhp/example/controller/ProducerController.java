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
		
		produceService.sendMessageFromProducer1(type, messsage);
		return "OK";
	}
	
	@PostMapping("/{type}/new")
	public String senfMessageFromNewProducer(@PathVariable String type, 
			@RequestBody Producer2 messsage) {
		
		produceService.sendMessageFromProducer2(type, messsage);
		return "OK";
	}
	
}
	
