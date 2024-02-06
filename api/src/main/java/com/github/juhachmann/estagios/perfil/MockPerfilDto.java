package com.github.juhachmann.estagios.perfil;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import com.github.juhachmann.estagios.commom.MockContactDto;
import com.github.juhachmann.estagios.commom.MockLocalizacaoDto;

/**
 * Generates Mocks for PerfilDTO
 */

public class MockPerfilDto {
	
	private static Random gerador;
	
	
	/**
	 * Generates a valid PerfilPrivadoDTO
	 * @return
	 */
	public static PerfilPrivadoDTO generateResource() {
		
		PerfilPrivadoDTO resource = new PerfilPrivadoDTO();		
		resource.setName("Nome");
		resource.setCnpj("20434016000110");
		resource.setIe(false);
		resource.setAddress( MockLocalizacaoDto.generateResource() );
		resource.setGeneralContact ( MockContactDto.generateResource() );
		resource.getAreas().add("Qualquer área");
		return resource;
	}


	/**
	 * Generates a list of valid PerfilPrivadoDTO
	 * @param itemsToAdd number of resources to return in the list
	 * @return
	 */
	public static List<PerfilPrivadoDTO> generateBundle(int itemsToAdd) {
		List<PerfilPrivadoDTO> bundle = new ArrayList<PerfilPrivadoDTO>();
		for (int i = 0; i < itemsToAdd; i++) {
			bundle.add( generateResource( ) );
		}
		return bundle;
	}
	
	
	/**
	 * Generates a invalid PerfilPrivadoDTO, usefull to test I/O validations, such as invalid request bodies
	 * @return
	 */
	public static PerfilPrivadoDTO generateInvalidResource() {
		gerador = new Random();
		int randomNumOfValidFields = gerador.nextInt(6);
		return generateInvalidResource(randomNumOfValidFields);
	}

	
	/**
	 * Generates a invalid PerfilPrivadoDTO, with only a specific number of valid fields, usefull to test I/O validations, such as invalid request bodies
	 * @param numOfValidFields 
	 * @return
	 */
	public static PerfilPrivadoDTO generateInvalidResource(int numOfValidFields) {
		PerfilPrivadoDTO resource = new PerfilPrivadoDTO();

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
