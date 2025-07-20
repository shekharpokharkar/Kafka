package com.seleniumexpress.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.seleniumexpress.model.Employee;

@Service
public class KafkaConsumerService {

	private List<String> message = new ArrayList<>();

	@KafkaListener(groupId = "shekhar-group-java", topicPartitions = {
			@TopicPartition(topic = "${kafka-topic-name}", partitions = { "1" }) })

	//@RetryableTopic(exclude = {NullPointerException.class,OutOfMemoryError.class},attempts = "4", backoff = @Backoff(delay = 3000, maxDelay = 15000, multiplier = 1.5))
	public void consumeMessageFromBroker(@Payload Employee employee) {

		System.out.println(employee);
		
		if(employee.getEmployeeSalary() > 50000)
		{
			throw new RuntimeException("Salary limit Exceed");
		}

		message.add(employee.toString());
	}

	public List<String> getMessage() {
		return message;
	}
	
	/*
	 * @DltHandler public void handleDLT(@Payload Employee employee) {
	 * System.out.println("UnExceptes Salary:"+employee); }
	 */
}
