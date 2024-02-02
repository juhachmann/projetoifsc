package com.github.juhachmann.estagios.data.mock;

import com.github.juhachmann.estagios.data.dto.SettingsDTO;

public interface SettingsFactory {
	
	String getName();
	
	SettingsDTO generate();

}
