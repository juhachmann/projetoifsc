package com.github.projetoifsc.pi.apirest.utils.validation;

import com.github.projetoifsc.pi.apirest.exceptions.InvalidHeaderRequestException;
import com.github.projetoifsc.pi.apirest.exceptions.InvalidParamRequestException;

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
