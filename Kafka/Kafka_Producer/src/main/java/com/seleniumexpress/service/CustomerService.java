package com.seleniumexpress.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.seleniumexpress.model.Customer;

@Service
public class CustomerService {

	@Value("${Topic-Name}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate<String, Customer> template;

	public String sendMessageToTopic(Customer customer) throws InterruptedException, ExecutionException {

		template.send(topicName, "Spring-Kafka", customer);

		return "Message send successfully";

	}

}
