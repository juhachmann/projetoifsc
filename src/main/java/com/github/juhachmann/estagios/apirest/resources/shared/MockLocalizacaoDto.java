package com.github.juhachmann.estagios.apirest.resources.shared;
//import static com.github.juhachmann.estagios.data.mock.MockDatabasePerfil.enderecoDTOMockCollection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MockLocalizacaoDto {

	public static LocalizacaoDTO generateResource() {
		LocalizacaoDTO resource = new LocalizacaoDTO();
		resource.setLine("Rua xxxx");
		resource.setCity("Florianópolis");
		resource.setState("SC");
		resource.setCountry("Brasil");
		return resource;
	}
	
	
	public static List<LocalizacaoDTO> generateBundle(int numberOfItems) {
		List<LocalizacaoDTO> bundle = new ArrayList<LocalizacaoDTO>();
		Long nextId = 10L;
		for(int i = 0; i < numberOfItems; i++) {
			bundle.add(generateResource());
			nextId++;
		}
		return bundle;
	}
	
}
