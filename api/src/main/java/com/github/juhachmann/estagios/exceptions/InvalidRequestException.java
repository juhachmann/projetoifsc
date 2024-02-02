package com.github.juhachmann.estagios.exceptions;

import java.io.Serializable;
import java.util.Set;

import com.github.juhachmann.estagios.data.dto.PerfilDTO;

import jakarta.validation.ConstraintViolation;

public class InvalidRequestException extends RuntimeException {

	
	public InvalidRequestException(Set<ConstraintViolation<Serializable>> violations) {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	
	

}
