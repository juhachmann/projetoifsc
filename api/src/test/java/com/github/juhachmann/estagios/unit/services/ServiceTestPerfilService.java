package com.github.juhachmann.estagios.unit.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.juhachmann.estagios.data.dto.ConfigDTO;
import com.github.juhachmann.estagios.data.dto.PerfilDTO;
import com.github.juhachmann.estagios.data.mock.MockConfigDTO;
import com.github.juhachmann.estagios.data.mock.MockPerfilDto;
import com.github.juhachmann.estagios.data.mock.NotificationSettingsFactory;
import com.github.juhachmann.estagios.exceptions.InvalidRequestException;
import com.github.juhachmann.estagios.exceptions.UnauthorizedRequestException;
import com.github.juhachmann.estagios.repository.MockConfigRepository;
import com.github.juhachmann.estagios.repository.MockPerfilRepository;
import com.github.juhachmann.estagios.services.PerfilService;
import com.github.juhachmann.estagios.utils.MediaTypes;


@ExtendWith(MockitoExtension.class)

class ServiceTestPerfilService {
	
	@Mock
	MockPerfilRepository repo;
	
	@Mock 
	MockConfigRepository configRepo;
	
	@InjectMocks
	PerfilService service;
	
	static List<String> acceptedContentTypes = new ArrayList<>();
	static List<String> producedContentTypes = new ArrayList<>();
		
	PerfilDTO resource;
	PerfilDTO invalidResource;
	Long correctId = 1L;
	
	static ConfigDTO configs;
	
	static List<String> HATEOASLinks = new ArrayList<>();
		
	@BeforeAll
	static void config() {

		acceptedContentTypes = new ArrayList<>();
		acceptedContentTypes.add(MediaTypes.APPLICATION_JSON);
		acceptedContentTypes.add(MediaTypes.APPLICATION_XML);
		acceptedContentTypes.add(MediaTypes.APPLICATION_YAML);	
	
		producedContentTypes = new ArrayList<>();
		producedContentTypes.add(MediaTypes.APPLICATION_JSON);
		producedContentTypes.add(MediaTypes.APPLICATION_XML);
		producedContentTypes.add(MediaTypes.APPLICATION_YAML);
		producedContentTypes.add(MediaTypes.APPLICATION_HAL);
		producedContentTypes.add(MediaTypes.APPLICATION_HAL_FORMS);
		
		HATEOASLinks.add("self");
		HATEOASLinks.add("configs");
		HATEOASLinks.add("vagas");
		HATEOASLinks.add("perfil_publico");
		
		configs = MockConfigDTO.generateResource( new NotificationSettingsFactory() );
	}
	
	@BeforeEach
	void setUp() throws Exception {
		resource = MockPerfilDto.generateResource();
		resource.setKey(correctId);
		
		invalidResource = new PerfilDTO();
		invalidResource.setKey(correctId);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	void checkHateoasLinks(PerfilDTO perfil) {
		HATEOASLinks.forEach((link) -> {
			assertNotNull(perfil.getRequiredLink(link));
		});
		assertTrue(perfil.getLinks().hasSize(4));
	}

	
	// CREATE		

	@Test
	void createMustValidateData() {		
		when(repo.create(resource)).thenReturn(resource);
		assertDoesNotThrow(() -> service.create(resource));
	}
	
	// Responses
	
	@Test
	void createMustReturnCreatedInstance() throws Exception {
		when(repo.create(resource)).thenReturn(resource);
		assertEquals(resource, service.create(resource));
		assertInstanceOf(PerfilDTO.class, service.create(resource));
	}

	@Test
	void createdInstanceMustHave4Links() throws Exception {
		when(repo.create(resource)).thenReturn(resource);
		var created = service.create(resource);
		checkHateoasLinks(created);
	}		
		
	@Test
	void nonValidatedRequestReturnsInvalidRequestException() {
		assertThrows(InvalidRequestException.class, () -> service.create(invalidResource));
	}
	
	
	// GET

	// Responses
	@Test
	void getMustReturnOneCorrectItem() throws Exception {
		when(repo.getById(correctId)).thenReturn(resource);
		assertEquals(resource, service.get(correctId));
		assertInstanceOf(PerfilDTO.class, service.get(correctId));
	}
	
	@Test
	void getMustReturnInstanceWith4Links() throws Exception {
		when(repo.getById(resource.getKey())).thenReturn(resource);
		var perfil = service.get(resource.getKey());
		checkHateoasLinks(perfil);
	}
	
	// UPDATE
	// Request
	@Test
	void updateValidateRequestData() {
		when(repo.update(resource.getKey(), resource)).thenReturn(resource);
		assertDoesNotThrow(() -> service.update(resource));
	}
	
	// Responses
	@Test
	void updateReturnsOneCorrectItem() throws Exception {
		when(repo.update(resource.getKey(), resource)).thenReturn(resource);
		assertEquals(resource, service.update(resource));
	}
	
	@Test
	void updateMustReturnItemWith4Links() throws Exception {
		when(repo.update(resource.getKey(), resource)).thenReturn(resource);
		PerfilDTO perfil = service.update(resource);
		checkHateoasLinks(perfil);
	}
	
	@Test
	void nonValidatedUpdateRequestReturnsInvalidRequestException() {
		assertThrows(InvalidRequestException.class, () -> service.update(invalidResource));
	}
	
	@Test
	void getConfigMusReturnOneItem() {
		when(configRepo.getById(1L)).thenReturn(configs);
		assertEquals(configs, service.getPerfilConfig(1L));
	}
	
	@Test
	void getConfigReturnsItemWith2Links() {
		when(configRepo.getById(1L)).thenReturn(configs);
		ConfigDTO configs = service.getPerfilConfig(1L);
		assertNotNull(configs.getRequiredLink("self"));
		assertNotNull(configs.getRequiredLink("perfil"));
	}
	
	@Test
	void updateConfigMustReturnOneItemWith2Links() {
		when(configRepo.update(1L, configs)).thenReturn(configs);
		ConfigDTO updated = service.updateConfig(1L, configs);
		assertEquals(configs, updated);
		assertNotNull(configs.getRequiredLink("self"));
		assertNotNull(configs.getRequiredLink("perfil"));
	}
	
	@Test
	void updateConfigMustValidateRequest() {
		when(configRepo.update(1L, configs)).thenReturn(configs);
		assertDoesNotThrow(() -> service.updateConfig(1L, configs));
	}
	
	@Test
	void invalidUpdateMustReturnInvalidRequestException() {
		assertThrows(InvalidRequestException.class, () -> service.updateConfig(1L, new ConfigDTO()));
	}
	
//	@Test
//	void name() {
//		
//	}
//	@Test
//	void name() {
//		
//	}
	
	
}
