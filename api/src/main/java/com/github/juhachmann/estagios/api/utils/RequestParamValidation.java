package com.github.juhachmann.estagios.api.utils;

import com.github.juhachmann.estagios.api.exceptions.InvalidHeaderRequestException;
import com.github.juhachmann.estagios.api.exceptions.InvalidParamRequestException;

public class RequestParamValidation {
	
	public void validateUserId (Long userId) { // TODO - essa é a validação do parâmetro para autenticação
		if ( userId == null || userId < 1 )
			throw new InvalidHeaderRequestException("");
	}
	

	public void validateVagaId (Long vagaId) { // TODO - essa é a validação do parâmetro para autenticação
		if ( vagaId < 1 )
			throw new InvalidParamRequestException("");
	}

	
}
