package com.bo.exception;

public class AddException extends Exception {
	public AddException() {
		super();
	}
	public AddException(String message) {
		super(message); //예외의 상세메시지
	}
}
