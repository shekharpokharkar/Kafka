package com.seleniumexpress.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

	@Bean
	public NewTopic topic() {
		return new NewTopic("SeleniumExpress", 5, (short) 1);
	}
}
