package com.seleniumexpress.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.seleniumexpress.model.Employee;

@Service
public class KafkaEmployeeService {

	@Value("${kafka-topic-name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, Employee> template;

	public void sendMessageToTopic(List<Employee> employees) {

		
		
		for(Employee emp:employees)
		{
			CompletableFuture<SendResult<String, Employee>> send = template.send(topicName, 1,"spring-kafka", emp);
		}
		

		/*
		 * for (int i = 0; i < 1000; i++) { CompletableFuture<SendResult<String,
		 * Employee>> future = null;
		 * 
		 * // Partitioning logic if (i % 5 == 0 && i % 4 != 0) { future =
		 * template.send(topicName, 2, "spring-kafka", employee); } else if
		 * (checkDivisibleByFour(i)) { future = template.send(topicName, 1,
		 * "spring-kafka", employee); } else if (i % 2 == 0 && i % 3 == 0) { future =
		 * template.send(topicName, 0, "spring-kafka", employee); }
		 * 
		 * if (future != null) { int finalI = i; // Needed because 'i' must be
		 * effectively final in lambda future.whenCompleteAsync((result, exp) -> { if
		 * (exp == null) { String info = "Message sent for i=" + finalI +
		 * " to partition " + result.getRecordMetadata().partition() + " with offset " +
		 * result.getRecordMetadata().offset(); System.out.println(info); synchronized
		 * (resultList) { resultList.add(info); } } else { String error =
		 * "Failed to send message for i=" + finalI; System.err.println(error);
		 * synchronized (resultList) { resultList.add(error); } } }); }
		 */
		

		// WARNING: Sending messages is async — if you want to wait for completion, you
		// must block or use CountDownLatch
		// For now, return empty or partial results list
		//return "send Successfully";
	}

	private boolean checkDivisibleByFour(int iNo) {
		int idigit = 0;
		/*
		 * int ireverse = 0; boolean flag = false; while (iNo != 0 && idigit < 2) {
		 * 
		 * ireverse = ireverse * 10 + (iNo % 10); iNo = iNo / 10; idigit++; if (idigit
		 * == 2) { break; }
		 * 
		 * }
		 * 
		 * Integer valueOf = Integer.valueOf(new
		 * StringBuilder(String.valueOf(ireverse)).reverse().toString());
		 * 
		 * if (valueOf % 4 == 0) { flag = true; } else { flag = false; }
		 */

		idigit = idigit % 100;

		return idigit % 4 == 0;
	}

}
