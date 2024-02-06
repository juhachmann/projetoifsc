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

import com.github.juhachmann.estagios.perfil.ConfigDTO;
import com.github.juhachmann.estagios.perfil.PerfilPrivadoController;
import com.github.juhachmann.estagios.perfil.PerfilPrivadoDTO;
import com.github.juhachmann.estagios.perfil.PerfilPrivadoService;

@ExtendWith(MockitoExtension.class)
class ControllerUnitTestPerfilController {
	
	PerfilPrivadoDTO perfil;
	ConfigDTO config; 
	
	@Mock
	PerfilPrivadoService service;

	@InjectMocks
	PerfilPrivadoController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		perfil = new PerfilPrivadoDTO();
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
		assertInstanceOf(PerfilPrivadoDTO.class, response.getBody());
	}

	@Test
	void getMustReturnResponseEntityWithStatusCode200() throws Exception {
		when(service.get ( perfil.getKey() ) ).thenReturn( perfil );
		var response = controller.get( perfil.getKey() );
		
		System.out.println(response.getBody().getKey());
		
		assertInstanceOf(ResponseEntity.class, response );
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertInstanceOf(PerfilPrivadoDTO.class, response.getBody());
	}

	@Test
	void updateMustReturnResponseEntityWithStatusCode200() throws Exception {
		when(service.update(perfil)).thenReturn(perfil);
		var response = controller.update(perfil.getKey(), perfil);
		
		assertInstanceOf(ResponseEntity.class, response );
		assertInstanceOf(PerfilPrivadoDTO.class, response.getBody());
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

	@Test
	void getPerfilMustReturnResponseEntityWithStatusCode200() throws Exception {
		config = new ConfigDTO();
		when(service.getPerfilConfig(1L)).thenReturn(config);
		var response = controller.getConfigs(1L);
		
		assertInstanceOf(ResponseEntity.class, response );
		assertInstanceOf(ConfigDTO.class, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void updatePerfilMustReturnResponseEntityWithStatusCode200() throws Exception {
		config = new ConfigDTO();
		when(service.updateConfig(0, config)).thenReturn(config);
		var response = controller.updateConfigs(0, config);
		
		assertInstanceOf(ResponseEntity.class, response );
		assertInstanceOf(ConfigDTO.class, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	

}
