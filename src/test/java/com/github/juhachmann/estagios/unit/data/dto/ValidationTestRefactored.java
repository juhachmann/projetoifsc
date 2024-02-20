package com.github.juhachmann.estagios.unit.data.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.juhachmann.estagios.apirest.utils.Validatable;

/** 
 * Validation Test for {@link Validatable}
 * 
 */
public class ValidationTestRefactored<E extends Validatable> {

	protected String propertyBeingTested;
	
	protected ObjectMapper jsonMapper = new ObjectMapper();
	
	protected E resource;
	
	
	/**
	 * Validates the {@link Validatable} resource 
	 * 
	 * @return Error Messages or blank message
	 * @throws JsonProcessingException
	 */
	protected String validate() throws JsonProcessingException {	
		try {
			resource.validate();
			return "";
		}
		catch (Exception e) {
			System.out.println(jsonMapper.writeValueAsString(resource));
			return e.getMessage();
		}
	}
	
}
