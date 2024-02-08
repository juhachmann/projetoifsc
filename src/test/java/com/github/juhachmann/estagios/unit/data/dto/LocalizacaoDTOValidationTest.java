package com.github.juhachmann.estagios.unit.data.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.juhachmann.estagios.api.resources.shared.LocalizacaoDTO;
import com.github.juhachmann.estagios.api.resources.shared.MockLocalizacaoDto;

class LocalizacaoDTOValidationTest extends ValidationTest<LocalizacaoDTO>{

	String VALID_CITY = "Qualquer Cidade";
	String VALID_STATE = "Qualquer estado";
	String VALID_COUNTRY = "Qualquer País";
	String BLANK = "   ";
	
	@BeforeEach
	void setUp() throws Exception {
		super.resource = new LocalizacaoDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		super.printViolations();
	}

	@Test
	void cityMustNotBeBlank() {
		super.validate();
		assertFalse(super.violations.isEmpty());
		assertTrue(super.violations.toString().contains("propertyPath=city"));
		
		resource.setCity(BLANK);
		super.validate();
		assertFalse(super.violations.isEmpty());
		assertTrue(super.violations.toString().contains("propertyPath=city"));
		
		resource.setCity(VALID_CITY);
		super.validate();
		assertFalse(super.violations.toString().contains("propertyPath=city"));
	}
	
	@Test
	void stateMustNotBeBlank() {
		super.validate();
		assertFalse(super.violations.isEmpty());
		assertTrue(super.violations.toString().contains("propertyPath=state"));
		
		resource.setState(BLANK);
		super.validate();
		assertFalse(super.violations.isEmpty());
		assertTrue(super.violations.toString().contains("propertyPath=state"));
		
		resource.setState(VALID_STATE);
		super.validate();
		assertFalse(super.violations.toString().contains("propertyPath=state"));
	}
	
	@Test
	void countryMustNotBeBlank() {
		super.validate();
		assertFalse(super.violations.isEmpty());
		assertTrue(super.violations.toString().contains("propertyPath=country"));
		
		resource.setCountry(BLANK);
		super.validate();
		assertFalse(super.violations.isEmpty());
		assertTrue(super.violations.toString().contains("propertyPath=country"));
		
		resource.setCountry(VALID_COUNTRY);
		super.validate();
		assertFalse(super.violations.toString().contains("propertyPath=country"));	}
	
	@Test
	void resourceMustBeValidated() {
		resource.setCity(VALID_CITY);
		resource.setCountry(VALID_COUNTRY);
		resource.setState(VALID_STATE);
		super.validate();
		
		assertTrue(super.violations.isEmpty());
	}
	
	@Test
	void mockGeneratesValidResource() {
		resource = MockLocalizacaoDto.generateResource();
		
		super.validate();		
		assertTrue(super.violations.isEmpty());
	}

}
