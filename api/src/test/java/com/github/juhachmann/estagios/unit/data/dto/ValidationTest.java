package com.github.juhachmann.estagios.unit.data.dto;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidationTest<T> {
	
	protected String propertyPathBeingTested;
	
	protected static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	protected static final Validator validator = factory.getValidator();
	
	protected ObjectMapper jsonMapper = new ObjectMapper();
		
	protected Set<ConstraintViolation<T>> violations;
	
	protected T resource;
	
	protected void printViolations( ) throws JsonProcessingException {
		System.out.println(jsonMapper.writeValueAsString(resource));
		System.out.println(violations.toString());
		for(ConstraintViolation<T> violation : violations ) {
			System.out.println(
					"O campo " + 
					"\"" + violation.getPropertyPath() + "\"" +
					" " +
					violation.getMessage() + 
					". Você forneceu: " + 
					"\"" + violation.getInvalidValue() + "\""
			);
		}
	}
	
	protected void validate() {
		violations = validator.validate(resource);
	}

}
