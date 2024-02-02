package com.github.juhachmann.estagios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.juhachmann.estagios.data.dto.ConfigDTO;
import com.github.juhachmann.estagios.data.dto.PerfilDTO;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

class General {
	
	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws JsonMappingException, JsonProcessingException {
		
		String jsonString = "{ \"name\" : \"Juliana\" }";
		
		System.out.println(
				mapper.readValue(jsonString, PerfilDTO.class).toString()
				
				);
		
		var ju = new PerfilDTO();
		//ju.getRequiredLink("self").;
		
		
	}

}
