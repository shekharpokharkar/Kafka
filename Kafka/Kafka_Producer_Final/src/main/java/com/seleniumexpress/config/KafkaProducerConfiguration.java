package com.seleniumexpress.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.seleniumexpress.model.Employee;

@Configuration
public class KafkaProducerConfiguration {

    /**
     * (Optional) Create a Kafka topic at startup if it doesn't exist.
     * This is useful during development or auto-setup environments.
     */
    @Bean
    public NewTopic createTopic() {
        // Topic name: "SeleniumExpress-Kafka", with 5 partitions, and replication factor 1
        return new NewTopic("SeleniumExpress-Abhilsh", 3, (short) 1);
    }

    /**
     * Configure and return a ProducerFactory bean.
     * This tells Spring Kafka how to produce messages using the correct serializers.
     */
    @Bean
    public ProducerFactory<String, Employee> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        // Kafka broker address
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // Key will be serialized as String
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Value will be serialized as JSON (Employee object)
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Optional: Avoid sending nulls as type headers
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * KafkaTemplate is the high-level API used to send messages.
     * You can autowire this wherever you need to produce messages.
     */
    @Bean
    public KafkaTemplate<String, Employee> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
