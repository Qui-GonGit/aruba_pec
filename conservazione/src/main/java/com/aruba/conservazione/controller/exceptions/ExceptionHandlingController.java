package com.aruba.conservazione.controller.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aruba.conservazione.services.exception.FileServiceException;



@RestControllerAdvice
public class ExceptionHandlingController {

	private static final String CODE_ERROR_USER_NOT_FOUND = "300";

	@ExceptionHandler(FileServiceException.class)
	public ResponseEntity<ResponseError> handleUserNotFoundExceptions(FileServiceException ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseError error = new ResponseError();
		error.setErrorCode(CODE_ERROR_USER_NOT_FOUND);
		error.setErrorDescription(ex.getMessage());
		return new ResponseEntity<>(error, headers, HttpStatus.OK);
	}
}
