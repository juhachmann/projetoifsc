package com.github.juhachmann.estagios.trash;

import org.springframework.stereotype.Component;

import com.github.juhachmann.estagios.apirest.resources.authUserConfig.AuthUserConfigDTO;
import com.github.juhachmann.estagios.apirest.resources.authUserConfig.AuthUserConfigMock;

@Component
public class MockConfigRepository {
	
	public AuthUserConfigDTO getById(Long id) {
		return AuthUserConfigMock.generateResource(  );
	}

	
	public AuthUserConfigDTO update(long id, AuthUserConfigDTO newObj) {
		return AuthUserConfigMock.generateResource(  );
	}

	public void delete(long id) {
		
	}
	
}
