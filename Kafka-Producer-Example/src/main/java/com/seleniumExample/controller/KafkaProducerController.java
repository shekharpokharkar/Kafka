package com.seleniumExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumExample.kafka.KafkaMeassagePublisher;

@RestController
@RequestMapping("/producer-app")
public class KafkaProducerController {

	@Autowired
	private KafkaMeassagePublisher publisher;

	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publicMessage(@PathVariable() String message) {
		try {
			publisher.sendMessageToTopic(message);
			return ResponseEntity.ok("message send to publisher successfully");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
