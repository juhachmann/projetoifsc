package com.github.juhachmann.estagios.exceptions;

import java.io.Serializable;
import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class InvalidBodyRequestException extends RuntimeException {

	
	public InvalidBodyRequestException(Set<ConstraintViolation<Serializable>> violations) {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	
	

}
