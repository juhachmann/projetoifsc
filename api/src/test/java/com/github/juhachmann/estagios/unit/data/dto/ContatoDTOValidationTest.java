package com.github.juhachmann.estagios.unit.data.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.juhachmann.estagios.data.dto.ContactDTO;
import com.github.juhachmann.estagios.data.mock.MockContactDto;


class ContatoDTOValidationTest extends ValidationTest<ContactDTO> {
	
//	enum Phone{
//		VALID("5454545");
//
//		String value;
//		Phone(String string) {
//			this.value = string;
//		}
//	}
//	
//	enum Email{
//		VALID("valid@email.com"),
//		INVALID("invalidEmail.com");
//
//		String value;
//		Email(String string) {
//			this.value = string;
//		}
//	}

	
	final String BLANK_VALUE = "    ";
	final String VALID_EMAIL = "valid@email.com";
	final String INVALID_EMAIL = "invalidEmail";
	final String VALID_PHONE = "458522";
	
	
	@BeforeEach
	void setUp() throws Exception {
		resource = new ContactDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		printViolations();
	}
	
	@Test
	void emptyConstructorGeneratesInvalidResource() {
//		violations = validator.validate(resource);	
		validate();
		assertFalse(violations.isEmpty());		
	}

	@Test
	void emailMustNotBeNull() {
		resource.setEmail(null);
//		violations = validator.validate(resource);	
		validate();

		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains("propertyPath=email"));		
	}
	
	@Test
	void emailMustBeAValidEmailAddress() {		
		resource.setEmail(INVALID_EMAIL);
//		violations = validator.validate(resource);
		validate();

		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains("propertyPath=email"));		
	}

	@Test
	void emailMustValidated() {		
		resource.setEmail(VALID_EMAIL);
//		violations = validator.validate(resource);		
		validate();

		assertFalse(violations.toString().contains("propertyPath=email"));
	}
	
	@Test
	void telefoneMustNotBeBlank() {
		resource.setTelefone(null);
//		violations = validator.validate(resource);	
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains("propertyPath=telefone"));
		
		resource.setTelefone(BLANK_VALUE);
//		violations = validator.validate(resource);	
		validate();
		assertFalse(violations.isEmpty());
		assertTrue(violations.toString().contains("propertyPath=telefone"));
	}
	
	@Test
	void telefoneMustBeValidated() {		
		resource.setTelefone(VALID_PHONE);
//		violations = validator.validate(resource);	
		validate();

		assertFalse(violations.toString().contains("propertyPath=telefone"));
	}
	
	
	@Test
	void validObjectMustBeValidated() {		
		resource.setEmail(VALID_EMAIL);
		resource.setTelefone(VALID_PHONE);
		validate();
		assertTrue(violations.isEmpty());
	}
	
	@Test
	void mockGeneratesValidResource() {		
		resource = MockContactDto.generateResource();	
		validate();
		assertTrue(violations.isEmpty());
	}


}
