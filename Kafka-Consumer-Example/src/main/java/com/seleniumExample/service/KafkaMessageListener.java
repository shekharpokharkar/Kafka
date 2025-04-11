package com.seleniumExample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

	Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

	
	@KafkaListener(topics = "shekhar Topic1",groupId = "KRIOSIS-GROUP -1")
	public void consume1(String message) {
		logger.info("Consumer1 consume the message {} ", message);
	}

	@KafkaListener(topics = "shekhar Topic1",groupId = "KRIOSIS-GROUP -1")
	public void consume2(String message) {
		logger.info("Consumer2 consume the message {} ", message);
	}
	
	@KafkaListener(topics = "shekhar Topic1",groupId = "KRIOSIS-GROUP -1")
	public void consume3(String message) {
		logger.info("Consumer3 consume the message {} ", message);
	}
	
	@KafkaListener(topics = "shekhar Topic1",groupId = "KRIOSIS-GROUP -1")
	public void consume4(String message) {
		logger.info("Consumer4 consume the message {} ", message);
	}
}
