package com.github.juhachmann.estagios.perfil;

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

import com.github.juhachmann.estagios.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.links.Link;


@EnableHypermediaSupport(type = HypermediaType.HAL_FORMS)
@RestController
@Tag(name="Perfil", description="Ações para o perfil da instituição ou empresa")
@RequestMapping(
		value = "/perfil", 
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

/**
 * REST Controller for "/perfil" related endpoints
 */
public class PerfilPrivadoController {
	
	
	// Swagger Messages
	// TODO - Achar uma casa pra isso aqui
	// Estas são as descrições para os erros 
	// Isso aqui é pra estar no sistema inteiro:
	private final String BAD_REQUEST = "Invalid data was sent in the request.";
	private final String UNAUTHORIZED = "Authentication failed. You must be correctly authenticated to access this resource.";
	private final String TOO_MANY_REQUESTS = "Exceeded the rate limit for requests.";

	private final String BAD_REQUEST_MSG = "{\"code\":400,\"message\": \"" + BAD_REQUEST + "\"}";
	private final String UNAUTHORIZED_MSG = "{\"code\":401,\"message\": \"" + UNAUTHORIZED + "\"}";
	private final String TOO_MANY_REQUESTS_MSG = "{\"code\":429,\"message\": \"" + TOO_MANY_REQUESTS + "\"}";

	private final String MAIN_TAG = "Perfil";
	
	private final String GET_SUMMARY = "Ver perfil";
	private final String GET_DESCRIPTION = "Exibe as informações da instituição ou empresa";
	private final String POST_SUMMARY = "Criar perfil";
	private final String POST_DESCRIPTION = "Cria o perfil da instituição ou empresa";
	private final String PUT_SUMMARY = "Editar perfil";
	private final String PUT_DESCRIPTION = "Atualiza o perfil da instituição ou empresa";
	private final String DELETE_SUMMARY = "Deletar perfil";
	private final String DELETE_DESCRIPTION = "Deleta o perfil da instituição ou empresa e todos os seus dados (anúncios de vagas, etc). Para utilizar o sistema, é necessário possuir um perfil ativo";
	private final String GET_CONFIG_SUMMARY = "Ver configurações";
	private final String GET_CONFIG_DESCRIPTION = "Exibe as configurações definidas pela instituição ou empresa";
	private final String PUT_CONFIG_SUMMARY = "Editar configurações";
	private final String PUT_CONFIG_DESCRIPTION = "Atualiza as configurações definidas pela instituição ou empresa";
		
	@Autowired
	PerfilPrivadoService service;
	
	@GetMapping("")
	@Operation(summary=GET_SUMMARY, description=GET_DESCRIPTION, tags={MAIN_TAG}, operationId="getPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = PerfilPrivadoDTO.class))}, 
	    		links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<PerfilPrivadoDTO> get (
				@RequestHeader(HttpHeaders.ALLOW) @Parameter(name = HttpHeaders.ALLOW, schema = @Schema(type = "number"), required = true, in = ParameterIn.HEADER) long id 
	) throws Exception {
		
		return new ResponseEntity<PerfilPrivadoDTO> (
				service.get(id), 
				HttpStatus.OK);	
	}
	
	
	
	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary=POST_SUMMARY, description=POST_DESCRIPTION, tags={MAIN_TAG}, operationId="postPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = PerfilPrivadoDTO.class))}, 
	    		links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<PerfilPrivadoDTO> create ( 
			@RequestBody PerfilPrivadoDTO dto 
	) throws Exception {
				
		return new ResponseEntity<PerfilPrivadoDTO>(
				service.create(dto),
				HttpStatus.CREATED);		
	}
	

	
	@PutMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary=PUT_SUMMARY, description=PUT_DESCRIPTION, tags={MAIN_TAG}, operationId="putPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = PerfilPrivadoDTO.class))}, 
	    		links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<PerfilPrivadoDTO> update (
			@RequestHeader(HttpHeaders.ALLOW) @Parameter(name = HttpHeaders.ALLOW, schema = @Schema(type = "number"), required = true, in = ParameterIn.HEADER) long id,
			@RequestBody PerfilPrivadoDTO newDto
	) throws Exception {
		
		return new ResponseEntity<PerfilPrivadoDTO>(
				service.update(newDto), 
				HttpStatus.OK);		
	}
	
	
	
	@DeleteMapping("")
	@Operation(summary=DELETE_SUMMARY, description=DELETE_DESCRIPTION, tags={MAIN_TAG}, operationId="deletePerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "204"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	  })
	public ResponseEntity<PerfilPrivadoDTO> delete (
			@RequestHeader(HttpHeaders.ALLOW) @Parameter(name = HttpHeaders.ALLOW, schema = @Schema(type = "number"), required = true, in = ParameterIn.HEADER) long id
	) throws Exception {
		service.delete(id);
		return new ResponseEntity<PerfilPrivadoDTO>(HttpStatus.NO_CONTENT);
	}

	
	
	@GetMapping("/configs")
	@Operation(summary=GET_CONFIG_SUMMARY, description=GET_CONFIG_DESCRIPTION, tags={MAIN_TAG}, operationId="getConfigs")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ConfigDTO.class))}, 
	    		links = {@Link(operationId="getConfigs", name="self"), @Link(operationId="getPerfil", name="perfil") }),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	  })
	public ResponseEntity<ConfigDTO> getConfigs (
			@RequestHeader(HttpHeaders.ALLOW) @Parameter(name = HttpHeaders.ALLOW, schema = @Schema(type = "number"), required = true, in = ParameterIn.HEADER) long id
	) throws Exception {
		return new ResponseEntity<ConfigDTO>(
				service.getPerfilConfig(id), 
				HttpStatus.OK);		
	}

	
	
	@PutMapping("/configs")
	@Operation(summary=PUT_CONFIG_SUMMARY, description=PUT_CONFIG_DESCRIPTION, tags={MAIN_TAG}, operationId="putConfigs")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ConfigDTO.class))}, 
	    		links = {@Link(operationId="getConfigs", name="self"),@Link(operationId="getPerfil", name="perfil")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	  })
	
	public ResponseEntity<ConfigDTO> updateConfigs(
			@RequestHeader(HttpHeaders.ALLOW) @Parameter(name = HttpHeaders.ALLOW, schema = @Schema(type = "number"), required = true, in = ParameterIn.HEADER) long id, 
			@RequestBody ConfigDTO resource
	) throws Exception {
		return new ResponseEntity<ConfigDTO>(
				service.updateConfig(id, resource), 
				HttpStatus.OK);	
	}
	

}
