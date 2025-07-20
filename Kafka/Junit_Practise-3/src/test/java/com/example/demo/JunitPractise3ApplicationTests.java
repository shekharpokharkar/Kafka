package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JunitPractise3ApplicationTests {

	private static Calculator cal = null;

	@BeforeAll
	public static void createCalculatorObject() {
		cal = new Calculator();
	}

	@BeforeEach
	public void beforeEachMethod()
	{
		assertNotNull(cal, "calculator object is not null");
		//assertNull(cal, "Sorry calculator object is null");
	}
	
	
	@Test
	@DisplayName("check Addition")
	public void test_Addition1() {
		assertNotEquals(30, cal.addition(10, 200), "Something Wrong in your Code");
		assertEquals(30,cal.addition(10, 20), "Something Wrong in your Code");
		assertEquals(-10,cal.addition(10, -20), "Something Wrong in your Code");
		assertEquals(10,cal.addition(-10, 20), "Something Wrong in your Code");
		assertEquals(-30,cal.addition(-10, -20), "Something Wrong in your Code");
		assertEquals(10,cal.addition(10,0), "Something Wrong in your Code");
		assertEquals(10,cal.addition(0,10), "Something Wrong in your Code");
		assertThrows(RuntimeException.class, ()->{cal.addition(0, 0);});
		
	}
	
	@Test
	@DisplayName("check Substrction")
	public void test_Substraction() {
		assertNotEquals(1000, cal.substraction(200, 100), "Something Wrong in your Code");
		assertEquals(-10,cal.substraction(10, 20), "Something Wrong in your Code");
		assertEquals(30,cal.substraction(10, -20), "Something Wrong in your Code");
		assertEquals(-30,cal.substraction(-10, 20), "Something Wrong in your Code");
		assertEquals(10,cal.substraction(-10, -20), "Something Wrong in your Code");
		assertEquals(10,cal.substraction(10, 0), "Something Wrong in your Code");
		assertEquals(20,cal.substraction(0, -20), "Something Wrong in your Code");
		
		assertThrows(RuntimeException.class, ()->{cal.substraction(0, 0);});
		
	}
	
	
	@Test
	@DisplayName("check multiplication")
	public void test_Multiplication() {
		assertNotEquals(1000, cal.multiplication(20, 10), "Something Wrong in your Code");
		assertEquals(200,cal.multiplication(10, 20), "Something Wrong in your Code");
		assertEquals(-200,cal.multiplication(10, -20), "Something Wrong in your Code");
		assertEquals(-200,cal.multiplication(-10, 20), "Something Wrong in your Code");
		assertEquals(200,cal.multiplication(-10, -20), "Something Wrong in your Code");
		assertEquals(0,cal.multiplication(0, 20), "Something Wrong in your Code");
		assertEquals(0,cal.multiplication(10, 0), "Something Wrong in your Code");
		
		assertThrows(RuntimeException.class, ()->{cal.multiplication(0, 0);});
		
	}
	
	
	@Test
	@DisplayName("check Division")
	public void test_Division() {
		assertNotEquals(1000, cal.division(20, 10), "Something Wrong in your Code");
		assertEquals(5,cal.division(100, 20), "Something Wrong in your Code");
		assertEquals(-5,cal.division(100, -20), "Something Wrong in your Code");
		assertEquals(-5,cal.division(-100, 20), "Something Wrong in your Code");
		assertEquals(5,cal.division(-100, -20), "Something Wrong in your Code");
		assertThrows(ArithmeticException.class, ()->{cal.division(10, 0);});
		
		assertThrows(RuntimeException.class, ()->{cal.division(0, 0);});
		
	}
	
	
	
	

	@AfterAll
	public static void destroyCalculatorObject() {
		cal = null;
	}

}
