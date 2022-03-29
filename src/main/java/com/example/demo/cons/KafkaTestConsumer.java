package com.example.demo.cons;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RezEntity;

@Service
public class KafkaTestConsumer {

	public List<RezEntity> rezentityList = new ArrayList();
	public boolean stop;

	public void clearMessagesList() {
		rezentityList.clear();

	}

	public  List<RezEntity> startConsumer(boolean stop){

		List<RezEntity>  rezList= new ArrayList();
		String bootstrapServers="localhost:9092";  
		String grp_id="third_app";  
		String topic="my-first";  
		//Creating consumer properties  
		Properties properties=new Properties();  
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);  
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   StringDeserializer.class.getName());  
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());  
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,grp_id);  
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
		properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.setProperty(ConsumerConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, "30000");

		//        TopicPartition part = new TopicPartition(topic, 2) ;

		
//			//creating consumer  
			KafkaConsumer<String,String> consumer= new KafkaConsumer<String,String>(properties);  
			//Subscribing 
			
			consumer.subscribe(Arrays.asList(topic));  
			//polling  
			while(true){  
				if(checkStopFlag()) break;
				ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(1));  
				for(ConsumerRecord<String,String> record: records){                      
					System.out.println("Key: "+ record.key() + ", Value:" +record.value());  
					System.out.println("Partition:" + record.partition()+",Offset:"+record.offset());  
					RezEntity entity = createEntityfromRecord(record);
					rezentityList.add(entity);
				}  
			}
			this.stop = false;
			System.out.println("stop --> " + this.stop);
			return rezList; 
	}

	

    public void changeStopFlag() {
    	stop = true;
    }
	private boolean checkStopFlag() {
		return stop;
	}

	private static RezEntity createEntityfromRecord(ConsumerRecord<String, String> record) {

		RezEntity  rez= new RezEntity();
		rez.setOffset(record.offset());
		rez.setPartition(record.partition());
		rez.setTimestamp(record.timestamp());
		rez.setValue(record.value());
		return rez;
	}

	public String changeOffset(long offset) {
		

		List<RezEntity>  rezList= new ArrayList();
		String bootstrapServers="localhost:9092";  
		String grp_id="third_app";  
		String topic="my-first";  
		//Creating consumer properties  
		Properties properties=new Properties();  
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);  
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   StringDeserializer.class.getName());  
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());  
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,grp_id);  
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
		properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.setProperty(ConsumerConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, "30000");

		        TopicPartition tp = new TopicPartition(topic, 0) ;

		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
		    consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
		        @Override
		        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {}

		        @Override
		        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		            // Move to the desired start offset 
		            consumer.seek(tp, offset);
		        }
		    });
//		    boolean run = true;
//		    long lastOffset = 96L;
		    while (true) {
		        ConsumerRecords<String, String> crs = consumer.poll(Duration.ofMillis(1L));
		        for (ConsumerRecord<String, String> record : crs) {
		            System.out.println(record);
		        }
		    }
		}

	}
	}





