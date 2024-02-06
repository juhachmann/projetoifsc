package com.github.juhachmann.estagios.unit.data.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.juhachmann.estagios.vagas.MockVagaDTO;
import com.github.juhachmann.estagios.vagas.VagaDTO;

public class VagaDTOValidationTest extends ValidationTest<VagaDTO>{
	
	String BLANK = "   ";
	String NOT_BLANK = "texto válido";
	
	@BeforeEach
	void setup() {
		resource = new VagaDTO();
	}
	
	@AfterEach
	void tearDown() {
		
	}
	
	@Test
	void titleIsNotBlank() {
		propertyPathBeingTested = "propertyPath=title";
		
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setTitle(BLANK);
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setTitle(NOT_BLANK);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
	}
	
	@Test
	void descriptionIsNotBlank() {
		propertyPathBeingTested = "propertyPath=description";
		
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setDescription(BLANK);
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setDescription(NOT_BLANK);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}

	@Test
	void periodsIsNotEmptyOrBlank() {
		propertyPathBeingTested = "propertyPath=periods";
		
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setPeriods(null);
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		var array = new ArrayList<String>();
		array.add(BLANK);
		array.add(BLANK);
		
		resource.setPeriods(array);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		array.clear();
		array.add(NOT_BLANK);
		resource.setPeriods(array);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));

	}

	@Test
	void workloadInHoursIsNotNullOrUnder1() {
		propertyPathBeingTested = "propertyPath=workloadInHours";
		
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setWorkloadInHours(-1L);
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setWorkloadInHours(0);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		resource.setWorkloadInHours(1L);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}

	@Test
	void paymentIsNotNullOrUnder1() {
		propertyPathBeingTested = "propertyPath=payment";
		
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setPayment(-1L);
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setPayment(0);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		resource.setPayment(1L);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}

	@Test
	void levelsIsNotBlank() {
		propertyPathBeingTested = "propertyPath=levels";
		
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		resource.setLevels(null);
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		var array = new ArrayList<String>();
		array.add(BLANK);
		
		resource.setLevels(array);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		array.clear();
		array.add(NOT_BLANK);
		resource.setLevels(array);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}

	@Test
	void areasIsNotEmptyOrBlank() {
		propertyPathBeingTested = "propertyPath=areas";
		
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setAreas(null);
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		var array = new ArrayList<String>();
		array.add(BLANK);
		
		resource.setAreas(array);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		array.clear();
		array.add(NOT_BLANK);
		resource.setAreas(array);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}
	
	@Test
	void coursesIsNotBlank() {
		propertyPathBeingTested = "propertyPath=courses";
		
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		resource.setCourses(null);
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		var array = new ArrayList<String>();
		array.add(BLANK);
		
		resource.setCourses(array);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		array.clear();
		array.add(NOT_BLANK);
		resource.setCourses(array);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}
	
	
	@Test
	void requirementsIsNotBlank() {
		propertyPathBeingTested = "propertyPath=requirements";
		
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		resource.setRequirements(null);
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		var array = new ArrayList<String>();
		array.add(BLANK);
		
		resource.setRequirements(array);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		array.clear();
		array.add(NOT_BLANK);
		resource.setRequirements(array);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}


	@Test
	void expiringDaysNotNegative() {
		propertyPathBeingTested = "propertyPath=expiringInDays";
		
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		resource.setExpiringInDays(-1);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setExpiringInDays(0);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));

		resource.setExpiringInDays(1);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));

	}
	
	@Test
	void renovateInDaysNotNegative() {
		propertyPathBeingTested = "propertyPath=renovateInDays";
		
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		resource.setRenovateInDays (-1);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setRenovateInDays(0);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));

		resource.setRenovateInDays(1);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));

	}
	
	@Test
	void externalLinksIsNotBlank() {
		propertyPathBeingTested = "propertyPath=externalLinks";
		
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		resource.setExternalLinks(null);
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		var array = new ArrayList<String>();
		array.add(BLANK);
		
		resource.setExternalLinks(array);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		array.clear();
		array.add(NOT_BLANK);
		resource.setExternalLinks(array);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}
	
	@Test
	void iesIsNotBlank() {
		propertyPathBeingTested = "propertyPath=ies";
		
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		resource.setIes(null);
		validate();
		assertFalse(violations.isEmpty());
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
		var array = new ArrayList<Long>();
		array.add(null);
		
		resource.setIes(array);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		array.clear();
		array.add(1L);
		resource.setIes(array);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}

	@Test
	void mockGeneratesValidVaga() throws JsonProcessingException {
		resource = MockVagaDTO.generateResource();
		validate();
		assertTrue(violations.isEmpty());
	}
	
	@Test
	void mockGeneratesInvalidData() {
		resource = MockVagaDTO.generateInvalidResource();
		validate();
		assertFalse(violations.isEmpty());
	}

	@Test
	void mockGeneratesValidBundle() {
		List<VagaDTO> bundle = MockVagaDTO.generateBundle(10);
		bundle.forEach((vaga) -> {
			resource = vaga;
			validate();
			assertTrue(violations.isEmpty());
		});
	}
	
}
