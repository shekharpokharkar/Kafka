package com.seleniumexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.CustomerService;

@RestController
public class CustomerConsumer {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/consume")
	public ResponseEntity<String> consumeMessage() {
		String message = customerService.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
