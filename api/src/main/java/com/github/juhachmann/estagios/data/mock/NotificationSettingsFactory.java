package com.github.juhachmann.estagios.data.mock;

import com.github.juhachmann.estagios.data.dto.NotificationsSettingsDTO;
import com.github.juhachmann.estagios.data.dto.SettingsDTO;

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
