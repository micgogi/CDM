package com.example.NewsService.exception;

import java.lang.RuntimeException;

public class NewsNotFoundException extends RuntimeException {
	private String message;
	public NewsNotFoundException(String message) {
		super(message);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
