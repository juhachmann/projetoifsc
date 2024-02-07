package com.github.juhachmann.estagios.perfil;

/**
 * Factory to create pre-given Notification Settings for the user configurations
 */
public class NotificationSettingsFactory implements SettingsFactory {

	@Override
	public SettingsDTO generate() {
		return new NotificationsSettingsDTO();
	}


}
