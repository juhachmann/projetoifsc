package com.github.juhachmann.estagios.integration.controller;

// TODO DÚVIDA - Não estou conseguindo fazer os testes de unidade do controller, porque não consigo aplicar os mocks corretamente

//import static io.restassured.RestAssured.given;

//import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
//import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*; 
import static io.restassured.RestAssured.given;

//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.juhachmann.estagios.perfil.PerfilController;
import com.github.juhachmann.estagios.perfil.PerfilPublicoDTO;

import io.restassured.http.ContentType;

// TODO Usar MockMVC para teste unitário dos controllers: https://www.baeldung.com/spring-mock-mvc-rest-assured
// Porque com o mockito não consegui fazer funcionar

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(MockMvc.class)
class ControllerIntegrationTestPerfilControllerWIthMockMvc {
	
//	@Autowired
//	WebApplicationContext webApplicationContext;
	
//	@Mock
//	MockPerfilRepository service;
//
//	@InjectMocks
	
	@Autowired
	PerfilController controller;
	
	Integer id;
	String response;
	
	PerfilPublicoDTO resource;

	
	
	@Before
	public void initialiseRestAssuredMockMvcWebApplicationContext() {
	    // RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
		//MockitoAnnotations.openMocks(controller);
//		RestAssuredMockMvc.standaloneSetup(new PerfilController());
	   
	}
	
	@BeforeEach
	void setUp() throws Exception {
//		RestAssuredMockMvc.standaloneSetup(new PerfilController());
//		RestAssuredMockMvc.standaloneSetup(controller);
		resource = new PerfilPublicoDTO();
		// this must be called for the @Mock annotations above to be processed.
        //MockitoAnnotations.openMocks(this);
    //    MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println(id);
		System.out.println(response);
	}
		
	@Test
	void contextLoads() {
//		assertNotNull(controller);
//		assertNotNull(service);
	}

	// Status Code
	
	@Order(1)
	@Test
	void postMustReturnStatusCode201() {
	
		//when(service.create(resource)).thenReturn(resource);
		
		id = 
		given()
			.contentType(ContentType.JSON)
		. when()
			.post("/perfil")
		.then()
			.statusCode(201)
			.extract()
			.path("id")
			;
			
		getMustReturnStatusCode200(id);
		putMustReturnStatusCode200(id);
		deleteMustReturnStatusCode204(id);
		
		
//			.apply(print());
	}
	
//	@Order(2)
//	@Test
	void getMustReturnStatusCode200(long id) {

//		when(service.getById(id)).thenReturn(resource);
		
		response = 
		given()
			.header("Authorization" , id)
		.when()
			.get("/perfil")
		.then()
			.statusCode(200)
//			.apply(print());
			.extract()
			.asPrettyString();
		
		System.out.println(response);
	}

//	@Order(3)
//	@Test
	void putMustReturnStatusCode200(long id) {
//		when(service.update(id, resource)).thenReturn(resource);
		
		response = 
				given()
					.header("Authorization" , id)
					.contentType(ContentType.JSON)
					.body(resource)
				. when()
					.put("/perfil")
				.then()
					.statusCode(200)
//					.apply(print());
					.extract()
					.asPrettyString();
		System.out.println(response);
	}

//	@Order(4)
//	@Test
	void deleteMustReturnStatusCode204(long id) {		
//		doNothing().when(service).delete(id);
		
		response = 
		given()
			.header("Authorization" , id)
		.when()
			.delete("/perfil")
		.then()
			.statusCode(204)
//			.apply(print());
			.extract()
			.asPrettyString();	
		System.out.println(response);
	}

	
	// Testing for accepted media types
	@Order(5)
	@Test
	void postAcceptedAndResponseContentTypeMustMacth() {
		
		List<ContentType> accepted = new ArrayList<>();
		accepted.add(ContentType.JSON);
		accepted.add(ContentType.XML);
		// TODO Testar com APPLICATION_HAL e APPLICATION_HAL-FORMS
		
		accepted.forEach((type) -> {
			given()
				.contentType(ContentType.JSON)
				.accept(type)
			. when()
				.post("/perfil")
			.then()
				.contentType(type)
//				.apply(print());
				;
		});
	}
	
}
