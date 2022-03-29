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

@RestController
@RequestMapping("/")
public class KafkaContr {

	@Autowired
	KafkaTestProducer producer;
	@Autowired
	KafkaTestConsumer consumer;

	@GetMapping()
	public String test() {
		return "OK";
	}

	@GetMapping("/prod")
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
	public List<RezEntity> startConsumer() {
		return	consumer.startConsumer(false); 
	}

	@GetMapping("/messages")
	public List<RezEntity> getMessages(){
		List<RezEntity> rez =  consumer.rezentityList;
		return rez;
	}
	
	@GetMapping("/clear")
	public List<RezEntity> clearRezList(){
		consumer.clearMessagesList();
		return consumer.rezentityList;
	}
	
	@GetMapping("/stop")
	public String stopConsumer() {
		consumer.changeStopFlag();
		return "OK";
	}
	
	@GetMapping("/offset/{newoffset}")
	public String changeOffset(@PathVariable("newoffset") long offset) {
		return consumer.changeOffset(offset);
		
	}
	

}
