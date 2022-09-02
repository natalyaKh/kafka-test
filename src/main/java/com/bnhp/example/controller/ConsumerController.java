package com.bnhp.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnhp.example.service.ConsumeService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	
	@Autowired
	ConsumeService consumeService;
	
	@PostMapping("/close")
	public String getMessage() 
	{
		System.out.println("Hi, I am CLOSE CONSUMER #1 'id' ");
		consumeService.getMessage("1");
		return "OK";
	}
	
	@PostMapping("/open")
	public String getMessageOpenConsumer() {
		System.out.println("Hi, I am OPEN CONSUMER #2 'id' ");
		consumeService.getMessage("2");
		return "OK";
	}
	
	@PostMapping("/new")
	public String getMessageNewConsumer() {
		System.out.println("Hi, I am CLOSE NEW CONSUMER  'id, name' ");
		consumeService.getMessage("3");
		return "OK";
	}
}
