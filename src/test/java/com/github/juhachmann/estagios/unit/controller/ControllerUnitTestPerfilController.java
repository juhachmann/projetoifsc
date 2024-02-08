package com.github.juhachmann.estagios.unit.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.juhachmann.estagios.api.resources.authUserConfig.AuthUserConfigDTO;
import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilController;
import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilDTO;
import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilService;

@ExtendWith(MockitoExtension.class)
class ControllerUnitTestPerfilController {
	
	AuthUserPerfilDTO perfil;
	AuthUserConfigDTO config; 
	
	@Mock
	AuthUserPerfilService service;

	@InjectMocks
	AuthUserPerfilController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		perfil = new AuthUserPerfilDTO();
		perfil.setKey(1L);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void createMustReturnResponseEntityWithStatusCode201() throws Exception {
		when(service.create(perfil)).thenReturn(perfil);
		var response = controller.create(perfil);
		
		assertInstanceOf(ResponseEntity.class, response );
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertInstanceOf(AuthUserPerfilDTO.class, response.getBody());
	}

	@Test
	void getMustReturnResponseEntityWithStatusCode200() throws Exception {
		when(service.get ( perfil.getKey() ) ).thenReturn( perfil );
		var response = controller.get( perfil.getKey() );
		
		System.out.println(response.getBody().getKey());
		
		assertInstanceOf(ResponseEntity.class, response );
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertInstanceOf(AuthUserPerfilDTO.class, response.getBody());
	}

	@Test
	void updateMustReturnResponseEntityWithStatusCode200() throws Exception {
		when(service.update(perfil.getKey(), perfil)).thenReturn(perfil);
		var response = controller.update(perfil.getKey(), perfil);
		
		assertInstanceOf(ResponseEntity.class, response );
		assertInstanceOf(AuthUserPerfilDTO.class, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void deleteMustReturnEmptyResponseEntityWithStatusCode204() throws Exception {
		doNothing().when(service).delete(perfil.getKey());
		var response = controller.delete(perfil.getKey());
		
		assertInstanceOf(ResponseEntity.class, response );
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertNull(response.getBody());
	}
	

}
