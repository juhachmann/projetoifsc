package com.github.juhachmann.estagios.vagas;

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

import com.github.juhachmann.estagios.exceptions.InvalidHeaderRequestException;
import com.github.juhachmann.estagios.exceptions.InvalidParamRequestException;
import com.github.juhachmann.estagios.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Vagas")
@RestController
@RequestMapping(value = "/vagas", produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS })
public class VagaController {
	
	
	@Autowired
	VagaService service;
	
	
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
	
	
	private final String MAIN_TAG = "Vagas";
	
	private final String GET_ONE_PUBLIC_SUMMARY = "Vagas para mim";
	private final String GET_ONE_PUBLIC_DESCRIPTION = "Ver todas as vagas disponibilizadas para a instituição de ensino";
	private final String GET_ONE_PUBLIC_ID = "getAllForMe";
	
	private final String GET_FOR_ME_SUMMARY = "Vagas para mim";
	private final String GET_FOR_ME_DESCRIPTION = "Ver todas as vagas disponibilizadas para a instituição de ensino";
	private final String GET_FOR_ME_ID = "getAllForMe";
	
	private final String GET_ALL_FROM_SUMMARY = "Vagas criadas por determinada organização";
	private final String GET_ALL_FROM_DESCRIPTION = "Ver todas as vagas criadas por determinada instituição ou empresa e disponbilizadas para sua instituição de ensino";
	private final String GET_ALL_FROM_ID = "getAllFrom";
	
	
	
	private void validateMaxLimitPageableValue (Integer givenValue) {
		Integer maxValue = Integer.valueOf(MAX_PAGE_VALUE) ;
		if (givenValue > maxValue ) {
			throw new InvalidParamRequestException(givenValue, maxValue);
		}
	}
	
	
	private void validateUserId (Long userId) { // TODO - essa é a validação do parâmetro para autenticação
		if ( userId == null || userId < 1 )
			throw new InvalidHeaderRequestException("");
	}
	

	private void validateVagaId (Long vagaId) { // TODO - essa é a validação do parâmetro para autenticação
		if ( vagaId < 1 )
			throw new InvalidParamRequestException("");
	}


	private void validateLimitAndOffset (Integer limit, Integer offset) { // TODO - essa é a validação do parâmetro para autenticação
		if ( limit < 1 || offset < 1)
			throw new InvalidParamRequestException("");
		validateMaxLimitPageableValue(limit);
	}

	
	
	
	@GetMapping("/{id}/public")
	@Operation(summary=GET_ONE_PUBLIC_SUMMARY, description=GET_ONE_PUBLIC_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_ONE_PUBLIC_ID)
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
	public ResponseEntity<VagaDTO> getOnePublic ( 
			@RequestHeader Long userId, 
			@PathVariable("id") Long vagaId
	) {
		validateVagaId(vagaId);
		validateUserId(userId);

		return new ResponseEntity<VagaDTO> (
				service.getOnePublic ( userId, vagaId ),
				HttpStatus.OK );
	}
	
	
	@GetMapping("/for/me")
	@Operation(summary=GET_FOR_ME_SUMMARY, description=GET_FOR_ME_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_FOR_ME_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = VagaDTO.class), uniqueItems = true, minItems = 0, maxItems = MAX_PAGE_VALUE))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
		),
		// TODO - Decidir se uma empresa acessa esse endpoint ou se vira um 404 pra ela
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<VagaDTO>> getAllForMe ( 
			@RequestHeader Long userId, 
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
	) {
		validateLimitAndOffset(limit, page);
		validateUserId(userId);
		
		Pageable pageable = PageRequest.of(page, limit);
		return new ResponseEntity<Page<VagaDTO>> (
				service.getAllForMe ( userId, pageable ),
				HttpStatus.OK );
	}
	
	
	
	@GetMapping("/from/{ownerId}")
	@Operation(summary=GET_ALL_FROM_SUMMARY, description=GET_ALL_FROM_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_ALL_FROM_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = VagaDTO.class), uniqueItems = true, minItems = 0, maxItems = MAX_PAGE_VALUE))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<VagaDTO>> getAllFrom ( 
			@RequestHeader Long userId, 
			@PathVariable("ownerId") Long ownerId,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
	) {	
		validateLimitAndOffset(limit, page);
		validateUserId(userId);
		
		Pageable pageable = PageRequest.of(page, limit);
		return new ResponseEntity<Page<VagaDTO>> (
				service.getAllFrom ( userId, pageable ),
				HttpStatus.OK );
		
	}
	
	
}
