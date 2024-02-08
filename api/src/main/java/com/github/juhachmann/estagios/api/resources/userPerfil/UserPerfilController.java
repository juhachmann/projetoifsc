package com.github.juhachmann.estagios.api.resources.userPerfil;

import static com.github.juhachmann.estagios.api.utils.HttpErrorMessages.BAD_REQUEST_MSG;
import static com.github.juhachmann.estagios.api.utils.HttpErrorMessages.NOT_FOUND_MSG;
import static com.github.juhachmann.estagios.api.utils.HttpErrorMessages.TOO_MANY_REQUESTS_MSG;
import static com.github.juhachmann.estagios.api.utils.HttpErrorMessages.UNAUTHORIZED_MSG;
import static com.github.juhachmann.estagios.api.utils.PaginationValidation.DEFAULT_LIMIT_VALUE;
import static com.github.juhachmann.estagios.api.utils.PaginationValidation.DEFAULT_PAGE_VALUE;
import static com.github.juhachmann.estagios.api.utils.SwaggerTags.BASE_URL;
import static com.github.juhachmann.estagios.api.utils.SwaggerTags.EMPRESA;
import static com.github.juhachmann.estagios.api.utils.SwaggerTags.IES;
import static com.github.juhachmann.estagios.api.utils.SwaggerTags.USERS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.juhachmann.estagios.api.resources.vagas.VagaDTO;
import com.github.juhachmann.estagios.api.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//@EnableHypermediaSupport(type = HypermediaType.HAL_FORMS)
@RestController
@RequestMapping( 
		value = BASE_URL + "/users",
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )


public class UserPerfilController {

	
	@Autowired
	UserPerfilService service;

//	private final String GET_USERS_SUMMARY = "Outros usuários";
//	private final String GET_USERS_DESCRIPTION = "Vê os perfis públicos de outras instituições e empresas cadastrados no sistema";
//	private final String GET_USERS_ID = "getAllUsers";
	
	private final String GET_ONE_USER_SUMMARY = "Perfil Público";
	private final String GET_ONE_USER_DESCRIPTION = "Vê o perfil público de determinada instituição ou empresa. Um perfil de uma empresa só é visível se ela está com uma oferta de estágio aberta para a sua instituição de ensino";
	private final String GET_ONE_USER_ID = "getUserById";

	private final String GET_IES_SUMMARY = "Instituições de Ensino";
	private final String GET_IES_DESCRIPTION = "Vê a lista de instituições de ensino para as quais é possível ofertar vagas de estágio";
	private final String GET_IES_ID = "getAll_IEs";

	

//	@GetMapping("")
//	@Operation(summary=GET_USERS_SUMMARY, description=GET_USERS_DESCRIPTION, tags= { USERS, IES, EMPRESA }, operationId=GET_USERS_ID)
//	@ApiResponses({
//		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserPerfilDTO.class), uniqueItems = true, minItems = 0))} 
//	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
//				),
//		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
//		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
//	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
//	})
//	public ResponseEntity<Page<UserPerfilDTO>> getUsers ( 
//			@RequestHeader Long auth,
//			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
//			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
//	) {
//		Pageable pageable = PageRequest.of(page, limit);
//		return new ResponseEntity<Page<UserPerfilDTO>>( 
//				service.getUsers ( auth, pageable ), 
//				HttpStatus.OK );
//	}
//	
	
	@GetMapping("/{id}")
	@Operation(summary=GET_ONE_USER_SUMMARY, description=GET_ONE_USER_DESCRIPTION, tags= { USERS, IES, EMPRESA }, operationId=GET_ONE_USER_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VagaDTO.class) ) } 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPerfilDTO> getUserById (
			@RequestHeader Long auth,
			@PathVariable Long userId
	) {
		return new ResponseEntity<UserPerfilDTO>( 
				service.getUserById ( auth, userId ), 
				HttpStatus.OK );
	}
	
	
	@GetMapping("/ies")
	@Operation(summary=GET_IES_SUMMARY, description=GET_IES_DESCRIPTION, tags= { USERS, IES, EMPRESA }, operationId=GET_IES_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserPerfilDTO.class), uniqueItems = true, minItems = 0))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<UserPerfilDTO>> getIEs (
			@RequestHeader Long auth,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
	) {
		Pageable pageable = PageRequest.of(page, limit);
		return new ResponseEntity<Page<UserPerfilDTO>>( 
				service.getIEs ( auth, pageable ), 
				HttpStatus.OK );

	}
	
	
	
}
