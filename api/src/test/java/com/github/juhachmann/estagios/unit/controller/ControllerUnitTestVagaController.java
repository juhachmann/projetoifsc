package com.github.juhachmann.estagios.unit.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.juhachmann.estagios.exceptions.InvalidHeaderRequestException;
import com.github.juhachmann.estagios.exceptions.InvalidParamRequestException;
import com.github.juhachmann.estagios.vagas.MockVagaDTO;
import com.github.juhachmann.estagios.vagas.VagaPrivadaController;
import com.github.juhachmann.estagios.vagas.VagaDTO;
import com.github.juhachmann.estagios.vagas.VagaPrivadaService;

@ExtendWith(MockitoExtension.class)
public class ControllerUnitTestVagaController {
	
	VagaDTO vaga;
	ResponseEntity<VagaDTO> response;
	HttpStatus status;
	//Long ownerId = 1L;
	Long userId = 1L;
	
	@Mock
	VagaPrivadaService service;

	@InjectMocks
	VagaPrivadaController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		vaga = new VagaDTO();
		vaga.setKey(1L);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	void commomAsserts() {
		assertInstanceOf(ResponseEntity.class, response );
		assertEquals(status, response.getStatusCode());
		assertInstanceOf(VagaDTO.class, response.getBody());
	}
	

	@Test
	void createMustReturnResponseEntityWithStatusCode201() throws Exception {
		when(service.create(userId, vaga)).thenReturn(vaga);
		response = controller.create(userId, vaga);
		status = HttpStatus.CREATED;
		
		commomAsserts();
	}

	
	@Test
	void getMustReturnResponseEntityWithStatusCode200() throws Exception {
		Pageable pageable = PageRequest.of(5, 5);
		Page<VagaDTO> page = Page.empty(pageable);
		when(service.getAll ( userId, pageable ) ).thenReturn( page );
		ResponseEntity<Page<VagaDTO>> response = controller.getAll( userId, 5, 5 );
		status = HttpStatus.OK;
		
		assertInstanceOf(ResponseEntity.class, response );
		assertEquals(status, response.getStatusCode());
		assertInstanceOf(Page.class, response.getBody());
		response.getBody().forEach(vaga -> assertInstanceOf(VagaDTO.class, vaga));

	}
	
	
	@Test
	void getVagasForMeMustReturnResponseEntityWithStatusCode200() throws Exception {
		
		Pageable pageable = PageRequest.of(5, 5);
		Page<VagaDTO> page = Page.empty(pageable);
		when(service.getAllForMe ( userId, pageable ) ).thenReturn( page );
		ResponseEntity<Page<VagaDTO>> response = controller.getAllForMe ( userId, 5, 5 );
		status = HttpStatus.OK;
		
		assertInstanceOf(ResponseEntity.class, response );
		assertEquals(status, response.getStatusCode());
		assertInstanceOf(Page.class, response.getBody());
		response.getBody().forEach(vaga -> assertInstanceOf(VagaDTO.class, vaga));

	}
	
	
	@Test
	void getByIdMustReturnResponseEntityWithStatusCode200() throws Exception {
		when(service.getOne ( userId, vaga.getKey() ) ).thenReturn( vaga );
		response = controller.getOne ( userId, vaga.getKey() );
		status = HttpStatus.OK;		
		
		commomAsserts();
	}

	
	@Test
	void updateMustReturnResponseEntityWithStatusCode200() throws Exception {
		when(service.updateOne (userId, vaga.getKey(), vaga)).thenReturn(vaga);
		response = controller.updateOne (userId, vaga.getKey(), vaga);
		status = HttpStatus.OK;
		
		commomAsserts();
	}
	

	@Test
	void deleteMustReturnEmptyResponseEntityWithStatusCode204() throws Exception {
		doNothing().when(service).deleteOne (userId, vaga.getKey());
		response = controller.deleteOne (userId, vaga.getKey());
		status = HttpStatus.NO_CONTENT;
		
		assertInstanceOf(ResponseEntity.class, response );
		assertEquals(status, response.getStatusCode());
		assertNull(response.getBody());
	}
	
