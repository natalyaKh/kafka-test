package com.example.demo.contr;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cons.KafkaTestConsumer;
import com.example.demo.entity.RezEntity;
import com.example.demo.producer.KafkaTestProducer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/")
public class KafkaContr {

	@Autowired
	KafkaTestProducer producer;
	@Autowired
	KafkaTestConsumer consumer;

	@GetMapping()
	@Operation(summary = "health check")
	public String healthcheck() {
		return "OK";
	}

	@GetMapping("/producer")
	@Operation(summary = " start producer for topic --> 'my-first' ")
	public List<RezEntity> startProducer() {
		try {
			List<RezEntity> rez = producer.startProducer();
			System.out.println("producer - done");
			return rez;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	
	@GetMapping("/consumer")
	@Operation(summary = " start consumer for topic --> 'my-first' and grp_id=-->'third_app' ")
	public List<RezEntity> startConsumer() {
		return	consumer.startConsumer(false); 
	}

	@GetMapping("/messages")
	@Operation(summary = " get list of all messages from kafka")
	public List<RezEntity> getMessages(){
		List<RezEntity> rez =  consumer.rezentityList;
		return rez;
	}
	
	@GetMapping("/clear")
	@Operation(summary = " clean a list of messages")
	public List<RezEntity> clearRezList(){
		consumer.clearMessagesList();
		return consumer.rezentityList;
	}
	
	@GetMapping("/stop")
	@Operation(summary = " stop consumer for topic --> 'my-first' and grp_id=-->'third_app' ")
	public String stopConsumer() {
		consumer.changeStopFlag();
		return "OK";
	}
	
	@GetMapping("/offset/{newoffset}")
	@Operation(summary = " change offset for topic --> 'my-first' and grp_id=-->'third_app'  and partition '0'")
	public String changeOffset(@Parameter(description = "number of new offset") 
	@PathVariable("newoffset") long offset) {
		return consumer.changeOffset(offset);
		
	}
	

}
