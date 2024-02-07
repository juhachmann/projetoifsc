package com.github.juhachmann.estagios.utils;

import com.github.juhachmann.estagios.exceptions.InvalidException;

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
