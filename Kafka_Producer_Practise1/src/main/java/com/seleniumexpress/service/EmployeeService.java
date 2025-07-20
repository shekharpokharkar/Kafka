package com.seleniumexpress.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.seleniumexpress.model.Employee;

@Service
public class EmployeeService {

	@Value("${topic-Name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, Employee> kafkaTemplate;

	public String sendEmployeeToServer(Employee employee) {
		CompletableFuture<SendResult<String, Employee>> send = kafkaTemplate.send(topicName, "Spring", employee);

		send.whenCompleteAsync((result, ex) -> {
			if (ex == null) {
				System.out.println("Message sent successfully. Offset: " + result.getRecordMetadata().offset());
			} else {
				System.err.println("Message sending failed: " + ex.getMessage());
				ex.printStackTrace();
			}
		});

		return "Message sent request issued";
	}

}
