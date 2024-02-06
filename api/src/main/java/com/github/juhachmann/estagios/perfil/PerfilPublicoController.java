package com.github.juhachmann.estagios.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.juhachmann.estagios.utils.MediaTypes;
import com.github.juhachmann.estagios.vagas.VagaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@EnableHypermediaSupport(type = HypermediaType.HAL_FORMS)
@RestController
@RequestMapping( 
		value = "/users",
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )


public class PerfilPublicoController {

	
	// Swagger Messages
	// TODO - Achar uma casa pra isso aqui
	// Estas são as descrições para os erros 
	// Isso aqui é pra estar no sistema inteiro:
	private final String BAD_REQUEST = "Invalid data was sent in the request.";
	private final String UNAUTHORIZED = "Authentication failed. You must be correctly authenticated to access this resource.";
	private final String TOO_MANY_REQUESTS = "Exceeded the rate limit for requests.";
	private final String FORBIDDEN = "Authorization failed. Looks like you don't have access to this resource.";
	private final String NOT_FOUND = "Resource not found or not available for you.";
	
	private final String BAD_REQUEST_MSG = "{\"code\":400,\"message\": \"" + BAD_REQUEST + "\"}";
	private final String UNAUTHORIZED_MSG = "{\"code\":401,\"message\": \"" + UNAUTHORIZED + "\"}";
	private final String TOO_MANY_REQUESTS_MSG = "{\"code\":429,\"message\": \"" + TOO_MANY_REQUESTS + "\"}";
	private final String FORBIDDEN_MSG = "{\"code\":403,\"message\": \"" + FORBIDDEN + "\"}";
	private final String NOT_FOUND_MSG = "{\"code\":404,\"message\": \"" + NOT_FOUND + "\"}";
	
	private final String DEFAULT_PAGE_VALUE = "1";
	private final String DEFAULT_LIMIT_VALUE = "10";
	private final int MAX_PAGE_VALUE = 30;
	
	
	private final String MAIN_TAG = "Users";
	
	private final String GET_USERS_SUMMARY = "";
	private final String GET_USERS_DESCRIPTION = "";
	private final String GET_USERS_ID = "";
	
	private final String GET_ONE_USER_SUMMARY = "";
	private final String GET_ONE_USER_DESCRIPTION = "";
	private final String GET_ONE_USER_ID = "";

	private final String GET_IES_SUMMARY = "";
	private final String GET_IES_DESCRIPTION = "";
	private final String GET_IES_ID = "";

	
	
	@Autowired
	PerfilPublicoService service;
	
	
	
	@GetMapping("")
	@Operation(summary=GET_USERS_SUMMARY, description=GET_USERS_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_USERS_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VagaDTO.class) ) } 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<PerfilDTO>> getUsers ( 
			@RequestHeader Long authId,
			@RequestParam int offset,
			@RequestParam int limit
	) {
		Pageable pageable = PageRequest.of(offset, limit);
		return new ResponseEntity<Page<PerfilDTO>>( 
				service.getUsers ( authId, pageable ), 
				HttpStatus.OK );
	}
	
	
	@GetMapping("/{id}")
	@Operation(summary=GET_ONE_USER_SUMMARY, description=GET_ONE_USER_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_ONE_USER_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VagaDTO.class) ) } 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<PerfilDTO> getUserById (
			@RequestHeader Long authId,
			@PathVariable Long userId
	) {
		return new ResponseEntity<PerfilDTO>( 
				service.getUserById ( authId, userId ), 
				HttpStatus.OK );
	}
	
	
	@GetMapping("/ies")
	@Operation(summary=GET_IES_SUMMARY, description=GET_IES_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_IES_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VagaDTO.class) ) } 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<PerfilDTO>> getIEs (
			@RequestHeader Long authId,
			@RequestParam int offset,
			@RequestParam int limit
	) {
		Pageable pageable = PageRequest.of(offset, limit);
		return new ResponseEntity<Page<PerfilDTO>>( 
				service.getIEs ( authId, pageable ), 
				HttpStatus.OK );

	}
	
	
	
}
