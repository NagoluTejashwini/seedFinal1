package com.ec.onlineplantnursery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyGlobalExceptionHandler {


	
	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidSeedIdByUser(SeedIdNotFoundException ex)
	{
		
		int seedId = ex.getSeedId();
		// design custom exception response 
		
		MyExceptionResponse excResponse = new MyExceptionResponse();
		excResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		excResponse.setExceptionMsg("Invalid Seed Id "+seedId+ " pls try again");
		
		
		return new ResponseEntity<MyExceptionResponse>(excResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	// java.util.NoSuchElementException

}
