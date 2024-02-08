package com.github.juhachmann.estagios.api.resources.area;

import static com.github.juhachmann.estagios.api.utils.HttpErrorMessages.BAD_REQUEST_MSG;
import static com.github.juhachmann.estagios.api.utils.HttpErrorMessages.TOO_MANY_REQUESTS_MSG;
import static com.github.juhachmann.estagios.api.utils.HttpErrorMessages.UNAUTHORIZED_MSG;
import static com.github.juhachmann.estagios.api.utils.SwaggerTags.BASE_URL;
import static com.github.juhachmann.estagios.api.utils.SwaggerTags.EMPRESA;
import static com.github.juhachmann.estagios.api.utils.SwaggerTags.IES;
import static com.github.juhachmann.estagios.api.utils.SwaggerTags.VAGAS;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.juhachmann.estagios.api.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = BASE_URL + "/areas", 
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
								MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class AreaController {

	
	private final String AREA_CONTENT_MSG = "{\"areas\" : [ \"Educação\" , \"Tecnologia\", \"Design\" ]}";
	
	public final String GET_ALL_ID = "getAllAreas";
	private final String GET_ALL_SUMMARY = "Ver todas as áreas";
	private final String GET_ALL_DESCRIPTION = "Ver as áreas de estudo para as quais as vagas de estágio podem se direcionar.";
	
	public final String GET_MINE_ID = "getMyAreas";
	private final String GET_MINE_SUMMARY = "Ver áreas criadas por você";
	private final String GET_MINE_DESCRIPTION = "Ver as áreas que a sua instituição de ensino inseriu no banco de dados.";

	public final String POST_ID = "createAreas";
	private final String POST_SUMMARY = "Criar áreas";
	private final String POST_DESCRIPTION = "Disponível apenas para instituições de ensino. Se a sua instituição percebeu que há áreas de estudo que não estão presentes no banco de dados, é possível inserir novas áreas relevantes. Lembre que estas novas áreas ficarão acessíveis para outras instituições de ensino também. Evite redundâncias e erros, para colaborar com a construção de uma base de dados consistente.";

	public final String PUT_ID = "updateAreas";
	private final String PUT_SUMMARY = "Atualizar áreas";
	private final String PUT_DESCRIPTION = "Sua instituição de ensino pode atualizar a lista de áreas criadas, para inserir novas áreas ou corrigir as existentes. Não é possível remover áreas que estejam sendo utilizadas por vagas disponíveis no sistema.";
	
	public final String DELETE_ID = "deleteAreas";
	private final String DELETE_SUMMARY = "Deletar áreas";
	private final String DELETE_DESCRIPTION = "Deletar toda a lista de áreas criada pela sua instituição de ensino. Possível apenas se nenhuma das áreas está sendo utilizada por uma vaga disponível no sistema.";

	
	
	@GetMapping("")
	@Operation(summary=GET_ALL_SUMMARY, description=GET_ALL_DESCRIPTION, tags={ VAGAS, IES, EMPRESA }, operationId=GET_ALL_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(examples= { @ExampleObject(value = AREA_CONTENT_MSG) })} ),
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public List<String> getAllAreas ( 
			@RequestHeader Long auth
	) {
		return null;
	}

	
	@GetMapping("/mine")
	@Operation(summary=GET_MINE_SUMMARY, description=GET_MINE_DESCRIPTION, tags={ VAGAS, IES }, operationId=GET_MINE_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(examples= { @ExampleObject(value = AREA_CONTENT_MSG) })} ),
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public List<String> getMyAreas (
			@RequestHeader Long auth
	) {
		return null;
	}

	
	
	@PostMapping(value = "/mine", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
	@Operation(summary=POST_SUMMARY, description=POST_DESCRIPTION, tags={ VAGAS, IES }, operationId=POST_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "201", content = {@Content(examples= { @ExampleObject(value = AREA_CONTENT_MSG) })} ),
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public List<String> createAreas (
			@RequestHeader Long auth,
			@RequestBody List<String> areasList
	) {
		return null;
	}

	
	
	@PutMapping(value = "/mine", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML })
	@Operation(summary=PUT_SUMMARY, description=PUT_DESCRIPTION, tags={ VAGAS, IES }, operationId=PUT_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(examples= { @ExampleObject(value = AREA_CONTENT_MSG) })} ),
	    		//links = {@Link(operationId="getPerfil", name="self"), @Link(operationId="getConfigs", name="configs"), @Link(operationId="getVagas", name="vagas"), @Link(operationId="getPerfilPublico", name="perfilPublico")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public List<String> updateAreas ( 
			@RequestHeader Long auth,
			@RequestBody List<String> areasList
	) {
		return null;
	}
	
	
	
	@DeleteMapping("/mine")
	@Operation(summary=DELETE_SUMMARY, description=DELETE_DESCRIPTION, tags={ VAGAS, IES }, operationId=DELETE_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "204"),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public List<String> deleteAreas (
			@RequestHeader Long auth
	) {
		return null;
	}
	
	
}
