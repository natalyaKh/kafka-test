package com.bnhp.example.service;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.stereotype.Service;

import com.bnhp.example.confir.KafkaConfiguration;
import com.bnhp.example.entity.Consumer1;
import com.bnhp.example.entity.Consumer2;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializerConfig;

@Service
public class ConsumeService {

	 public void getMessage(String version) {
		Properties properties = createConsumer();
		switch(version) {
		case "1":
			getMessageFromProducerOne(properties);
			break;
		case "2":
			getMessageFromProducerOneOpenConsumer(properties);
			break;
		case "3" :
			getMessageFromProducerSecondCloseConsumer(properties);
		}


	}

	private void getMessageFromProducerSecondCloseConsumer(Properties properties) {
		properties.setProperty(KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE, Consumer2.class.getName());
		KafkaConsumer<Integer, Consumer2> consumer = new KafkaConsumer<>(properties);	
		String topic = KafkaConfiguration.TOPIC_NAME;
		consumer.subscribe(Collections.singletonList(topic));
		while(true) {
			ConsumerRecords<Integer, Consumer2> records = consumer.poll(Duration.ofMillis(100));
			records.forEach(record -> {
				Consumer2 userREcord = record.value();
				System.out.println("Close consumer for message with one field " + userREcord.toString());
			});
		}
	}

	private void getMessageFromProducerOneOpenConsumer(Properties properties) {
		properties.setProperty(KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE, Consumer1.class.getName());
		//Open consumer
		properties.setProperty(KafkaJsonSchemaDeserializerConfig.FAIL_INVALID_SCHEMA, "false");
		KafkaConsumer<Integer, Consumer1> consumer = new KafkaConsumer<>(properties);	
		String topic = KafkaConfiguration.TOPIC_NAME;
		consumer.subscribe(Collections.singletonList(topic));
		while(true) {
			ConsumerRecords<Integer, Consumer1> records = consumer.poll(Duration.ofMillis(100));
			records.forEach(record -> {
				Consumer1 userREcord = record.value();
				System.out.println("Close consumer for message with one field " + userREcord.toString());
			});
		}

	}

	private Properties createConsumer() {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.BOOTSTRAP_SERVICE);
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaJsonSchemaDeserializer.class.getName());
		properties.setProperty(KafkaJsonSchemaDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaConfiguration.SCHEMA_REGISTRY_URL);
		return properties;
	}

	private void getMessageFromProducerOne(Properties properties) {
		properties.setProperty(KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE, Consumer1.class.getName());
		KafkaConsumer<Integer, Consumer1> consumer = new KafkaConsumer<Integer, Consumer1>(properties);	
		String topic = KafkaConfiguration.TOPIC_NAME;
		consumer.subscribe(Collections.singletonList(topic));
		while(true) {
			ConsumerRecords<Integer, Consumer1> records = consumer.poll(Duration.ofMillis(100));
			records.forEach(record -> {
				Consumer1 userREcord = record.value();
				System.out.println("Close consumer for message with one field " + userREcord.toString());
			});
		}
	}



}
