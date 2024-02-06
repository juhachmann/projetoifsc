package com.github.juhachmann.estagios.unit.data.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.juhachmann.estagios.commom.ContactDTO;
import com.github.juhachmann.estagios.commom.LocalizacaoDTO;
import com.github.juhachmann.estagios.commom.MockContactDto;
import com.github.juhachmann.estagios.commom.MockLocalizacaoDto;
import com.github.juhachmann.estagios.perfil.MockPerfilDto;
import com.github.juhachmann.estagios.perfil.PerfilPrivadoDTO;

public class PerfilDTOValidationTest extends ValidationTest<PerfilPrivadoDTO> {

	String propertyPathBeingTested;
	
	String BLANK = "   ";

	String VALID_NAME = "khkjhad ksajdh sadkjhasd";
	
	String INVALID_CNPJ = "152525252";
	String VALID_CNPJ = "34223048000180";

	String[] VALID_CATEGORIES = { "ie", "empresa" };
	String INVALID_CATEGORY = "outro";

	List<String> validAreas;
	List<String> invalidAreas;

	
	@BeforeEach
	void setUp() throws Exception {
		resource = new PerfilPrivadoDTO();
	}

	
	@AfterEach
	void tearDown() throws Exception {
		super.printViolations();
	}
	
	
	@Test
	void nameMustBeValid() {
		propertyPathBeingTested = "propertyPath=name";
		
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setName(BLANK);
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setName(VALID_NAME);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));

	}
	
	@Test
	void cnpjMustBeValid() {
		propertyPathBeingTested = "propertyPath=cnpj";
		
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setCnpj(BLANK);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		resource.setCnpj(INVALID_CNPJ);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		resource.setCnpj(VALID_CNPJ);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}
	
	
	@Test
	void ieMustNotBeInitializedAndBoolean() {
		propertyPathBeingTested = "propertyPath=ie";

		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}

	
	@Test
	void areasMustBeValid() {
		
		propertyPathBeingTested = "propertyPath=areas";
		
		validAreas = new ArrayList<>();
		invalidAreas = new ArrayList<>();

		validAreas.add("Qualquer área");
		validAreas.add("Outra area");
		invalidAreas.add(BLANK);
		invalidAreas.add(BLANK);
		
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setAreas(null);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
	
		resource.setAreas(invalidAreas);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		resource.setAreas(validAreas);
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
		
	}
	
	
	@Test
	void addressMustBeValid() {
		propertyPathBeingTested = "propertyPath=address";
		
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setAddress(null);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		resource.setAddress( new LocalizacaoDTO());
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setAddress( MockLocalizacaoDto.generateResource() );
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}
	
	
	@Test
	void genContactMustBeValid() {
		propertyPathBeingTested = "propertyPath=generalContact";
		
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setGeneralContact(null);
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));

		resource.setGeneralContact( new ContactDTO() );
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
		
		resource.setGeneralContact(  MockContactDto.generateResource() );
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}

	
	@Test
	void applicationContactMustBeValid() {
		propertyPathBeingTested = "propertyPath=applicationContact";
		
		resource.setApplicationContact( new ContactDTO() );
		validate();
		assertTrue(violations.toString().contains(propertyPathBeingTested));
				
		resource.setApplicationContact(  MockContactDto.generateResource() );
		validate();
		assertFalse(violations.toString().contains(propertyPathBeingTested));
	}

	
	
	@Test
	void mockGeneratesValidObject() {
		resource = MockPerfilDto.generateResource();
		validate();
		assertTrue(violations.isEmpty());
	}
	
	
	@Test 
	void mockGeneratesInvalidObjectWithGivenNumOfFields() {
		for (int i = 0; i < 10; i++) {
			resource = MockPerfilDto.generateInvalidResource(i);
			validate();
			assertFalse(violations.isEmpty());
		}
	}
	
	
	@Test 
	void mockGeneratesInvalidObjectWithRandomNumOfFields() {
		for (int i = 0; i < 20; i++) {
			resource = MockPerfilDto.generateInvalidResource();
			validate();
			assertFalse(violations.isEmpty());
		}
	}

	

	
}
