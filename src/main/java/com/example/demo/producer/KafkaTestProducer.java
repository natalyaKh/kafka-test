package com.example.demo.producer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RezEntity;

import org.apache.kafka.clients.producer.*;

@Service
public class KafkaTestProducer {
	
	
	
	public static List<RezEntity>  startProducer() throws InterruptedException, ExecutionException{

		final String BOOTSTRAP_SERVERS = "localhost:9092";
		//создание свойств продюссера
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		//		создание самого продюсера
		KafkaProducer<String, String> first_producer = new KafkaProducer<String, String>(properties);  

		for(int i = 0; i < 10; i++) {
			String topic = "my-first";
			String value = "OneTwo --- " + Integer.toString(i) + " data = " + LocalDateTime.now();
			String key = "id ==" + Integer.toString(i);
			ProducerRecord<String, String> record=new ProducerRecord<String, String>(
					topic, key, value);

	
			//создание записи (message)
			//		ProducerRecord<String, String> record=new ProducerRecord<String, String>("my-first", "Hye Kafka");  
			//отправляем запись

			
			 first_producer.send(record, (Callback) new ProdCallBackWithKey()).get() ;
				 
				
		
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		first_producer.flush();
		first_producer.close();
			return ProdCallBackWithKey.rezList;
	}

}
	class ProdCallBackWithKey implements Callback {

		public static List<RezEntity>  rezList= new ArrayList();
		
//		public List<RezEntity> returnRexultList(){
//			return rezList;
//		}
		/**
		 * onCompletion method will be called when the record sent to the Kafka Server has been acknowledged.
		 * 
		 * @param metadata  The metadata contains the partition and offset of the record. Null if an error occurred.
		 * @param exception The exception thrown during processing of this record. Null if no error occurred.
		 */
		public void onCompletion(RecordMetadata recordMetadata, Exception e) {  
			if (e== null) {  
				
				RezEntity rez = new RezEntity();
			
				rez.setOffset(recordMetadata.offset());
				rez.setPartition(recordMetadata.partition());
				rez.setTimestamp(recordMetadata.timestamp());
				rezList.add(rez);
						
				System.out.println("Successfully received the details as: \n" +  
						"Topic:" + recordMetadata.topic() + "\n" +  
						"Partition:" + recordMetadata.partition() + "\n" +  
						"Offset" + recordMetadata.offset() + "\n" +  
						"Timestamp" + recordMetadata.timestamp());  
			}  

			else {  
				System.out.println("Can't produce,getting error" + e);  

			}  
		}  
	}


