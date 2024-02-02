package com.github.juhachmann.estagios.perfil;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.Affordance.*;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.juhachmann.estagios.exceptions.InvalidRequestException;
import com.github.juhachmann.estagios.vagas.VagaController;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Service
public class PerfilService {
	
	@Autowired
	MockPerfilRepository repo;
	
	@Autowired
	MockConfigRepository confRepo;
	
	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	
	
	private void validate(Serializable resource) {
		Set<ConstraintViolation<Serializable>> violations = validator.validate(resource) ;
		if(violations.isEmpty()) {
			return;
		}
		throw new InvalidRequestException(violations);
	}
	
	private PerfilDTO addRequiredLinks(PerfilDTO resource) throws Exception {
		return resource.add( linkTo( methodOn (PerfilController.class) .get(resource.getKey()) )
							.withSelfRel()
							.withTitle("self")
							.andAffordance( afford(methodOn(PerfilController.class).delete(resource.getKey()) ) )
							.andAffordance(afford(methodOn(PerfilController.class).update(resource.getKey(), resource) ) )
						)
						.add( linkTo( methodOn (PerfilController.class) . getConfigs(resource.getKey()) ) 
								.withRel("configs")
								.withTitle("Configs for your profile")
						)
						.add( linkTo( methodOn (VagaController.class) .get(resource.getKey()) )
								.withRel("vagas")
								.withTitle("Jobs offered by you")
						)
						.add( linkTo( methodOn (VagaController.class) .getOwnerById(resource.getKey()) )
								.withRel("perfil_publico")
								.withTitle("Your public profile")
						);
	}
	
	private ConfigDTO addRequiredLinks(ConfigDTO resource) throws Exception {
		return resource.add( linkTo( methodOn (PerfilController.class).getConfigs(resource.getKey()) ).withSelfRel()
								.andAffordance( afford ( methodOn( PerfilController.class).updateConfigs(resource.getKey(), resource) ) ) ) 
						.add( linkTo( methodOn (PerfilController.class).get(resource.getKey()) ) 
								.withRel("perfil")
								.withTitle("Seu perfil")
						);
	}

	
	
	public PerfilDTO create(PerfilDTO resource) throws Exception {
		System.out.println("Printing from service " + resource.toString());
		validate(resource);		
		PerfilDTO created = repo.create(resource.removeLinks());
		created = addRequiredLinks(created);
		return created;
	}
	
	public PerfilDTO get(long id) throws Exception {
		PerfilDTO perfil = repo.getById(id);
		perfil = addRequiredLinks(perfil);
		return perfil;
	}
	
	public PerfilDTO update(PerfilDTO resource) throws Exception {
		validate(resource);
		PerfilDTO perfil =  repo.update(resource.getKey(), resource.removeLinks());
		perfil = addRequiredLinks(perfil);
		return perfil;
	}

	public void delete(long id) {
		repo.delete(id);
	}

	
	// Configs
	
	public ConfigDTO getPerfilConfig(long id) throws Exception {
		ConfigDTO config = confRepo.getById(id) ;
		config = addRequiredLinks(config);
		return config;
	}

	public ConfigDTO updateConfig(long id, ConfigDTO resource) throws Exception {
		validate(resource);
		ConfigDTO configs = confRepo.update(id, resource.removeLinks());
		configs = addRequiredLinks(configs);
		return configs;
	}

}
