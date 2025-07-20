package com.seleniumexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.model.Employee;
import com.seleniumexpress.service.EmployeeService;

@RestController
public class KafkaController {

	@Autowired
	private EmployeeService service;

	@PostMapping("/send")
	public ResponseEntity<String> sendMessageTopic(@RequestBody Employee employee) {
		String sendEmployeeToServer = service.sendEmployeeToServer(employee);
		return new ResponseEntity<String>(sendEmployeeToServer, HttpStatus.OK);
	}

}
