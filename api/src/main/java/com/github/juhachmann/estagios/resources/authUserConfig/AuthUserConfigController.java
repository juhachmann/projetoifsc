package com.github.juhachmann.estagios.resources.authUserConfig;

import com.github.juhachmann.estagios.utils.MediaTypes;

import static com.github.juhachmann.estagios.utils.HttpErrorMessages.*;
import static com.github.juhachmann.estagios.utils.SwaggerTags.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = BASE_URL + "/me/configs", 
				produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
						MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS })

public class AuthUserConfigController {
	
	@Autowired
	AuthUserConfigService service;
	
	
	public final String GET_CONFIG_ID = "getAuthUserConfig";
	public final String GET_CONFIG_SUMMARY = "Ver configurações";
	public final String GET_CONFIG_DESCRIPTION = "Exibe as configurações definidas pela instituição ou empresa";

	public final String UPDATE_CONFIG_ID = "updateAuthUserConfig";
	public final String UPDATE_CONFIG_SUMMARY = "Editar configurações";
	public final String UPDATE_CONFIG_DESCRIPTION = "Atualiza as configurações definidas pela instituição ou empresa";
	
	

	@GetMapping("")
	@Operation(summary=GET_CONFIG_SUMMARY, description=GET_CONFIG_DESCRIPTION, tags={PERFIL, IES, EMPRESA}, operationId=GET_CONFIG_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthUserConfigDTO.class))}), 
	    	//	links = {@Link(operationId="getConfigs", name="self"), @Link(operationId="getPerfil", name="perfil") }),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	  })
	public ResponseEntity<AuthUserConfigDTO> getConfigs (
			@RequestHeader long auth
	) throws Exception {
		return new ResponseEntity<AuthUserConfigDTO>(
				service.getPerfilConfig(auth), 
				HttpStatus.OK);		
	}

	
	
	@PutMapping("")
	@Operation(summary=UPDATE_CONFIG_SUMMARY, description=UPDATE_CONFIG_DESCRIPTION, tags={PERFIL, IES, EMPRESA}, operationId=UPDATE_CONFIG_ID)
	@ApiResponses({
	    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthUserConfigDTO.class))}), 
	    		//links = {@Link(operationId="getConfigs", name="self"),@Link(operationId="getPerfil", name="perfil")}),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	  })
	
	public ResponseEntity<AuthUserConfigDTO> updateConfigs(
			@RequestHeader long auth, 
			@RequestBody AuthUserConfigDTO config
	) throws Exception {
		return new ResponseEntity<AuthUserConfigDTO>(
				service.updateConfig(auth, config), 
				HttpStatus.OK);	
	}
	
	
}
