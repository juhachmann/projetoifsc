package com.github.projetoifsc.pi.apirest.utils.mock;

import com.github.projetoifsc.pi.apirest.dto.shared.Contato;

public class ContatoMock {
	
	public static Contato getOne() {
		Contato resource = new Contato();
		resource.setEmail ("nome@email.com");
		resource.setPhone("(XX) XXXX-XXXX");
		return resource;
	}


}