	@Test 
	void operationsMustValidateUserIdParams() {
		Long[] invalidParams = {0L, -1L};
		for (Long param : invalidParams) {
			assertThrows(InvalidHeaderRequestException.class, () -> controller.getAll(param, 1, 1));
			assertThrows(InvalidHeaderRequestException.class, () -> controller.getAllForMe(param, 1, 1));
			assertThrows(InvalidHeaderRequestException.class, () -> controller.create(param, vaga));
			assertThrows(InvalidHeaderRequestException.class, () -> controller.getOne(param, 1L));
			assertThrows(InvalidHeaderRequestException.class, () -> controller.updateOne(param, 1L, vaga));
			assertThrows(InvalidHeaderRequestException.class, () -> controller.deleteOne(param, 1L));
		}
		
		Long param = 1L; 
		assertDoesNotThrow(() -> controller.getAll(param, 1, 1));
		assertDoesNotThrow(() -> controller.getAllForMe(param, 1, 1));
		assertDoesNotThrow(() -> controller.create(param, vaga));
		assertDoesNotThrow(() -> controller.getOne(param, 1L));
		assertDoesNotThrow(() -> controller.updateOne(param, 1L, vaga));
		assertDoesNotThrow(() -> controller.deleteOne(param, 1L));

	}
	
	@Test 
	void operationsMustValidateVagaIdParams() {
		Long[] invalidParams = {0L, -1L};
		for (Long param : invalidParams) {
			assertThrows(InvalidParamRequestException.class, () -> controller.getOne(userId, param));
			assertThrows(InvalidParamRequestException.class, () -> controller.updateOne(userId, param, vaga));
			assertThrows(InvalidParamRequestException.class, () -> controller.deleteOne(userId, param));
		}
		
		Long param = 1L; 
		assertDoesNotThrow(() -> controller.getOne(userId, param));
		assertDoesNotThrow(() -> controller.updateOne(userId, param, vaga));
		assertDoesNotThrow(() -> controller.deleteOne(userId, param));
	}

	@Test 
	void operationsMustValidateLimitAndOffsetParams() {
		String MAX_PAGE_VALUE = "30"; // TODO - Pegar do lugar definitivo onde isso vai ficar

		Integer[] invalidLimit = {0, -1, Integer.valueOf(MAX_PAGE_VALUE) + 1};
		for (Integer invalid : invalidLimit) {
			assertThrows(InvalidParamRequestException.class, () -> controller.getAll(userId, invalid, 1));
			assertThrows(InvalidParamRequestException.class, () -> controller.getAllForMe(userId, invalid, 1));
		}

		Integer[] validLimit = {1, Integer.valueOf(MAX_PAGE_VALUE)};
		for (Integer valid : validLimit) {
			assertDoesNotThrow(() -> controller.getAll(userId, valid, 1));
			assertDoesNotThrow(() -> controller.getAllForMe(userId, valid, 1));
		}
		
		
		Integer[] invalidOffset = {0, -1};
		for (Integer invalid : invalidOffset) {
			assertThrows(InvalidParamRequestException.class, () -> controller.getAll(userId, 1, invalid));
			assertThrows(InvalidParamRequestException.class, () -> controller.getAllForMe(userId, 1, invalid));
		}
		
		Integer validOffset = 1; 
		assertDoesNotThrow(() -> controller.getAll(userId, 1, validOffset));
		assertDoesNotThrow(() -> controller.getAllForMe(userId, 1, validOffset));

	}

	
//
//	@Test
//	void updateMustReturnResponseEntityWithStatusCode200() throws Exception {
//		HashMap<Long, VagaDTO> hash = new HashMap<>();
//		var vagaA = new VagaDTO();
//		var vagaB = new VagaDTO();
//		hash.put(1L, vagaA);
//		hash.put(2L, vagaB);
//		List<VagaDTO> list = new ArrayList<>();
//		list.add(vagaA);
//		list.add(vagaB);
//		
//		when(service.update(ownerId, hash)).thenReturn(list);
//		ResponseEntity<List<VagaDTO>> response = controller.update(ownerId, hash);
//		status = HttpStatus.OK;
//		
//		assertInstanceOf(ResponseEntity.class, response );
//		assertEquals(status, response.getStatusCode());
//		response.getBody().forEach(vaga -> assertInstanceOf(VagaDTO.class, vaga));
//		
//	}
//
//	@Test
//	void deleteMustReturnEmptyResponseEntityWithStatusCode204() throws Exception {
//		Long[] list = {1L, 2L, 3L};
//		doNothing().when(service).delete(list);
//		response = controller.delete(list);
//		status = HttpStatus.NO_CONTENT;
//		
//		assertInstanceOf(ResponseEntity.class, response );
//		assertEquals(status, response.getStatusCode());
//		assertNull(response.getBody());
//		
//	}



}
