package com.github.juhachmann.estagios.apirest.service;

import com.github.juhachmann.estagios.apirest.dto.shared.Contato;

public class ContatoMock {
	
	public static Contato getOne() {
		Contato resource = new Contato();
		resource.setEmail ("nome@email.com");
		resource.setPhone("(XX) XXXX-XXXX");
		return resource;
	}


}
