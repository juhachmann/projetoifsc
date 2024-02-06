package com.github.juhachmann.estagios.integration.controller;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import com.github.juhachmann.estagios.perfil.PerfilPublicoDTO;
import com.github.juhachmann.estagios.utils.MediaTypes;


@SpringBootTest
class ControllerIntegrationTestPerfilController {

	static List<String> acceptedContentTypes = new ArrayList<>();
	static List<String> producedContentTypes = new ArrayList<>();
		
	PerfilPublicoDTO resource;
		
	@BeforeAll
	static void config() {
	
		acceptedContentTypes.add(MediaTypes.APPLICATION_JSON);
		acceptedContentTypes.add(MediaTypes.APPLICATION_XML);
		acceptedContentTypes.add(MediaTypes.APPLICATION_YAML);
		
		producedContentTypes.add(MediaTypes.APPLICATION_JSON);
		producedContentTypes.add(MediaTypes.APPLICATION_XML);
		producedContentTypes.add(MediaTypes.APPLICATION_YAML);
		producedContentTypes.add(MediaTypes.APPLICATION_HAL);
		producedContentTypes.add(MediaTypes.APPLICATION_HAL_FORMS);
	}
	
	@BeforeEach
	void setUp() throws Exception {
		resource = new PerfilPublicoDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	// POST "/perfil"
	
	// Request

	@Test
	void postAcceptsBodyDataAndContentTypes() {
		acceptedContentTypes.forEach((type) -> {
			given()
				.contentType(type)
			.when()
				.post("/perfil")
			.then()
				.statusCode(201);
		});
	}
	
	@Test
	void postDoesNotNeedAuth() {
		given()
			.contentType(acceptedContentTypes.get(0))
			//.header("Authorization", null)
		.when()
			.post("/perfil")
		.then()
			.statusCode(201);
	}

	@Test
	void postRespondsWithContentTypes() {
		producedContentTypes.forEach((produced) -> {
			System.out.println("Testing for " + produced);
			String response = 
			given()
				.contentType(acceptedContentTypes.get(0))
				.header(HttpHeaders.ACCEPT, produced)
			//.header("Authorization", null)
			.when()
				.post("/perfil")
			.then()
				.statusCode(201)
				.contentType(produced)
				.extract()
				.asPrettyString()
			;
			System.out.println(response);
		});
	}
		
	
	@Test
	void postValidatesRequestData() {
		PerfilPublicoDTO invalidData = new PerfilPublicoDTO();
		
		
		
	}
	
//	// Responses
//	
//	// Success Action
//	
//	@Test
//	void postCreatesNewResource() {
//		fail("Not yet implemented");
//	}
//		
//	@Test
//	void responseHas201StatusCodeAndCreatedObject() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void responseHasHATEOASLinks() {
//		fail("Not yet implemented");
//	}
//	
//	// Error
//	
//	@Test
//	void wrongBodyDataRespondsWith404BadRequest() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void rateLimitIsAppliedAndRespondsWith429TooManyRequests() {
//		fail("Not yet implemented");
//	}
//	
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
