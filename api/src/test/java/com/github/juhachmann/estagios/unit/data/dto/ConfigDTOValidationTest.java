package com.github.juhachmann.estagios.unit.data.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.juhachmann.estagios.data.dto.ConfigDTO;
import com.github.juhachmann.estagios.data.mock.MockConfigDTO;
import com.github.juhachmann.estagios.data.mock.NotificationSettingsFactory;
import com.github.juhachmann.estagios.data.mock.SettingsFactory;

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
		
		resource = MockConfigDTO.generateResource( new NotificationSettingsFactory() );
		validate();
		assertTrue(super.violations.isEmpty());
		
		List<SettingsFactory> factories = new ArrayList<>();
		factories.add(new NotificationSettingsFactory());
		factories.add(new NotificationSettingsFactory());
		
		resource = MockConfigDTO.generateResource( factories);
		validate();
		assertTrue(super.violations.isEmpty());
		
	}

}
