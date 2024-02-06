package com.github.juhachmann.estagios.perfil;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import com.github.juhachmann.estagios.commom.MockContactDto;
import com.github.juhachmann.estagios.commom.MockLocalizacaoDto;

/**
 * Generates Mocks for PerfilPublicoDTO
 */

public class MockPerfilDto {
	
	private static Random gerador;
	
	
	/**
	 * Generates a valid PerfilDTO
	 * @return
	 */
	public static PerfilDTO generateResource() {
		
		PerfilDTO resource = new PerfilDTO();		
		resource.setName("Nome");
		resource.setCnpj("20434016000110");
		resource.setIe(false);
		resource.setAddress( MockLocalizacaoDto.generateResource() );
		resource.setGeneralContact ( MockContactDto.generateResource() );
		resource.getAreas().add("Qualquer área");
		return resource;
	}


	/**
	 * Generates a list of valid PerfilDTO
	 * @param itemsToAdd number of resources to return in the list
	 * @return
	 */
	public static List<PerfilDTO> generateBundle(int itemsToAdd) {
		List<PerfilDTO> bundle = new ArrayList<PerfilDTO>();
		for (int i = 0; i < itemsToAdd; i++) {
			bundle.add( generateResource( ) );
		}
		return bundle;
	}
	
	
	/**
	 * Generates a invalid PerfilDTO, usefull to test I/O validations, such as invalid request bodies
	 * @return
	 */
	public static PerfilDTO generateInvalidResource() {
		gerador = new Random();
		int randomNumOfValidFields = gerador.nextInt(6);
		return generateInvalidResource(randomNumOfValidFields);
	}

	
	/**
	 * Generates a invalid PerfilDTO, with only a specific number of valid fields, usefull to test I/O validations, such as invalid request bodies
	 * @param numOfValidFields 
	 * @return
	 */
	public static PerfilDTO generateInvalidResource(int numOfValidFields) {
		PerfilDTO resource = new PerfilDTO();

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
