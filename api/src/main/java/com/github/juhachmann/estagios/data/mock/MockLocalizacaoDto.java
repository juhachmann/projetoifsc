package com.github.juhachmann.estagios.data.mock;
//import static com.github.juhachmann.estagios.data.mock.MockDatabasePerfil.enderecoDTOMockCollection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.juhachmann.estagios.data.dto.LocalizacaoDTO;

@Service
public class MockLocalizacaoDto {

	public static LocalizacaoDTO generateResource() {
		LocalizacaoDTO resource = new LocalizacaoDTO();
		resource.setAddressLine("Rua xxxx");
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
