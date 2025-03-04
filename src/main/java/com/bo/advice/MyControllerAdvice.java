package com.bo.advice;

import java.lang.module.FindException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bo.exception.AddException;
import com.bo.exception.ModifyException;
import com.bo.exception.RemoveException;

@RestControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(value= {FindException.class, AddException.class, ModifyException.class, RemoveException.class})
	public ResponseEntity<?> exceptionHandler(Exception e) {		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
