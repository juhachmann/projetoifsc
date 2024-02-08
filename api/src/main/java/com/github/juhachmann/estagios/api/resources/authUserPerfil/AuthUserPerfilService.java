package com.github.juhachmann.estagios.api.resources.authUserPerfil;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.juhachmann.estagios.api.exceptions.InvalidBodyRequestException;
import com.github.juhachmann.estagios.api.resources.authUserConfig.AuthUserConfigDTO;
import com.github.juhachmann.estagios.api.resources.authUserVaga.AuthUserVagaController;
import com.github.juhachmann.estagios.trash.MockConfigRepository;
import com.github.juhachmann.estagios.trash.MockPerfilRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


/**
 * Performs basic data validation, adds HATEOAS Links and orchestrates Database and Application operations for authenticated user profile actions
 * 
 */

@Service
public class AuthUserPerfilService {
	
	@Autowired
	MockPerfilRepository repo;
	
	@Autowired
	MockConfigRepository confRepo;
	
	
	
	/**
	 * Adds HATEOAS links and affordances to a Profile resource
	 * 
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	private void addRequiredLinks(AuthUserPerfilDTO resource) throws Exception {
//		resource.removeLinks().add( linkTo( methodOn (AuthUserPerfilController.class) .get(resource.getKey()) ) // SELF
//						.withSelfRel()
//						.withTitle("self")
//						.andAffordance(afford(methodOn(AuthUserPerfilController.class).update(resource.getKey(), resource) ) ) // update
//						.andAffordance( afford(methodOn(AuthUserPerfilController.class).delete(resource.getKey()) ) ) // delete
//					)
//					.add( linkTo( methodOn (AuthUserPerfilController.class) . getConfigs(resource.getKey()) ) // CONFIGS 
//							.withRel("configs")
//							.withTitle("Configs for your profile")
//					)
//					.add( linkTo( methodOn (AuthUserVagaController.class) .getAllFromMe(resource.getKey(), 5, 5) ) // VAGAS TODO - Arrumar estes argumentos de busca paginada aqui
//							.withRel("vagas")
//							.withTitle("Jobs offered by you")
//					)
//					.add( linkTo( methodOn (AuthUserPerfilController.class) .getUserById(resource.getKey(), resource.getKey() ) ) // PERFIL PUBLICO
//							.withRel("publicProfile")
//							.withTitle("Your public profile")
//					);
	}
	
	
	/**
	 * Adds HATEOAS links and affordances to a Profile Configs resource
	 * 
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	private void addRequiredLinks(AuthUserConfigDTO resource) throws Exception {
//		resource.removeLinks().add( linkTo( methodOn (AuthUserPerfilController.class).getConfigs(resource.getKey()) ).withSelfRel()
//							.andAffordance( afford ( methodOn( AuthUserPerfilController.class).updateConfigs(resource.getKey(), resource) ) ) ) // update
//					.add( linkTo( methodOn (AuthUserPerfilController.class).get(resource.getKey()) ) 
//							.withRel("profile")
//							.withTitle("Your profile")
//					);
	}

	
	/**
	 * Creates new profile for authenticated user
	 * 
	 * @param resource AuthUserPerfilDTO
	 * @return created user profile with HATEOAS links
	 * @throws Exception
	 */
	public AuthUserPerfilDTO create(AuthUserPerfilDTO resource) throws Exception {
		resource.removeLinks();
//		validate(resource);		
		AuthUserPerfilDTO created = repo.create(resource);
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
	public AuthUserPerfilDTO get(long id) throws Exception {
		AuthUserPerfilDTO perfil = repo.getById(id);
		addRequiredLinks(perfil);
		return perfil;
	}
	
	
	/**
	 * Updates authenticated user profile
	 * @param auth 
	 * @param resource AuthUserPerfilDTO 
	 * @return updated profile with HATEOAS links
	 * @throws Exception
	 */
	public AuthUserPerfilDTO update(Long auth, AuthUserPerfilDTO resource) throws Exception {
		resource.removeLinks();
//		validate(resource);
		AuthUserPerfilDTO perfil =  repo.update(resource.getKey(), resource);
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
	public AuthUserConfigDTO getPerfilConfig(long id) throws Exception {
		AuthUserConfigDTO config = confRepo.getById(id) ;
		addRequiredLinks(config);
		return config;
	}

	
	/**
	 * Updates authenticated user configs
	 * @param id user id
	 * @param resource AuthUserConfigDTO
	 * @return updated user configs with HATEOAS links
	 * @throws Exception
	 */
	public AuthUserConfigDTO updateConfig(long id, AuthUserConfigDTO resource) throws Exception {
		resource.removeLinks();
//		validate(resource);
		AuthUserConfigDTO configs = confRepo.update(id, resource);
		addRequiredLinks(configs);
		return configs;
	}

}
