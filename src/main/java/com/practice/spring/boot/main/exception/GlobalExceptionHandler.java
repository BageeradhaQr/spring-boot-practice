package com.practice.spring.boot.main.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*Specialization of @Component for classes that declare
 * @ExceptionHandler,
 * @InitBinder, or
 * @ModelAttribute
 * methods to be shared across multiple
 * @Controller classes.*/
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest request){
		ErrorDetails details = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				request.getDescription(false),
				"USER_NOT_FOUND");

		return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);

	}


	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleEmailAlreadyExistException(EmailAlreadyExistsException exception,WebRequest request){
		ErrorDetails details = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				request.getDescription(false),
				"USER_EMAIL_ALREADY_EXIST");

		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				exception.getMessage(),
				request.getDescription(false),
				"INTERNAL_SERVER");
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers,
																  HttpStatusCode status,
																  WebRequest request) {
		Map<String,String> errors = new HashMap<>();
		List<ObjectError> errorList =  ex.getBindingResult().getAllErrors();
		errorList.forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			//String message = fieldName+ " " +error.getDefaultMessage();
			String message = error.getDefaultMessage();
			errors.put(fieldName,message);
		});
	return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String parameterName = ex.getParameterName() + " " +"parameter is missing";
		return new ResponseEntity<>(parameterName,HttpStatus.BAD_REQUEST);
	}
}
