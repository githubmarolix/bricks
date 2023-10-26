package com.marolix.Bricks99.utility;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.marolix.Bricks99.exception.Bricks99Exception;

@RestControllerAdvice
public class Bricks99ControllerAdvice {
	@Autowired
	private Environment environment;
	private static final Log logger = LogFactory.getLog(Bricks99ControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exp) {
		ErrorInfo e = new ErrorInfo();
		e.setTime(LocalDateTime.now());
		e.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		e.setErrorMessage("Something went Wrong");

		return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Bricks99Exception.class)
	public ResponseEntity<ErrorInfo> Bricks99ExceptionHanler(Bricks99Exception exp) {
		ErrorInfo e = new ErrorInfo();
		e.setTime(LocalDateTime.now());
		e.setErrorCode(HttpStatus.BAD_REQUEST.value());
		e.setErrorMessage(exp.getMessage());

		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ConstraintViolationException.class, MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorInfo> ExceptionHanler(Exception exp) {
		if (exp instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> c = ((ConstraintViolationException) exp).getConstraintViolations();
			ErrorInfo e = new ErrorInfo();
			e.setTime(LocalDateTime.now());
			e.setErrorCode(HttpStatus.BAD_REQUEST.value());
			e.setErrorMessage(c.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));

			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} else {
			ErrorInfo error = new ErrorInfo();
			error.setTime(LocalDateTime.now());
			error.setErrorCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(((MethodArgumentNotValidException) exp).getBindingResult().getAllErrors().stream()
					.map(t->t.getDefaultMessage()).collect(Collectors.joining(", ")));
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	}

}
