package com.github.juhachmann.estagios.api.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/teste")
public class TesteController {

	
	@GetMapping("")
	public String testeComQueryParams(
			@RequestParam(value = "message", defaultValue = "Olá") String message,
			@RequestParam(value = "interlocutor", defaultValue = "Mundo") String interlocutor,
			@RequestParam(value = "signature", defaultValue="") String signature
	) {
		String greeting = "";
		
		return greeting.concat(message).concat(", " + interlocutor + "! \n").concat(signature) ;
	}
	
}
