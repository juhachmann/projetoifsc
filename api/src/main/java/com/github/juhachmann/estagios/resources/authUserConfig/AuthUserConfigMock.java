package com.github.juhachmann.estagios.resources.authUserConfig;

import org.springframework.stereotype.Service;

/** 
 * Generates Mocks for AuthUserConfigDTO 
 */

@Service
public class AuthUserConfigMock {
	

	/**
	 * Generates valid resource
	 * @return 
	 */
	public static AuthUserConfigDTO generateResource() {
		AuthUserConfigDTO resource = new AuthUserConfigDTO();
//		resource.getSettings().put("notifications", new NotificationsSettingsDTO());
		return resource;
	}
	
	
	/**
	 * Generates invalid resource, usefull to test I/O validations, such as invalid request bodies
	 * @param 
	 * @return 
	 */
	public static AuthUserConfigDTO generateInvalid() {
		AuthUserConfigDTO resource = new AuthUserConfigDTO();
		resource.setNotifications(null);
		return resource;
	}

	
}

