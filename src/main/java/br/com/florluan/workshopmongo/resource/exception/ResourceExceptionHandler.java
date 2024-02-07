package br.com.florluan.workshopmongo.resource.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.florluan.workshopmongo.service.exception.ObjectNotFoundException;

@ControllerAdvice
@RestController
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, WebRequest web){
		StandardError error = new StandardError(
			new Date(),
			ex.getMessage(),
			web.getDescription(false)
		);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
