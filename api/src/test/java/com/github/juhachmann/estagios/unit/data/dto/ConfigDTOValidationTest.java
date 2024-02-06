package com.github.juhachmann.estagios.unit.data.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.juhachmann.estagios.perfil.ConfigDTO;
import com.github.juhachmann.estagios.perfil.MockConfigDTO;
import com.github.juhachmann.estagios.perfil.NotificationSettingsFactory;
import com.github.juhachmann.estagios.perfil.SettingsFactory;

class ConfigDTOValidationTest extends ValidationTest<ConfigDTO> {

	@BeforeEach
	void setUp() throws Exception {
		super.resource = new ConfigDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		super.printViolations();
	}

	@Test
	void mockMustGenerateValidResource() {
		validate();
		assertFalse(super.violations.isEmpty());		
		
		resource = MockConfigDTO.generateResource(  );
		validate();
		assertTrue(super.violations.isEmpty());
		
		
		resource = MockConfigDTO.generateResource( );
		validate();
		assertTrue(super.violations.isEmpty());
		
	}
	
	@Test
	void mockGenerateInvalidResourceIsNotValid() {
		validate();
		assertFalse(super.violations.isEmpty());		
		
		resource = MockConfigDTO.generateInvalid();
		validate();
		assertFalse(super.violations.isEmpty());
		
	}
	

}
