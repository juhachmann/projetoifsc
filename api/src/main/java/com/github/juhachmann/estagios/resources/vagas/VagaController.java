package com.github.juhachmann.estagios.resources.vagas;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class VagaController {
	
	
	@Autowired
	VagaService service;
	
	
	private final String GET_ONE_PUBLIC_SUMMARY = "Ver Vaga";
	private final String GET_ONE_PUBLIC_DESCRIPTION = "Vê o perfil público de uma vaga. Acessível apenas se a instituição ou empresa é a criadora da vaga ou a destinatária dela.";
	private final String GET_ONE_PUBLIC_ID = "getVagaPublic";
	
	private final String GET_FOR_ME_SUMMARY = "Vagas para mim";
	private final String GET_FOR_ME_DESCRIPTION = "Ver todas as vagas disponibilizadas para a instituição de ensino";
	private final String GET_FOR_ME_ID = "getAllForMe";
	
	private final String GET_ALL_FROM_SUMMARY = "Vagas criadas por determinada organização";
	private final String GET_ALL_FROM_DESCRIPTION = "Ver todas as vagas criadas por determinada instituição ou empresa e disponbilizadas para sua instituição de ensino";
	private final String GET_ALL_FROM_ID = "getAllFrom";

	
	
	@GetMapping("/{id}/public")
	@Operation(summary=GET_ONE_PUBLIC_SUMMARY, description=GET_ONE_PUBLIC_DESCRIPTION, tags= { VAGAS, IES, EMPRESA }, operationId=GET_ONE_PUBLIC_ID)
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
			@RequestHeader Long auth, 
			@PathVariable("id") Long vagaId
	) {

		return new ResponseEntity<VagaDTO> (
				service.getOnePublic ( auth, vagaId ),
				HttpStatus.OK );
	}
	
	
	@GetMapping("/for/me")
	@Operation(summary=GET_FOR_ME_SUMMARY, description=GET_FOR_ME_DESCRIPTION, tags= { VAGAS, IES }, operationId=GET_FOR_ME_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = VagaDTO.class), uniqueItems = true, minItems = 0))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
		),
		// TODO - Decidir se uma empresa acessa esse endpoint ou se vira um 404 pra ela
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<VagaDTO>> getAllForMe ( 
//			@RequestHeader Long auth, 
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
	) {
		
		Pageable pageable = PageRequest.of(page, limit);
		Long auth = 1L;
		return new ResponseEntity<Page<VagaDTO>> (
				service.getAllForMe ( auth, pageable ),
				HttpStatus.OK );
	}
	
	
	// TODO - Decidir se uma empresa acessa esse endpoint ou se vira um 404 pra ela
	@GetMapping("/for/me/from/{id}")
	@Operation(summary=GET_ALL_FROM_SUMMARY, description=GET_ALL_FROM_DESCRIPTION, tags= { VAGAS, IES }, operationId=GET_ALL_FROM_ID)
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = VagaDTO.class), uniqueItems = true, minItems = 0))} 
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}
				),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<VagaDTO>> getAllFrom ( 
			@RequestHeader Long auth, 
			@PathVariable("id") Long ownerId,
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page
	) {	
		
		Pageable pageable = PageRequest.of(page, limit);
		return new ResponseEntity<Page<VagaDTO>> (
				service.getAllFrom ( auth, ownerId, pageable ),
				HttpStatus.OK );
		
	}
	
	
}
