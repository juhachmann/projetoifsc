package com.github.projetoifsc.pi.apirest.utils.validation;

import com.github.projetoifsc.pi.apirest.exceptions.InvalidException;

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
