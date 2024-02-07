package com.github.juhachmann.estagios.unit.data.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.juhachmann.estagios.resources.authUserConfig.AuthUserConfigDTO;
import com.github.juhachmann.estagios.resources.authUserConfig.AuthUserConfigMock;

class ConfigDTOValidationTest extends ValidationTest<AuthUserConfigDTO> {

	@BeforeEach
	void setUp() throws Exception {
		super.resource = new AuthUserConfigDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void mockMustGenerateValidResource() {
		validate();
		assertFalse(super.violations.isEmpty());		
		
		resource = AuthUserConfigMock.generateResource(  );
		validate();
		assertTrue(super.violations.isEmpty());
		
		
		resource = AuthUserConfigMock.generateResource( );
		validate();
		assertTrue(super.violations.isEmpty());
		
	}
	
	@Test
	void mockGenerateInvalidResourceIsNotValid() {
		validate();
		assertFalse(super.violations.isEmpty());		
		
		resource = AuthUserConfigMock.generateInvalid();
		validate();
		assertFalse(super.violations.isEmpty());
		
	}
	

}
