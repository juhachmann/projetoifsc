package com.github.projetoifsc.pi.apirest.utils.mock;
//import static com.github.juhachmann.estagios.data.mock.MockDatabasePerfil.enderecoDTOMockCollection;

import com.github.projetoifsc.pi.apirest.dto.shared.Localizacao;
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
