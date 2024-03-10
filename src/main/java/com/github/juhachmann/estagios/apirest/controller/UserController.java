package com.github.juhachmann.estagios.apirest.controller;

import static com.github.juhachmann.estagios.apirest.utils.HttpErrorMessages.*;
import static com.github.juhachmann.estagios.apirest.utils.swagger.SwaggerTags.*;

import com.github.juhachmann.estagios.apirest.dto.UserDTO;
import com.github.juhachmann.estagios.apirest.dto.UserPrivateProfileDTO;
import com.github.juhachmann.estagios.apirest.dto.UserPublicProfileDTO;
import com.github.juhachmann.estagios.apirest.service.UserMock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.juhachmann.estagios.apirest.utils.MediaTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;


@RestController
@RequestMapping(
		value = BASE_URL + "/entidades",
		produces = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML, 
				MediaTypes.APPLICATION_HAL, MediaTypes.APPLICATION_HAL_FORMS } )

public class UserController {


	ModelMapper mapper = new ModelMapper();

	@PostMapping(value = "/perfil", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Criar Perfil da Entidade", description="Cadastro de Nova Entidade", tags={PERFIL}, operationId="userPost")
	@ApiResponses({
			@ApiResponse(responseCode = "201"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPrivateProfileDTO> createNewUser (
			@RequestBody UserPrivateProfileDTO perfil
	) {
		perfil.setId("111");
		var userDTO = mapper.map(
				perfil,
				UserPrivateProfileDTO.class);

		return new ResponseEntity<>(
				userDTO,
				HttpStatus.CREATED);
	}


	@GetMapping("/perfil")
	@Operation(summary="Perfil da entidade autenticado", description="Ver Perfil Privado da entidade autenticada", tags={PERFIL}, operationId="getPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "200"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPrivateProfileDTO> getAuthUserPerfil () {

		var userDTO = mapper.map(
				UserMock.getOne(),
				UserPrivateProfileDTO.class
		);

		return new ResponseEntity<> (
				userDTO,
				HttpStatus.OK);	
	}

	
	@PutMapping(value = "/perfil", consumes = { MediaTypes.APPLICATION_JSON, MediaTypes.APPLICATION_XML, MediaTypes.APPLICATION_YAML } )
	@Operation(summary="Atualizar perfil", description="Atualizar perfil da entidade autenticada", tags={PERFIL}, operationId="putPerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "200"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPrivateProfileDTO> updateAuthUserPerfil (
			@RequestBody UserPrivateProfileDTO perfil
	)  {
		perfil.setId("123");
		var userDTO = mapper.map(
				perfil,
				UserPrivateProfileDTO.class
		);
		
		return new ResponseEntity<>(
				userDTO,
				HttpStatus.OK);		
	}
	
	
	
	@DeleteMapping("/perfil")
	@Operation(summary="Deletar Perfil", description="Deletar perfil da entidade autenticada e todos os seus dados (vagas de estágio, áreas, etc)", tags={PERFIL}, operationId="deletePerfil")
	@ApiResponses({
	    @ApiResponse(responseCode = "204"),
	    @ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
	    @ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
	    @ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	  })
	public ResponseEntity<UserPrivateProfileDTO> deleteAuthUserPerfil () {

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("")
	@Operation(summary="Entidades", description="Lista das entidades cadastradas no sistema", tags={PERFIL}, operationId="usersGetAll")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<UserDTO>> getAllUsers() {
		var users = UserMock.getList();
        return this.getPageFromList(users);
	}

	@GetMapping("/educacionais")
	@Operation(summary="Instituições de Ensino", description="Lista de todas as instituições de ensino cadastradas no sistema", tags={PERFIL}, operationId="getSchools")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<Page<UserDTO>> getAllSchools() {
		var users = UserMock.getSchools();
        return this.getPageFromList(users);
	}

//	@GetMapping("/entidades/{id}/privado")
//	@Operation(summary="Perfil privado do usuário", description="Ver perfil privado do usuário", tags={PERFIL}, operationId="getUserPrivateProfile")
//	@ApiResponses({
//			@ApiResponse(responseCode = "200"),
//			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
//			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
//			@ApiResponse(responseCode = "403", content = {@Content(examples= { @ExampleObject(value = FORBIDDEN_MSG) })} ),
//			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
//			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
//	})
//	public ResponseEntity<UserPrivateProfileDTO> getUserPrivateProfile(
//			@PathVariable String id
//	) {
//		var user = UserMock.getOne();
//		var userDTO = mapper.map(
//				user,
//				UserPrivateProfileDTO.class
//		);
//		return new ResponseEntity<>(
//				userDTO,
//				HttpStatus.OK
//		);
//	}

	@GetMapping("/{id}")
	@Operation(summary="Perfil da entidade", description="Ver Perfil Público da entidade", tags={PERFIL}, operationId="getUserPublicProfile")
	@ApiResponses({
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", content = {@Content(examples= { @ExampleObject(value = BAD_REQUEST_MSG) })} ),
			@ApiResponse(responseCode = "401", content = {@Content(examples= { @ExampleObject(value = UNAUTHORIZED_MSG) })} ),
			@ApiResponse(responseCode = "404", content = {@Content(examples= { @ExampleObject(value = NOT_FOUND_MSG) })} ),
			@ApiResponse(responseCode = "429", content = {@Content(examples= { @ExampleObject(value = TOO_MANY_REQUESTS_MSG) })} )
	})
	public ResponseEntity<UserPublicProfileDTO> getUserPublicProfile(
			@PathVariable String id
	) {
		var user = UserMock.getOne();
		var userDTO = mapper.map(
				user,
				UserPublicProfileDTO.class
		);
		return new ResponseEntity<>(
				userDTO,
				HttpStatus.OK
		);
	}

	private ResponseEntity<Page<UserDTO>> getPageFromList(List<UserPrivateProfileDTO> users) {
		var usersDTO = users.stream().map(user -> mapper.map(user, UserDTO.class)).toList();
		var page = new PageImpl<>(usersDTO);
		return new ResponseEntity<>(
				page,
				HttpStatus.OK
		);
	}


}
