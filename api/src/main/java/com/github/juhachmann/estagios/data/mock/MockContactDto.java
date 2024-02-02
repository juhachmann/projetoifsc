package com.github.juhachmann.estagios.data.mock;

import java.util.ArrayList;
import java.util.List;

import com.github.juhachmann.estagios.data.dto.ContactDTO;

public class MockContactDto {
	
	public static ContactDTO generateResource() {
		ContactDTO resource = new ContactDTO();
		resource.setEmail ("nome@email.com");
		resource.setTelefone("(XX) XXXX-XXXX");
		return resource;
	}
	
	
	public static List<ContactDTO> generateBundle(int numberOfItems) {
		List<ContactDTO> bundle = new ArrayList<ContactDTO>();
		Long nextId = 10L;
		for(int i = 0; i < numberOfItems; i++) {
			bundle.add(generateResource());
			nextId++;
		}
		return bundle;
	}
	

}
