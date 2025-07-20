package com.seleniumexpress;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.seleniumexpress.model.Customer;

@Service
public class CustomerService {

	private String message;

	@KafkaListener(topics = "${Topic-Name}", groupId ="my-group")
	public void listner(@Payload Customer customer) {
		message = customer.toString();
	}

	public String getMessage() {
		return message;
	}

}
