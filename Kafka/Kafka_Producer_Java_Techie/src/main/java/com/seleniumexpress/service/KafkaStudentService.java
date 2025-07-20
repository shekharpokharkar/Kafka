package com.seleniumexpress.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.seleniumexpress.model.Student;

@Service
public class KafkaStudentService {

	@Value("${Topic-Name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, Student> template;

	public String sendStudentToTopic(Student student) {
		CompletableFuture<SendResult<String, Student>> future = template.send(topicName, "Spring", student);

		String ret = null;
		future.whenComplete((result, exp) -> {

			System.out.println("Message Sent successfully" + result.getRecordMetadata().offset());

		});
		
		return "successfully";
	}

}
