package com.gameapp.exception;


import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GamesExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {NullPointerException.class})
	protected ResponseEntity<Object> handleNullPointerException(RuntimeException ex, WebRequest request){
		Error error = new Error();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setDescription(ex.getMessage());
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	@ExceptionHandler(value = {NoDetailsFoundException.class})
	protected ResponseEntity<Object> handleNoDetailsFoundException(RuntimeException ex, WebRequest request){
		Error error = new Error();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setDescription(ex.getMessage());
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {DataAccessException.class})
	protected ResponseEntity<Object> handleDataAccessException(RuntimeException ex, WebRequest request){
		Error error = new Error();
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setDescription(ex.getMessage());
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	@ExceptionHandler(value = {Exception.class})
	protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request){
		Error error = new Error();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setDescription(ex.getMessage());
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
