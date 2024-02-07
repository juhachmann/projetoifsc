package com.github.juhachmann.estagios.utils;

import com.github.juhachmann.estagios.exceptions.InvalidParamRequestException;

public class PaginationValidation {

	
	public static final String DEFAULT_PAGE_VALUE = "1"; // OFFSET
	public static final String DEFAULT_LIMIT_VALUE = "10";
	public static final int MAX_LIMIT_VALUE = 30;
	
	
	private void validateMaxLimitPageableValue (Integer givenValue) {
		Integer maxValue = Integer.valueOf(MAX_LIMIT_VALUE) ;
		if (givenValue > maxValue ) {
			throw new InvalidParamRequestException(givenValue, maxValue);
		}
	}

	private void validateLimitAndOffset (Integer limit, Integer offset) { // TODO - essa é a validação do parâmetro para autenticação
		if ( limit < 1 || offset < 1)
			throw new InvalidParamRequestException("");
		validateMaxLimitPageableValue(limit);
	}

	
}
