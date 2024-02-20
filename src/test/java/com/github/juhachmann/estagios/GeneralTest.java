package com.github.juhachmann.estagios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.juhachmann.estagios.core.application.UserPrivateProfileDTO;
import com.github.juhachmann.estagios.core.application.UserPublicProfileDTO;
import com.github.juhachmann.estagios.core.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.RecordMatcher;


class GeneralTest {
	
//	ObjectMapper mapper = new ObjectMapper();
//
//	private void print(String str) {
//		System.out.println(str);
//	}
//
//	private void print(@NotNull Object obj) {
//		System.out.println(obj.toString());
//	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws Exception {
		ModelMapper mapper = new ModelMapper();

		ObjectMapper jsonMapper = new ObjectMapper();

		var userDTO = new UserPrivateProfileDTO();
		userDTO.setId(2L);
		userDTO.setProfileName("Joana");


		System.out.println(
				jsonMapper.writeValueAsString(
					mapper.map(userDTO, User.class)
				)
		);


	}

}
