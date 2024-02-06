package com.github.juhachmann.estagios.vagas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.juhachmann.estagios.exceptions.InvalidHeaderRequestException;
import com.github.juhachmann.estagios.exceptions.InvalidParamRequestException;
import com.github.juhachmann.estagios.perfil.PerfilDTO;
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
public class VagaPrivadaController {
	
	// TODO - Pensar agora nas regras de validação de pageable
	// TODO - Pensar tbm nos limites de operações disponíveis
	// TODO - Pesquisar como extrair lista de queryParams separados por vírgula
	// TODO - Refatorar esse controller pq já está com muita coisa nele
	
	@Autowired
	VagaPrivadaService service;
	
	
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

	private final String GET_ALL_SUMMARY = "Minhas Vagas";
	private final String GET_ALL_DESCRIPTION = "Ver todas as vagas criadas pela empresa ou instituição";
	private final String GET_ALL_ID = "getAllVagas";

	private final String POST_SUMMARY = "Criar Vaga";
	private final String POST_DESCRIPTION = "Criar uma nova oferta de vaga de estágio";
	private final String POST_ID = "createVaga";

	private final String GET_ONE_SUMMARY = "Ver vaga";
	private final String GET_ONE_DESCRIPTION = "Ver os detalhes de determinada vaga. Acessível apenas se a empresa ou instituição é o criador ou está no grupo de IEs destinatária da oferta de vaga";
	private final String GET_ONE_ID = "getVaga";
	
	private final String UPDATE_ONE_SUMMARY = "Editar vaga";
	private final String UPDATE_ONE_DESCRIPTION = "Editar e atualizar uma vaga criada pela empresa ou instituição";
	private final String UPDATE_ONE_ID = "updateVaga";
	
	private final String DELETE_ONE_SUMMARY = "Deletar Vaga";
	private final String DELETE_ONE_DESCRIPTION = "Deletar uma vaga criada pela empresa ou instituição";
	private final String DELETE_ONE_ID = "deleteVaga";
	
	private final String GET_ALL_FOR_SUMMARY = "Vagas disponibilizadas para determinada instituição";
	private final String GET_ALL_FOR_DESCRIPTION = "Ver todas as vagas criadas pela sua organização e disponibilizadas para determinada instituição de ensino";
	private final String GET_ALL_FOR_ID = "getAllFor";
	

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

	
	
	
	
	@GetMapping("")
	@Operation(summary=GET_ALL_SUMMARY, description=GET_ALL_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_ALL_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = VagaPrivadaDTO.class), uniqueItems = true, minItems = 0, maxItems = MAX_PAGE_VALUE))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<VagaPrivadaDTO>> getAllFromMe ( 
			@RequestHeader Long userId, 
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
	) {	
		validateLimitAndOffset(limit, page);
		validateUserId(userId);
		
		Pageable pageable = PageRequest.of(page, limit);
		return new ResponseEntity<Page<VagaPrivadaDTO>> (
				service.getAllFromMe ( userId, pageable ),
				HttpStatus.OK );
		
	}	
	
	
	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
	@Operation(summary=POST_SUMMARY, description=POST_DESCRIPTION, tags= {MAIN_TAG}, operationId=POST_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = VagaPrivadaDTO.class))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPrivadaDTO> create ( 
			@RequestHeader Long userId, 
			@RequestBody VagaPrivadaDTO vaga 
	) {
		validateUserId(userId);

		return new ResponseEntity<VagaPrivadaDTO>(
				service.create(userId, vaga),
				HttpStatus.CREATED );
	}


	
	
	@GetMapping("/{id}")
	@Operation(summary=GET_ONE_SUMMARY, description=GET_ONE_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_ONE_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VagaPrivadaDTO.class) ) } 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPrivadaDTO> getOne ( 
			@RequestHeader Long userId, 
			@PathVariable("id") Long vagaId
	) {
		validateVagaId(vagaId);
		validateUserId(userId);

		return new ResponseEntity<VagaPrivadaDTO> (
				service.getOne ( userId, vagaId ),
				HttpStatus.OK );
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary=UPDATE_ONE_SUMMARY, description=UPDATE_ONE_DESCRIPTION, tags= {MAIN_TAG}, operationId=UPDATE_ONE_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = VagaPrivadaDTO.class))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPrivadaDTO> updateOne(						
			@RequestHeader Long userId, 
			@PathVariable("id") Long vagaId, 
			@RequestBody VagaPrivadaDTO vaga
	) {
		validateVagaId(vagaId);
		validateUserId(userId);
		
		return new ResponseEntity<VagaPrivadaDTO> (
				service.updateOne ( userId, vagaId, vaga ),
				HttpStatus.OK );
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(summary=DELETE_ONE_SUMMARY, description=DELETE_ONE_DESCRIPTION, tags= {MAIN_TAG}, operationId=DELETE_ONE_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "204", content = {} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPrivadaDTO> deleteOne(						
			@RequestHeader Long userId, 
			@PathVariable("id") Long vagaId
	) {
		validateVagaId(vagaId);
		validateUserId(userId);
		
		service.deleteOne (userId, vagaId); 
		return new ResponseEntity<VagaPrivadaDTO> (
				HttpStatus.NO_CONTENT );
	}
	
	
	
	@GetMapping("/for/{ieId}")
	@Operation(summary=GET_ALL_FOR_SUMMARY, description=GET_ALL_FOR_DESCRIPTION, tags= {MAIN_TAG}, operationId=GET_ALL_FOR_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = VagaPrivadaDTO.class), uniqueItems = true, minItems = 0, maxItems = MAX_PAGE_VALUE))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<VagaPrivadaDTO>> getAllFor ( 
			@RequestHeader Long userId, 
			@PathVariable("ieId") Long ieId,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page

	) {	
		validateLimitAndOffset(limit, page);
		validateUserId(userId);
		
		Pageable pageable = PageRequest.of(page, limit);
		return new ResponseEntity<Page<VagaPrivadaDTO>> (
				service.getAllFor ( userId, pageable ),
				HttpStatus.OK );
		
	}
	

















}
