package com.bnhp.example.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnhp.example.entity.Producer1;
import com.bnhp.example.entity.Producer2;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.confluent.kafka.schemaregistry.json.JsonSchema;
import io.confluent.kafka.schemaregistry.json.JsonSchemaUtils;

@RestController
@RequestMapping("/schema")
public class SchemaController {

	@PostMapping("true/{version}")
	public String getOpenSchemaByVersion(@PathVariable String version) {
		JsonSchema sc = null;
		if(version.equals("1")) {
			try {
				sc = JsonSchemaUtils.getSchema(new Producer1(1), null, true,false, new ObjectMapper(), null);
				System.out.println("OPEN schema PRODUCER 1--> " + sc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(version.equals("2")) {
			try {
				sc = JsonSchemaUtils.getSchema(new Producer2(2, "test"), null, true,false, new ObjectMapper(), null);
				System.out.println("OPEN schema PRODUCER2 --> " + sc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "You should put version 1 or 2";
		}
		return "OK";
	}
	
	@PostMapping("false/{version}")
	public String getCloseSchemaByVersion(@PathVariable String version) {
		JsonSchema sc = null;
		if(version.equals("1")) {
			try {
				sc = JsonSchemaUtils.getSchema(new Producer1(1), null, true,true, new ObjectMapper(), null);
				System.out.println("CLOSE schema PRODUCER 1--> " + sc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(version.equals("2")) {
			try {
				sc = JsonSchemaUtils.getSchema(new Producer2(2,"test"), null, true,true, new ObjectMapper(), null);
				System.out.println("CLOSE schema PRODUCER 2--> " + sc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "You should put version 1 or 2";
		}
		return "OK";
	}
	
}
