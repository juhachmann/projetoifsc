package com.github.juhachmann.estagios.perfil;

/**
 * Interface to create new Settings Factory with name and subsettings object
 */
public interface SettingsFactory {
	
//	String getName();
	
	SettingsDTO generate();

}
