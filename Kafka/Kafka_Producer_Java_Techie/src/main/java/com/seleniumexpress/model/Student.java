package com.seleniumexpress.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentId;
	private String studentName;
	private String studentCity;
	private String studentCountry;
	

}
