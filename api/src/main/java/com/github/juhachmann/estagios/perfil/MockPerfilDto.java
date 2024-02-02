package com.github.juhachmann.estagios.perfil;

import java.util.ArrayList;
import java.util.List;

import com.github.juhachmann.estagios.commom.MockContactDto;
import com.github.juhachmann.estagios.commom.MockLocalizacaoDto;

public class MockPerfilDto {
	

	public static PerfilDTO generateResource() {
		
		PerfilDTO resource = new PerfilDTO();
		
		resource.setName("Nome");
		resource.setCnpj("20434016000110");
		// Categoria
		resource.setIe(false);
		resource.setAddress( MockLocalizacaoDto.generateResource() );
		resource.setGeneralContact ( MockContactDto.generateResource() );
	//	resource.setApplicationContact ( MockContactDto.generateResource() );
	
		resource.getAreas().add("Qualquer área");
		
		//resource.getConfigs().add(new ConfigDTO("notifications", new NotificationsSettingsDTO() ) );
		
		return resource;
	}


	public static List<PerfilDTO> generateBundle(int itemsToAdd) {
		List<PerfilDTO> bundle = new ArrayList<PerfilDTO>();
		for (int i = 0; i < itemsToAdd; i++) {
			bundle.add( generateResource( ) );
		}
		return bundle;
	}

	

}