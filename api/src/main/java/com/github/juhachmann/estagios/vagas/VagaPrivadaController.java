package com.github.juhachmann.estagios.vagas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.juhachmann.estagios.perfil.PerfilPublicoDTO;
import com.github.juhachmann.estagios.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
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


	@GetMapping("")
	public ResponseEntity<VagaDTO> get(long id) {
		return null;
	}
	
	
	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
	@Operation(summary="", description="", tags= {}, operationId="")
	@ApiResponses({
		@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = VagaDTO.class))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaDTO> create ( 
			@RequestBody VagaDTO vaga 
	) {
		return null;
	}

	@GetMapping("/owners/{id}")
	public ResponseEntity<PerfilPublicoDTO> getOwnerById(
			@PathVariable("id") long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
