package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class EvenNumberTest {

	CheckEven even = null;

	@BeforeEach
	public void init() {
		even = new CheckEven();
	}

	@ParameterizedTest(name = "CheckEven({0}) should return {1}")
	@CsvSource({ "12, even", "13, odd", "0, even", "-2, even", "-5, odd" })
	void testEven(int input, String expected) {
		String result = even.checkEven(input);
		assertEquals(expected, result);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/odd.csv", numLinesToSkip = 1)
	void testEven(String iNo, String result) {

		String checkEven = even.checkEven(Integer.parseInt(iNo));
		assertEquals(result, checkEven);

	}

	@ParameterizedTest
	@ValueSource(strings = { "12", "15", "16", "18", "21" })
	void testEven1(String iNo) {

		String checkEven = even.checkEven(Integer.parseInt(iNo));
		assertEquals("even", checkEven);

	}

	@ParameterizedTest
	@MethodSource("evenNumberProvider")
	void testEven2(String iNo, String result) {

		String checkEven = even.checkEven(Integer.parseInt(iNo));
		assertEquals(result, checkEven);

	}

	static Stream<Arguments> evenNumberProvider() {
		return Stream.of(Arguments.of("12", "even"), Arguments.of("13", "odd"), Arguments.of("28", "even"),
				Arguments.of("31", "odd"));
	}
}
