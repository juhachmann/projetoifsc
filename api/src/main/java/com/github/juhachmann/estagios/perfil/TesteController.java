package com.github.juhachmann.estagios.perfil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.juhachmann.estagios.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class TesteController {

	
	@PostMapping(value = "/perfil/teste", consumes = MediaTypes.APPLICATION_JSON, produces = MediaTypes.APPLICATION_JSON)
	@Operation()
	@ApiResponses()
	public TesteDTO create(
			@RequestBody() TesteDTO resource) {
		return resource;
	}
	
}
