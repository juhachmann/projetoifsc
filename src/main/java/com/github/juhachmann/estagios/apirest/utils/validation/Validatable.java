package com.github.juhachmann.estagios.apirest.utils.validation;

import com.github.juhachmann.estagios.apirest.exceptions.InvalidException;

/**
 * Interface to add validation behaviour
 * 
 */
public interface Validatable {
	
	/**
	 * Validates the resource
	 * 
	 * @throws InvalidException
	 */
	void validate() throws InvalidException;

}
