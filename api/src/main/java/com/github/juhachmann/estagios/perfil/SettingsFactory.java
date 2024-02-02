package com.github.juhachmann.estagios.perfil;

public interface SettingsFactory {
	
	String getName();
	
	SettingsDTO generate();

}
