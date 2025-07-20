package com.seleniumexpress.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.seleniumexpress.model.Employee;

@Service
public class KafkaConsumerService {

	private String message;



@KafkaListener(
	    groupId = "my-group",
	    topicPartitions = {
	        @TopicPartition(
	            topic = "${kafka.topic.name}",
	            partitions = {"2"}
	        )
	    }
	)
public void consumeMessage(@Payload Employee employee) {

		message = employee.toString();

	}

	public String getMessage() {
		return message;
	}

}
