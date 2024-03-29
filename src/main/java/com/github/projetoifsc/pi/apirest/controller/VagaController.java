package com.github.projetoifsc.pi.apirest.controller;

import com.github.projetoifsc.pi.apirest.dto.UserDTO;
import com.github.projetoifsc.pi.apirest.dto.VagaPrivateProfileDTO;
import com.github.projetoifsc.pi.apirest.dto.VagaPublicProfileDTO;
import com.github.projetoifsc.pi.apirest.service.VagaService;
import com.github.projetoifsc.pi.apirest.utils.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static com.github.projetoifsc.pi.apirest.utils.HttpErrorMessages.*;
import static com.github.projetoifsc.pi.apirest.utils.swagger.SwaggerTags.BASE_URL;
import static com.github.projetoifsc.pi.apirest.utils.swagger.SwaggerTags.VAGAS;
import static com.github.projetoifsc.pi.apirest.utils.validation.PaginationValidation.DEFAULT_LIMIT_VALUE;
import static com.github.projetoifsc.pi.apirest.utils.validation.PaginationValidation.DEFAULT_PAGE_VALUE;


@RestController
@RequestMapping(value = BASE_URL,
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
						MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS })
public class VagaController {

	VagaService service;

	@Autowired
	public VagaController(VagaService service) {
		this.service = service;
	}

	@PostMapping(value = "/vagas", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
	@Operation(summary="Criar Vaga", description="Criar Nova Vaga de Estágio", tags= {VAGAS}, operationId="postVaga")
	@ApiResponses({
			@ApiResponse(responseCode = "201"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPrivateProfileDTO> create (
			@RequestBody VagaPrivateProfileDTO vaga
	) {
		return service.create(vaga);
	}


	@GetMapping(value = "/vagas")
	@Operation(summary="Procurar Vaga", description="Procura Vagas Disponíveis", tags= {VAGAS}, operationId="searchVaga")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<PagedModel<EntityModel<VagaPublicProfileDTO>>> search (
			@RequestParam(value = "titulo", defaultValue = "", required = false) String titulo,
			@RequestParam(value = "areas", defaultValue = "", required = false) String areas,
			@RequestParam(value = "niveis", defaultValue = "", required = false) @Schema(allowableValues = {"fundamental", "medio", "superior", "tecnico", "pos"}) String niveis,
			@RequestParam(value = "remuneracao", defaultValue = "0") @Schema(description = "Remuneração mínima") Integer remuneracao,
			@RequestParam(value = "periodos", defaultValue = "") @Schema(allowableValues = {"matutino", "vespertino", "noturno"}) String periodos,
			@RequestParam(value = "sort", defaultValue = "") @Schema(allowableValues = {"periodos", "remuneracao", "data"}) String sort,
			@RequestParam(value = "order", defaultValue = "ASC") @Schema(type = "string", allowableValues = {"ASC", "DESC"}) String order,
			@RequestParam(value= "page", defaultValue = "0") @Schema(type = "number") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") @Schema(type = "number", maxContains = 30) Integer limit
	) {
		var stringArgs = new HashMap<String, String>();
		stringArgs.put("titulo", titulo);
		stringArgs.put("areas", areas);
		stringArgs.put("niveis", niveis);
		stringArgs.put("periodos", periodos);
		stringArgs.put("sort", sort);

		var numericArgs = new HashMap<String, Integer>();
		numericArgs.put("remuneracao", remuneracao);
		numericArgs.put("page", page);
		numericArgs.put("limit", limit);

		return service.search(stringArgs, numericArgs);

	}



	@PutMapping("/vagas/{id}")
	@Operation(summary="Atualizar Vaga", description="Atualizar uma vaga de estágio", tags= {VAGAS}, operationId="putVaga")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPrivateProfileDTO> update(
			@PathVariable("id") String vagaId,
			@RequestBody VagaPrivateProfileDTO vaga
	) {
		return service.update(vagaId, vaga);
	}


	@DeleteMapping("/vagas/{id}")
	@Operation(summary="Deletar Vaga", description="Deletar uma vaga de estágio e todos os dados relacionados a ela", tags= {VAGAS}, operationId="deleteVaga")
	@ApiResponses({
			@ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPrivateProfileDTO> delete(
			@PathVariable("id") String vagaId
	) {
		return service.delete(vagaId);
	}

	@GetMapping("/vagas/{id}")
	@Operation(summary="Ver Vaga", description="Ver o perfil público de uma vaga. Autorizado apenas ao criador ou destinatários da vaga.", tags= {VAGAS}, operationId="getVaga")
	@ApiResponses({
		@ApiResponse(responseCode = "200"),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPublicProfileDTO> getPublicProfile (
			@PathVariable("id") String vagaId
	) {
		return service.getPublicProfile(vagaId);
	}


	@GetMapping("/vagas/{id}/private")
	@Operation(summary="Ver Perfil Privado de Vaga", description="Ver perfil privado de uma vaga. Autorizado apenas ao criador da vaga.", tags= {VAGAS}, operationId="getVagaPrivate")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<VagaPrivateProfileDTO> getPrivateProfile (
			@PathVariable("id") String vagaId
	) {
		return service.getPrivateProfile(vagaId);
	}


	@GetMapping("/users/{id}/vagas/received")
	@Operation(summary="Vagas recebidas", description="Ver as vagas recebidas pelo usuário autenticado", tags= {VAGAS}, operationId="getVagasRecebidas")
	@ApiResponses({
		@ApiResponse(responseCode = "200"),
		@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
		@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
		@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
		@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<VagaPublicProfileDTO>> getAllReceivedByUser (
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page,
			@PathVariable String id) {
		return service.getAllReceivedByUser(id, page, limit);
	}


	@GetMapping("/users/{id}/vagas")
	@Operation(summary="Vagas Criadas", description="Ver as vagas criadas pelo usuário autenticado.", tags= { VAGAS}, operationId="getVagasCriadas")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<VagaPrivateProfileDTO>> getAllCreatedByUser (
			@RequestParam(value= "limit", defaultValue = DEFAULT_LIMIT_VALUE) Integer limit,
			@RequestParam(value= "page", defaultValue = DEFAULT_PAGE_VALUE) Integer page,
			@PathVariable String id) {
		return service.getAllCreatedByUser(id, page, limit);
	}


	public ResponseEntity<Page<UserDTO>> getVagaRecipients(
			@PathVariable String id) {
		return service.getVagaRecipients(id);
	}
}
