package com.github.juhachmann.estagios.api.utils;

import com.github.juhachmann.estagios.api.exceptions.InvalidException;

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
