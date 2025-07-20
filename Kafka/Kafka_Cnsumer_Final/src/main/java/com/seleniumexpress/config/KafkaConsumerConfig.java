package com.seleniumexpress.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import org.springframework.kafka.retrytopic.RetryTopicConfiguration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;

import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.seleniumexpress.model.Employee;

@Configuration
public class KafkaConsumerConfig {

    // Consumer factory for String key and Employee JSON value
    @Bean
    public ConsumerFactory<String, Employee> consumerFactory() {
        JsonDeserializer<Employee> jsonDeserializer = new JsonDeserializer<>(Employee.class);
        jsonDeserializer.addTrustedPackages("com.seleniumexpress.model"); // 🔐 Be specific in production

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "shekhar-group-java");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    // Listener container factory for @KafkaListener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Employee> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Employee> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    // Producer factory to serialize Employee object to JSON (used for retry/DLT)
    @Bean
    public ProducerFactory<String, Employee> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(props);
    }

    // KafkaTemplate for sending Employee objects to Kafka (used by RetryTopic mechanism)
    @Bean
    public KafkaTemplate<String, Employee> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // Retry topic and DLT configuration
    @Bean
    public RetryTopicConfiguration retryTopicConfiguration(KafkaTemplate<String, Employee> kafkaTemplate) {
        return RetryTopicConfigurationBuilder
                .newInstance()
                .maxAttempts(3)
                .fixedBackOff(2000)  // 2 seconds delay between retries
                .retryTopicSuffix("-retry") // Retry topic name suffix
                .dltSuffix("-dlt")          // Dead-letter topic name suffix
                .notRetryOn(NullPointerException.class) // Skip retry for this exception
                .autoCreateTopics(true, 3, (short) 1)    // Auto-create topics if needed
                .create(kafkaTemplate);
    }
}
