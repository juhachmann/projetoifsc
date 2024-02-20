package com.github.juhachmann.estagios.apirest.resources.authUserPerfil;

import static com.github.juhachmann.estagios.apirest.utils.HttpErrorMessages.BAD_REQUEST_MSG;
import static com.github.juhachmann.estagios.apirest.utils.HttpErrorMessages.TOO_MANY_REQUESTS_MSG;
import static com.github.juhachmann.estagios.apirest.utils.HttpErrorMessages.UNAUTHORIZED_MSG;
import static com.github.juhachmann.estagios.apirest.utils.SwaggerTags.BASE_URL;
import static com.github.juhachmann.estagios.apirest.utils.SwaggerTags.EMPRESA;
import static com.github.juhachmann.estagios.apirest.utils.SwaggerTags.IES;
import static com.github.juhachmann.estagios.apirest.utils.SwaggerTags.PERFIL;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.github.juhachmann.estagios.apirest.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


//@EnableHypermediaSupport(type = HypermediaType.HAL_FORMS)
@RestController
@RequestMapping(
		value = BASE_URL + "/me", 
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

/**
 * REST Controller for "/perfil" related endpoints
 */
public class AuthUserPerfilController {
	
	public final String GET_ID = "getAuthUserPerfil";
	private final String GET_SUMMARY = "Ver perfil";
	private final String GET_DESCRIPTION = "Exibe as informações da instituição ou empresa";

	public final String POST_ID = "createAuthUserPerfil";
	private final String POST_SUMMARY = "Criar perfil";
	private final String POST_DESCRIPTION = "Cria o perfil da instituição ou empresa";
	
	public final String PUT_ID = "updateAuthUserPerfil";
	private final String PUT_SUMMARY = "Editar perfil";
	private final String PUT_DESCRIPTION = "Atualiza o perfil da instituição ou empresa";

	public final String DELETE_ID = "deleteAuthUserPerfil";
	private final String DELETE_SUMMARY = "Deletar perfil";
	private final String DELETE_DESCRIPTION = "Deleta o perfil da instituição ou empresa e todos os seus dados (anúncios de vagas, etc). Para utilizar o sistema, é necessário possuir um perfil ativo";

		
	@Autowired
	AuthUserPerfilService service;
	
	@GetMapping("")
	@Operation(summary=GET_SUMMARY, description=GET_DESCRIPTION, tags={PERFIL, IES, EMPRESA}, operationId=GET_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthUserPerfilDTO.class))}, 
	    		links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<AuthUserPerfilDTO> get (
				@RequestHeader Long auth 
	) throws Exception {
		
		return new ResponseEntity<AuthUserPerfilDTO> (
				service.get(auth), 
				HttpStatus.OK);	
	}
	
	
	
	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary=POST_SUMMARY, description=POST_DESCRIPTION, tags={PERFIL, IES, EMPRESA}, operationId=POST_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = AuthUserPerfilDTO.class))}, 
	    		links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<AuthUserPerfilDTO> create ( 
			@RequestBody AuthUserPerfilDTO perfil 
	) throws Exception {
				
		return new ResponseEntity<AuthUserPerfilDTO>(
				service.create(perfil),
				HttpStatus.CREATED);		
	}
	

	
	@PutMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary=PUT_SUMMARY, description=PUT_DESCRIPTION, tags={PERFIL, IES, EMPRESA}, operationId=PUT_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthUserPerfilDTO.class))}, 
	    		links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<AuthUserPerfilDTO> update (
			@RequestHeader Long auth,
			@RequestBody AuthUserPerfilDTO perfil
	) throws Exception {
		
		return new ResponseEntity<AuthUserPerfilDTO>(
				service.update(auth, perfil), 
				HttpStatus.OK);		
	}
	
	
	
	@DeleteMapping("")
	@Operation(summary=DELETE_SUMMARY, description=DELETE_DESCRIPTION, tags={PERFIL, IES, EMPRESA}, operationId=DELETE_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "204"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	  })
	public ResponseEntity<AuthUserPerfilDTO> delete (
			@RequestHeader Long auth
	) throws Exception {
		service.delete(auth);
		return new ResponseEntity<AuthUserPerfilDTO>(HttpStatus.NO_CONTENT);
	}

	

}
