package com.bnhp.example.service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.stereotype.Service;

import com.bnhp.example.confir.KafkaConfiguration;
import com.bnhp.example.entity.Producer1;
import com.bnhp.example.entity.Producer2;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializerConfig;


@Service
public class ProduceServ {



	public void sendMessageFromProducer1(String jsonType, Producer1 messsage) {
		switch (jsonType) {
		case "open":
			sendOpenMessageProducer1(messsage);
			break;
		case "close" :
			sendCloseMessageProducer1(messsage);
			break;
		}
	}

	private void sendCloseMessageProducer1(Producer1 messsage) {
		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.BOOTSTRAP_SERVICE);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
		properties.setProperty(KafkaJsonSchemaSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaConfiguration.SCHEMA_REGISTRY_URL);
		properties.setProperty(KafkaJsonSchemaSerializerConfig.AUTO_REGISTER_SCHEMAS, "false");
		
		KafkaProducer<Integer, Producer1> producer = new KafkaProducer<Integer, Producer1>(properties);
		ProducerRecord<Integer, Producer1> record = new ProducerRecord<Integer, Producer1>(
				KafkaConfiguration.TOPIC_NAME, 1, messsage);
		try {
			producer.send(record).get();
			System.out.println("message :" + messsage.toString() + " was send successfully");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

	private void sendOpenMessageProducer1(Producer1 messsage) {
		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.BOOTSTRAP_SERVICE);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
		properties.setProperty(KafkaJsonSchemaSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaConfiguration.SCHEMA_REGISTRY_URL);
		properties.setProperty(KafkaJsonSchemaSerializerConfig.AUTO_REGISTER_SCHEMAS, "false");
		//open schema
		properties.setProperty(KafkaJsonSchemaSerializerConfig.FAIL_UNKNOWN_PROPERTIES, "false");

		KafkaProducer<Integer, Producer1> producer = new KafkaProducer<Integer, Producer1>(properties);
		ProducerRecord<Integer, Producer1> record = new ProducerRecord<Integer, Producer1>(
				KafkaConfiguration.TOPIC_NAME, 1, messsage);
		try {
			producer.send(record).get();
			System.out.println("message :" + messsage.toString() + " was send successfully");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		producer.close();

	}

	public void sendMessageFromProducer2(String jsonType, Producer2 messsage) {
		switch (jsonType) {
		case "open":
			sendOpenMessageProducer2(messsage);
			break;
		case "close" :
			sendCloseMessageProducer2(messsage);
			break;
		}
	}

	private void sendCloseMessageProducer2(Producer2 messsage) {
		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.BOOTSTRAP_SERVICE);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
		properties.setProperty(KafkaJsonSchemaSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaConfiguration.SCHEMA_REGISTRY_URL);
		properties.setProperty(KafkaJsonSchemaSerializerConfig.AUTO_REGISTER_SCHEMAS, "false");
		

		KafkaProducer<Integer, Producer2> producer = new KafkaProducer<Integer, Producer2>(properties);
		ProducerRecord<Integer, Producer2> record = new ProducerRecord<Integer, Producer2>(
				KafkaConfiguration.TOPIC_NAME, 1, messsage);
		try {
			producer.send(record).get();
			System.out.println("message :" + messsage.toString() + " was send successfully");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

	private void sendOpenMessageProducer2(Producer2 messsage) {
		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.BOOTSTRAP_SERVICE);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
		properties.setProperty(KafkaJsonSchemaSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaConfiguration.SCHEMA_REGISTRY_URL);
		properties.setProperty(KafkaJsonSchemaSerializerConfig.AUTO_REGISTER_SCHEMAS, "false");
		//open schema
		properties.setProperty(KafkaJsonSchemaSerializerConfig.FAIL_UNKNOWN_PROPERTIES, "false");

		KafkaProducer<Integer, Producer2> producer = new KafkaProducer<Integer, Producer2>(properties);
		ProducerRecord<Integer, Producer2> record = new ProducerRecord<Integer, Producer2>(
				KafkaConfiguration.TOPIC_NAME, 1, messsage);
		try {
			producer.send(record).get();
			System.out.println("message :" + messsage.toString() + " was send successfully");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}



}
