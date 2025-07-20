package com.seleniumexpress.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.model.Customer;
import com.seleniumexpress.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/send")
	public ResponseEntity<String> sendMessgeToTopic(@RequestBody Customer customer)
			throws InterruptedException, ExecutionException {
		String sendMessageToTopic = service.sendMessageToTopic(customer);
		return new ResponseEntity<String>(sendMessageToTopic, HttpStatus.OK);
	}

}
