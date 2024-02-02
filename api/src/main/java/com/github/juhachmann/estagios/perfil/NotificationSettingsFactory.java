package com.github.juhachmann.estagios.perfil;

public class NotificationSettingsFactory implements SettingsFactory {

	@Override
	public SettingsDTO generate() {
		return new NotificationsSettingsDTO();
	}

	@Override
	public String getName() {
		return "notifications";
	}

}
