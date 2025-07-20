package com.seleniumexpress.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.Service.KafkaEmployeeService;
import com.seleniumexpress.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private KafkaEmployeeService service;

	@PostMapping("/sendEmployee")
	public ResponseEntity<String> sendEmployeeToController(@RequestBody List<Employee> employee) {

		// List<String>msgRetin=service.sendMessageToTopic(employee);

		service.sendMessageToTopic(employee);
		return new ResponseEntity<String>("Send Successfully", HttpStatus.OK);
	}

}
