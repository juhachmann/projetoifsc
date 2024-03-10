package com.github.juhachmann.estagios.apirest.service;
//import static com.github.juhachmann.estagios.data.mock.MockDatabasePerfil.enderecoDTOMockCollection;

import java.util.ArrayList;
import java.util.List;

import com.github.juhachmann.estagios.apirest.dto.shared.Localizacao;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoMock {

	public static Localizacao getOne() {
		Localizacao resource = new Localizacao();
		resource.setLine("Rua xxxx");
		resource.setCity("Florianópolis");
		resource.setState("SC");
		resource.setCountry("Brasil");
		return resource;
	}
	
}
