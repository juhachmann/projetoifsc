package com.github.juhachmann.estagios.perfil;

import org.springframework.stereotype.Service;

/** 
 * Generates Mocks for ConfigDTO 
 */

@Service
public class MockConfigDTO {
	

	/**
	 * Generates valid resource
	 * @return 
	 */
	public static ConfigDTO generateResource() {
		ConfigDTO resource = new ConfigDTO();
//		resource.getSettings().put("notifications", new NotificationsSettingsDTO());
		return resource;
	}
	
	
	/**
	 * Generates invalid resource, usefull to test I/O validations, such as invalid request bodies
	 * @param 
	 * @return 
	 */
	public static ConfigDTO generateInvalid() {
		ConfigDTO resource = new ConfigDTO();
		resource.setNotifications(null);
		return resource;
	}

	
}

