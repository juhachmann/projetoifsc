package com.github.juhachmann.estagios.api.utils;

import com.github.juhachmann.estagios.api.exceptions.InvalidParamRequestException;

public class PaginationValidation {


	public static final String DEFAULT_PAGE_VALUE = "1"; // OFFSET
	public static final String DEFAULT_LIMIT_VALUE = "10";
	public static final int MAX_LIMIT_VALUE = 30;
	
	
	private void validateMaxLimitPageableValue (Integer givenValue) {
		Integer maxValue = MAX_LIMIT_VALUE;
		if (givenValue > maxValue ) {
			throw new InvalidParamRequestException(givenValue, maxValue);
		}
	}

	public void validateLimitAndOffset (Integer limit, Integer offset) { // TODO - essa é a validação do parâmetro para autenticação
		if ( limit < 1 || offset < 1)
			throw new InvalidParamRequestException("");
		validateMaxLimitPageableValue(limit);
	}

	
}
