package com.seleniumExample.kafka;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaMeassagePublisher {

	@Autowired
	private KafkaTemplate<String, Object> template;

	public void sendMessageToTopic(String message) {
		CompletableFuture<SendResult<String, Object>> future = template.send("shekhar Topic1", message);

		future.whenComplete((result, ex) -> {

			if (ex == null) {
				System.out.println(
						"Sent Message=[" + message + " ] with offset = [" + result.getRecordMetadata().offset() + "]");
			} else {
				System.out.println("Unable to send Message = [" + message + " ] due to :" + ex.getMessage());
			}

		});

	}

}
