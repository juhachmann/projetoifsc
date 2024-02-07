package com.github.juhachmann.estagios.resources.authUserPerfil;

import java.util.ArrayList;
import java.util.Random;

import com.github.juhachmann.estagios.resources.shared.MockContactDto;
import com.github.juhachmann.estagios.resources.shared.MockLocalizacaoDto;

import java.util.List;

/**
 * Generates Mocks for AuthUserPerfilDTO
 */

public class AuthUserPerfilMock {
	
	private static Random gerador;
	
	
	// TODO: Refazer isso aqui!
	
	/**
	 * Generates a valid AuthUserPerfilDTO
	 * @return
	 */
	public static AuthUserPerfilDTO generateResource() {
		
		AuthUserPerfilDTO resource = new AuthUserPerfilDTO();		
		resource.setName("Nome");
		resource.setCnpj("20434016000110");
		resource.setIe(false);
		resource.setAddress( MockLocalizacaoDto.generateResource() );
		resource.setGeneralContact ( MockContactDto.generateResource() );
		resource.getAreas().add("Qualquer área");
		return resource;
	}


	/**
	 * Generates a list of valid AuthUserPerfilDTO
	 * @param itemsToAdd number of resources to return in the list
	 * @return
	 */
	public static List<AuthUserPerfilDTO> generateBundle(int itemsToAdd) {
		List<AuthUserPerfilDTO> bundle = new ArrayList<AuthUserPerfilDTO>();
		for (int i = 0; i < itemsToAdd; i++) {
			bundle.add( generateResource( ) );
		}
		return bundle;
	}
	
	
	/**
	 * Generates a invalid AuthUserPerfilDTO, usefull to test I/O validations, such as invalid request bodies
	 * @return
	 */
	public static AuthUserPerfilDTO generateInvalidResource() {
		gerador = new Random();
		int randomNumOfValidFields = gerador.nextInt(6);
		return generateInvalidResource(randomNumOfValidFields);
	}

	
	/**
	 * Generates a invalid AuthUserPerfilDTO, with only a specific number of valid fields, usefull to test I/O validations, such as invalid request bodies
	 * @param numOfValidFields 
	 * @return
	 */
	public static AuthUserPerfilDTO generateInvalidResource(int numOfValidFields) {
		AuthUserPerfilDTO resource = new AuthUserPerfilDTO();

		if (numOfValidFields > 0) 
			resource.setName("Nome");
		else return resource;

		if (numOfValidFields > 1) 
			resource.setCnpj("20434016000110");
		else return resource;

		if (numOfValidFields > 2) 
			resource.setIe(false);
		else return resource;
	
		if (numOfValidFields > 3)
			resource.setAddress( MockLocalizacaoDto.generateResource() );
		else return resource;
		
		if (numOfValidFields > 4) 
			resource.setGeneralContact ( MockContactDto.generateResource() );
		
		return resource;
	}
	

}
