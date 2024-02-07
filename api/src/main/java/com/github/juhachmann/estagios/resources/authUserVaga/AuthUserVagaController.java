package com.github.juhachmann.estagios.resources.authUserVaga;

import static com.github.juhachmann.estagios.utils.HttpErrorMessages.BAD_REQUEST_MSG;
import static com.github.juhachmann.estagios.utils.HttpErrorMessages.FORBIDDEN_MSG;
import static com.github.juhachmann.estagios.utils.HttpErrorMessages.NOT_FOUND_MSG;
import static com.github.juhachmann.estagios.utils.HttpErrorMessages.TOO_MANY_REQUESTS_MSG;
import static com.github.juhachmann.estagios.utils.HttpErrorMessages.UNAUTHORIZED_MSG;
import static com.github.juhachmann.estagios.utils.PaginationValidation.DEFAULT_LIMIT_VALUE;
import static com.github.juhachmann.estagios.utils.PaginationValidation.DEFAULT_PAGE_VALUE;
import static com.github.juhachmann.estagios.utils.SwaggerTags.BASE_URL;
import static com.github.juhachmann.estagios.utils.SwaggerTags.EMPRESA;
import static com.github.juhachmann.estagios.utils.SwaggerTags.IES;
import static com.github.juhachmann.estagios.utils.SwaggerTags.VAGAS;

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

import com.github.juhachmann.estagios.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = BASE_URL + "/vagas", 
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
						MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS })

public class AuthUserVagaController {

	
	@Autowired
	AuthUserVagaService service;
	

	private final String GET_ALL_SUMMARY = "Minhas Vagas";
	private final String GET_ALL_DESCRIPTION = "Ver todas as vagas criadas pela instituição ou empresa";
	public final String GET_ALL_ID = "getAllVagas";

	private final String POST_SUMMARY = "Criar Vaga";
	private final String POST_DESCRIPTION = "Criar uma nova oferta de vaga de estágio";
	public final String POST_ID = "createVaga";

	private final String GET_ONE_SUMMARY = "Ver vaga";
	private final String GET_ONE_DESCRIPTION = "Ver os detalhes de determinada vaga criada pela instituição ou empresa";
	public final String GET_ONE_ID = "getVaga";
	
	private final String UPDATE_ONE_SUMMARY = "Editar vaga";
	private final String UPDATE_ONE_DESCRIPTION = "Editar e atualizar uma vaga criada pela instituição ou empresa";
	public final String UPDATE_ONE_ID = "updateVaga";
	
	private final String DELETE_ONE_SUMMARY = "Deletar Vaga";
	private final String DELETE_ONE_DESCRIPTION = "Deletar uma vaga criada pela instituição ou empresa";
	public final String DELETE_ONE_ID = "deleteVaga";
	
	private final String GET_ALL_FOR_SUMMARY = "Vagas disponibilizadas para determinada instituição";
	private final String GET_ALL_FOR_DESCRIPTION = "Ver todas as vagas criadas pela sua organização e disponibilizadas para determinada instituição de ensino";
	public final String GET_ALL_FOR_ID = "getAllFor";
	
	
	
	@GetMapping("")
	@Operation(summary=GET_ALL_SUMMARY, description=GET_ALL_DESCRIPTION, tags= { VAGAS, IES, EMPRESA }, operationId=GET_ALL_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = AuthUserVagaDTO.class), uniqueItems = true, minItems = 0))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<AuthUserVagaDTO>> getAllFromMe ( 
			@RequestHeader Long auth, 
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
	) {	
		
		Pageable pageable = PageRequest.of(page, limit);
		return new ResponseEntity<Page<AuthUserVagaDTO>> (
				service.getAllFromMe ( auth, pageable ),
				HttpStatus.OK );
		
	}	
	
	
	@PostMapping(value = "", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
	@Operation(summary=POST_SUMMARY, description=POST_DESCRIPTION, tags= { VAGAS, IES, EMPRESA }, operationId=POST_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = AuthUserVagaDTO.class))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<AuthUserVagaDTO> create ( 
			@RequestHeader Long auth, 
			@RequestBody AuthUserVagaDTO vaga 
	) {


		return new ResponseEntity<AuthUserVagaDTO>(
				service.create(auth, vaga),
				HttpStatus.CREATED );
	}


	
	
	@GetMapping("/{id}")
	@Operation(summary=GET_ONE_SUMMARY, description=GET_ONE_DESCRIPTION, tags= { VAGAS, IES, EMPRESA }, operationId=GET_ONE_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthUserVagaDTO.class) ) } 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<AuthUserVagaDTO> getOne ( 
			@RequestHeader Long auth, 
			@PathVariable("id") Long vagaId
	) {
		
		return new ResponseEntity<AuthUserVagaDTO> (
				service.getOne ( auth, vagaId ),
				HttpStatus.OK );
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary=UPDATE_ONE_SUMMARY, description=UPDATE_ONE_DESCRIPTION, tags= { VAGAS, IES, EMPRESA }, operationId=UPDATE_ONE_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthUserVagaDTO.class))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<AuthUserVagaDTO> updateOne(						
			@RequestHeader Long auth, 
			@PathVariable("id") Long vagaId, 
			@RequestBody AuthUserVagaDTO vaga
	) {

		return new ResponseEntity<AuthUserVagaDTO> (
				service.updateOne ( auth, vagaId, vaga ),
				HttpStatus.OK );
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(summary=DELETE_ONE_SUMMARY, description=DELETE_ONE_DESCRIPTION, tags= { VAGAS, IES, EMPRESA }, operationId=DELETE_ONE_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "204"),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<AuthUserVagaDTO> deleteOne(						
			@RequestHeader Long auth, 
			@PathVariable("id") Long vagaId
	) {
		
		service.deleteOne (auth, vagaId); 
		return new ResponseEntity<AuthUserVagaDTO> (
				HttpStatus.NO_CONTENT );
	}
	
	
	
	@GetMapping("/for/{id}")
	@Operation(summary=GET_ALL_FOR_SUMMARY, description=GET_ALL_FOR_DESCRIPTION, tags= { VAGAS, IES, EMPRESA }, operationId=GET_ALL_FOR_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = AuthUserVagaDTO.class), uniqueItems = true, minItems = 0))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<AuthUserVagaDTO>> getAllFor ( 
			@RequestHeader Long auth, 
			@PathVariable("id") Long ies,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page

	) {	
		
		Pageable pageable = PageRequest.of(page, limit); // pageNumber, pageSize
		return new ResponseEntity<Page<AuthUserVagaDTO>> (
				service.getAllFor ( auth, ies, pageable ),
				HttpStatus.OK );
		
	}
	

}
