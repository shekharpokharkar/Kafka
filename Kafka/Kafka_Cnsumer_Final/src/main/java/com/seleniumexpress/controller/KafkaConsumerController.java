package com.seleniumexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.service.KafkaConsumerService;

@RestController
public class KafkaConsumerController {

	@Autowired
	private KafkaConsumerService service;

	@GetMapping("/consume")
	public ResponseEntity<List<String>> getMesageFromTopic() {
		 List<String> message = service.getMessage();
		System.out.println("Message:"+message);
		return new ResponseEntity<List<String>>(message, HttpStatus.OK);
	}
}
