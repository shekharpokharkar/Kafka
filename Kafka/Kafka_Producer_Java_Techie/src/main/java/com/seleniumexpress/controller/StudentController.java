package com.seleniumexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.model.Student;
import com.seleniumexpress.service.KafkaStudentService;

@RestController
public class StudentController {

	@Autowired
	private KafkaStudentService studentService;

	@PostMapping("/send")
	public ResponseEntity<String> sendMessageToTopic(@RequestBody Student student) {

		String sendStudentToTopic = studentService.sendStudentToTopic(student);

		return new ResponseEntity<String>(sendStudentToTopic, HttpStatus.OK);

	}

}
