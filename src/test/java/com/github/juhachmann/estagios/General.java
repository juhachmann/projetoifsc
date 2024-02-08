package com.github.juhachmann.estagios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.juhachmann.estagios.api.exceptions.InvalidException;
import com.github.juhachmann.estagios.api.resources.authUserConfig.AuthUserConfigDTO;
import com.github.juhachmann.estagios.api.resources.authUserConfig.AuthUserConfigMock;
import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilController;
import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilDTO;
import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilMock;
import com.github.juhachmann.estagios.api.resources.userPerfil.UserPerfilDTO;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

class General {
	
	ObjectMapper mapper = new ObjectMapper();

	private void print(String str) {
		System.out.println(str);
	}
	
	private void print(Object obj) {
		System.out.println(obj.toString());
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws Exception {
		
		
		System.out.println("Validating UserPerfilDTO");
		UserPerfilDTO perfil = AuthUserPerfilMock.generateInvalidResource(0);
		assertThrows(InvalidException.class, () -> perfil.validate());
		
		System.out.println("Validating AuthUserPerfilDTO");
		AuthUserPerfilDTO perfilB = AuthUserPerfilMock.generateInvalidResource(0);
		assertThrows(InvalidException.class, () -> perfilB.validate());

	}

}
