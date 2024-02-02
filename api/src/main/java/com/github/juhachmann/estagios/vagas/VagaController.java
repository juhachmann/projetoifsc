package com.github.juhachmann.estagios.vagas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.juhachmann.estagios.perfil.ConfigDTO;
import com.github.juhachmann.estagios.perfil.PerfilPublicoDTO;

@RestController
@RequestMapping("/vagas")
public class VagaController {

	@GetMapping("")
	public ResponseEntity<VagasDTO> get(long id) {
		return null;
	}

	@GetMapping("/owners/{id}")
	public ResponseEntity<PerfilPublicoDTO> getOwnerById(
			@PathVariable("id") long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
