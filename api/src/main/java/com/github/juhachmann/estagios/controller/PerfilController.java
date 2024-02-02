package com.github.juhachmann.estagios.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.juhachmann.estagios.data.dto.ConfigDTO;
import com.github.juhachmann.estagios.data.dto.PerfilDTO;
import com.github.juhachmann.estagios.repository.MockPerfilRepository;
import com.github.juhachmann.estagios.services.PerfilService;
import com.github.juhachmann.estagios.utils.MediaTypes;


@EnableHypermediaSupport(type = HypermediaType.HAL_FORMS)
@RestController
@RequestMapping(
		value = "/perfil", 
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )


public class PerfilController {
		
	@Autowired
//	MockPerfilRepository service;
	PerfilService service;
	
	
	@GetMapping("")
	public ResponseEntity<PerfilDTO> get(
				@RequestHeader(HttpHeaders.AUTHORIZATION) long id 
			) throws Exception {
		
		return new ResponseEntity<PerfilDTO> (
				service.get(id), 
				HttpStatus.OK);
	}
	
	
	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	public ResponseEntity<PerfilDTO> create(
			@RequestBody PerfilDTO dto) throws Exception {
		
		System.out.println("Printing from controller: " + dto.toString());
		
		return new ResponseEntity<PerfilDTO>(
				service.create(dto),
				HttpStatus.CREATED);
		
	}
	

	@PutMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	public ResponseEntity<PerfilDTO> update(
			@RequestHeader(HttpHeaders.AUTHORIZATION) long id, 
			@RequestBody PerfilDTO newDto) throws Exception {
		
		return new ResponseEntity<PerfilDTO>(
				service.update(newDto), 
				HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("")
	public ResponseEntity<PerfilDTO> delete(
			@RequestHeader(HttpHeaders.AUTHORIZATION) long id) throws Exception {
		service.delete(id);
		return new ResponseEntity<PerfilDTO>(HttpStatus.NO_CONTENT);
	}


	@GetMapping("/configs")
	public ResponseEntity<ConfigDTO> getConfigs(long id) {
		return null;
	}
	
	@PutMapping("/configs")
	public ResponseEntity<ConfigDTO> updateConfigs(long id, ConfigDTO resource) {
		return null;
	}
	
	

}
