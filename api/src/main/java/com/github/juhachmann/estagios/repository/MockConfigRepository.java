package com.github.juhachmann.estagios.repository;

import org.springframework.stereotype.Component;

import com.github.juhachmann.estagios.data.dto.ConfigDTO;
import com.github.juhachmann.estagios.data.mock.MockConfigDTO;
import com.github.juhachmann.estagios.data.mock.NotificationSettingsFactory;

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
