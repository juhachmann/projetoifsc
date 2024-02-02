package com.github.juhachmann.estagios.perfil;

import org.springframework.stereotype.Component;

@Component
public class MockConfigRepository {
	
	public ConfigDTO getById(Long id) {
		return MockConfigDTO.generateResource( new NotificationSettingsFactory() );
	}

	
	public ConfigDTO update(long id, ConfigDTO newObj) {
		return MockConfigDTO.generateResource( new NotificationSettingsFactory() );
	}

	public void delete(long id) {
		
	}
	
}
