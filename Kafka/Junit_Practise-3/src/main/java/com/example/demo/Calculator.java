package com.example.demo;

public class Calculator {

	public int addition(int iNo1, int iNo2) {
		if (iNo1 == 0 && iNo2 == 0) {
			throw new RuntimeException("Number not in proper format check your Number");
		}

		if (iNo1 < 0 && iNo2 > 0) {
			return iNo2 + iNo1;
		} else if (iNo1 > 0 && iNo2 < 0) {
			return iNo1 + iNo2;
		} else {
			return iNo1 + iNo2;
		}

	}

	public int substraction(int iNo1, int iNo2) {
		if (iNo1 == 0 && iNo2 == 0) {
			throw new RuntimeException("Number not in proper format check your Number");

		}

		if (iNo1 < 0 && iNo2 > 0) {
			return iNo1 - iNo2;
		} else if (iNo1 > 0 && iNo2 < 0) {
			return iNo1 - iNo2;
		} else {
			return iNo1 - iNo2;
		}

	}

	public int multiplication(int iNo1, int iNo2) {
		if (iNo1 == 0 && iNo2 == 0) {
			throw new RuntimeException("Number not in proper format check your Number");

		}

		if (iNo1 < 0 && iNo2 > 0) {
			return iNo1 * iNo2;
		} else if (iNo1 > 0 && iNo2 < 0) {
			return iNo1 * iNo2;
		}else {
			return iNo1 * iNo2;
		}

	}

	public int division(int iNo1, int iNo2) {
		if (iNo1 == 0 && iNo2 == 0) {
			throw new RuntimeException("Number not in proper format check your Number");
		}
		if (iNo2 == 0) {
			throw new ArithmeticException("Enter Valid number");
		}

		if (iNo1 < 0 && iNo2 > 0 ) {
			return iNo1 / iNo2;
		} else if (iNo1 > 0 && iNo2 < 0 ) {
			return iNo1 / iNo2;
		}  else {
			return iNo1 / iNo2;
		}

	}

}
