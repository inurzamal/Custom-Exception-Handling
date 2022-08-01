package com.nur.contact.globalExceptionHandler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.nur.contact.custom.exception.EmptyInputException;
import com.nur.contact.custom.exception.NotFoundException;

@ControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
		
		return new ResponseEntity<>("Your Input is Empty", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleFindBy(NoSuchElementException noSuchElementException){
		
		return new ResponseEntity<>("Id not present", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotFoundException.class)
	ResponseEntity<String> handleNotFound(NotFoundException notFoundException){
		return new ResponseEntity<>("Not Found, Please provide valid input", HttpStatus.NOT_FOUND);	
	}

}
