package com.github.juhachmann.estagios.perfil;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.juhachmann.estagios.exceptions.InvalidBodyRequestException;
import com.github.juhachmann.estagios.vagas.VagaPrivadaController;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


/**
 * Performs basic data validation, adds HATEOAS Links and orchestrates Database and Application operations for authenticated user profile actions
 * 
 */

@Service
public class PerfilPrivadoService {
	
	@Autowired
	MockPerfilRepository repo;
	
	@Autowired
	MockConfigRepository confRepo;
	
	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	
	/**
	 * Validates the serializable resource using Jakarta Validation
	 * @param resource to be validated
	 */
	private void validate(Serializable resource) {
		Set<ConstraintViolation<Serializable>> violations = validator.validate(resource) ;
						
		if(violations.isEmpty()) {
			return;
		}

		// TODO - Remove this block once the correct error message is thrown
		violations.forEach((v) -> {
			System.out.println(v.toString());
		});

		throw new InvalidBodyRequestException(violations);
		
	}
	
	
	/**
	 * Adds HATEOAS links and affordances to a Profile resource
	 * 
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	private void addRequiredLinks(PerfilPrivadoDTO resource) throws Exception {
		resource.removeLinks().add( linkTo( methodOn (PerfilPrivadoController.class) .get(resource.getKey()) ) // SELF
						.withSelfRel()
						.withTitle("self")
						.andAffordance(afford(methodOn(PerfilPrivadoController.class).update(resource.getKey(), resource) ) ) // update
						.andAffordance( afford(methodOn(PerfilPrivadoController.class).delete(resource.getKey()) ) ) // delete
					)
					.add( linkTo( methodOn (PerfilPrivadoController.class) . getConfigs(resource.getKey()) ) // CONFIGS 
							.withRel("configs")
							.withTitle("Configs for your profile")
					)
					.add( linkTo( methodOn (VagaPrivadaController.class) .getAllFromMe(resource.getKey(), 5, 5) ) // VAGAS TODO - Arrumar estes argumentos de busca paginada aqui
							.withRel("vagas")
							.withTitle("Jobs offered by you")
					)
					.add( linkTo( methodOn (VagaPrivadaController.class) .getOwnerById(resource.getKey()) ) // PERFIL PUBLICO
							.withRel("publicProfile")
							.withTitle("Your public profile")
					);
	}
	
	
	/**
	 * Adds HATEOAS links and affordances to a Profile Configs resource
	 * 
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	private void addRequiredLinks(ConfigDTO resource) throws Exception {
		resource.removeLinks().add( linkTo( methodOn (PerfilPrivadoController.class).getConfigs(resource.getKey()) ).withSelfRel()
							.andAffordance( afford ( methodOn( PerfilPrivadoController.class).updateConfigs(resource.getKey(), resource) ) ) ) // update
					.add( linkTo( methodOn (PerfilPrivadoController.class).get(resource.getKey()) ) 
							.withRel("profile")
							.withTitle("Your profile")
					);
	}

	
	/**
	 * Creates new profile for authenticated user
	 * 
	 * @param resource PerfilPrivadoDTO
	 * @return created user profile with HATEOAS links
	 * @throws Exception
	 */
	public PerfilPrivadoDTO create(PerfilPrivadoDTO resource) throws Exception {
		resource.removeLinks();
		validate(resource);		
		PerfilPrivadoDTO created = repo.create(resource);
		addRequiredLinks(created);
		return created;
	}
	
	
	/**
	 * Returns authenticated user profile
	 * 
	 * @param id user id
	 * @return user profile with HATEOAS links
	 * @throws Exception
	 */
	public PerfilPrivadoDTO get(long id) throws Exception {
		PerfilPrivadoDTO perfil = repo.getById(id);
		addRequiredLinks(perfil);
		return perfil;
	}
	
	
	/**
	 * Updates authenticated user profile
	 * @param resource PerfilPrivadoDTO 
	 * @return updated profile with HATEOAS links
	 * @throws Exception
	 */
	public PerfilPrivadoDTO update(PerfilPrivadoDTO resource) throws Exception {
		resource.removeLinks();
		validate(resource);
		PerfilPrivadoDTO perfil =  repo.update(resource.getKey(), resource);
		addRequiredLinks(perfil);
		return perfil;
	}

	
	/**
	 * Deletes the authenticated user's profile
	 * @param id user id
	 */
	public void delete(long id) {
		repo.delete(id);
	}

	
	
	// USER CONFIGS
	
	
	/**
	 * Get authenticated user configs
	 * @param id user id
	 * @return user configs with HATEOAS links
	 * @throws Exception
	 */
	public ConfigDTO getPerfilConfig(long id) throws Exception {
		ConfigDTO config = confRepo.getById(id) ;
		addRequiredLinks(config);
		return config;
	}

	
	/**
	 * Updates authenticated user configs
	 * @param id user id
	 * @param resource ConfigDTO
	 * @return updated user configs with HATEOAS links
	 * @throws Exception
	 */
	public ConfigDTO updateConfig(long id, ConfigDTO resource) throws Exception {
		resource.removeLinks();
		validate(resource);
		ConfigDTO configs = confRepo.update(id, resource);
		addRequiredLinks(configs);
		return configs;
	}

}
