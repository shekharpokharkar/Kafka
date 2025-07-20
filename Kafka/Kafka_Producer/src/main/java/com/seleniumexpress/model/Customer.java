package com.seleniumexpress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
	
	private Integer customerId;
	private String customerName;
	private String customerEmail;

}
