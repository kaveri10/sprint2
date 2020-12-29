package com.cg.spring.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {UserNotFoundException.class})
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(
			UserNotFoundException ex) {
		String error = "User is not found";

		ErrorMessage errorMessage = 
	      new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<ErrorMessage>(
	    		errorMessage, new HttpHeaders(), errorMessage.getStatus());
		
	}

	@ExceptionHandler(value = {InvalidDataException.class})
	public ResponseEntity<ErrorMessage> handleInvalidDataException(
	InvalidDataException ex) {
		String error = "Data is Inavalid";
		
		ErrorMessage errorMessage =
		     new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		   return new ResponseEntity<ErrorMessage>(
		    errorMessage, new HttpHeaders(), errorMessage.getStatus());
	
	}
	
	@ExceptionHandler(value = {InvalidUserDetails.class})
	public ResponseEntity<ErrorMessage> handleInvalidUserDetails(
			InvalidUserDetails ex) {
		String error = ex.getLocalizedMessage();
		ErrorMessage errorMessage =
		     new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		logger.info(errorMessage);

		   return new ResponseEntity<ErrorMessage>(
		    errorMessage, new HttpHeaders(), errorMessage.getStatus());
	
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<ErrorMessage> handleInvalidUserDetails(
			Exception ex) {
		String error = ex.getLocalizedMessage();
		ErrorMessage errorMessage =
		     new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		logger.info(errorMessage);

		   return new ResponseEntity<ErrorMessage>(
		    errorMessage, new HttpHeaders(), errorMessage.getStatus());
	
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for(FieldError fieldError: ex.getBindingResult().getFieldErrors())
			errors.add(fieldError.getField() + " - " + fieldError.getDefaultMessage());
		for(ObjectError objectError: ex.getBindingResult().getGlobalErrors())
			errors.add(objectError.getObjectName() + " - " + objectError.getDefaultMessage());
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, 
				ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, errorMessage,headers, HttpStatus.BAD_REQUEST, request);
	}
}
