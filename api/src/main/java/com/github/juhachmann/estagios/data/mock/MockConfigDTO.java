package com.github.juhachmann.estagios.data.mock;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.juhachmann.estagios.data.dto.ConfigDTO;
import com.github.juhachmann.estagios.data.dto.ConfigSettingsDTO;

@Service
public class MockConfigDTO {
	
	
	public static ConfigDTO generateResource(SettingsFactory factory) {
				
		ConfigDTO resource = new ConfigDTO();
		
		resource.getSettings().add( new ConfigSettingsDTO( factory.getName(), factory.generate() ) );
				
		return resource;
	}
	
	
	public static ConfigDTO generateResource(List<SettingsFactory> factoriesToUse) {

		ConfigDTO resource = new ConfigDTO();
		
		factoriesToUse.forEach((factory) -> {
			resource.getSettings().add( new ConfigSettingsDTO( factory.getName(), factory.generate() ) );
		});
				
		return resource;
	}

}

