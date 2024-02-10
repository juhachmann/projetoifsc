package com.github.juhachmann.estagios;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.juhachmann.estagios.api.exceptions.InvalidException;
import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilDTO;
import com.github.juhachmann.estagios.api.resources.authUserPerfil.AuthUserPerfilMock;
import com.github.juhachmann.estagios.api.resources.userPerfil.UserPerfilDTO;
import com.github.juhachmann.estagios.api.utils.ValidationHelper;
import com.github.juhachmann.estagios.core.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GeneralTest {
	
	ObjectMapper mapper = new ObjectMapper();

	private void print(String str) {
		System.out.println(str);
	}
	
	private void print(@NotNull Object obj) {
		System.out.println(obj.toString());
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws Exception {

	}





}
