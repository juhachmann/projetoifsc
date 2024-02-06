package com.github.juhachmann.estagios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.juhachmann.estagios.perfil.ConfigDTO;
import com.github.juhachmann.estagios.perfil.PerfilController;
import com.github.juhachmann.estagios.perfil.PerfilDTO;
import com.github.juhachmann.estagios.perfil.PerfilPublicoDTO;

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
		
		
		PerfilDTO ju = new PerfilDTO();
		
		ResponseEntity<PerfilDTO> response = new ResponseEntity<PerfilDTO>(ju, HttpStatus.OK);
		
		
	}

}
