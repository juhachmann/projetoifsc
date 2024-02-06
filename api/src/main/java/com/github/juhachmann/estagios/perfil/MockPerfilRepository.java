package com.github.juhachmann.estagios.perfil;


import static com.github.juhachmann.estagios.perfil.MockDatabasePerfil.perfilCollection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.juhachmann.estagios.exceptions.ResourceNotFoundException;

/**
 * Temporary class to mock a repository of perfilDTOs
 */

@Service
public class MockPerfilRepository {

//	private MockDatabasePerfil db = new MockDatabasePerfil();
	
	public List<PerfilPrivadoDTO> getAll() {		
		return perfilCollection;
	}
	
	// Isso não tá nada versátil, mas é assim que vai ficar por enquanto
	// Fere o princípio Open Closed
	// Pq toda vez que precisar colocar um novo parâmetro de busca, vai ter que mudar aqui
	// Como é só um mock e na vida real usa SQL para busca, aí não precisa se estressar
	public List<PerfilPrivadoDTO> getBy(List<String> nomes, List<String> cidades, List<String> estados, List<String> areas) {
		// Aqui no caso vai receber os queryParams
		List<PerfilPrivadoDTO> result;
		
		result = getAll();		
		
		if (nomes != null)
			result = getByName(result, nomes);
		
		if (cidades != null) 
			result = getByCidade(result, cidades); 
		
		if (estados != null) 
			result = getByEstado(result, estados) ;
		
		if (areas != null) 
			result = getByArea(result, areas) ;

		return result;
	}

	
	public PerfilPrivadoDTO getById(Long id) {
		for ( PerfilPrivadoDTO perfil : this.getAll()  ) {
			if ( perfil.getKey() == id ) {
				return perfil;
			}
		}
		throw new ResourceNotFoundException();		
	}
			

	public PerfilPrivadoDTO create(PerfilPrivadoDTO submitted) {
		System.out.println("Printing from repository: " + submitted.toString());
		System.out.println(perfilCollection.toString());
		//PerfilPrivadoDTO treated = submitted;
		submitted.setKey( MockDatabasePerfil.incrementAndGet() );
		perfilCollection.add(submitted);
		return submitted;
	}

	
	public PerfilPrivadoDTO update(long id, PerfilPrivadoDTO newObj) {
	
		PerfilPrivadoDTO toUpdate = getById(id);
	
		int index = perfilCollection.indexOf(toUpdate);
		
		// Atualiza o resource com novos dados de update, mantendo o id
		toUpdate = newObj;
		toUpdate.setKey(id);
		// Atualiza na Collection
		perfilCollection.set(index, toUpdate);
		
		return getById(id);
		
	}

	public void delete(long id) {
		PerfilPrivadoDTO resource = getById(id);
		perfilCollection.remove(resource);
	}
	
	public void deleteAll() {
		perfilCollection.clear();
	}
	
	
	// Buscas para instituições será por: nome, cidade, estado e área
	// Como não é com sql, tenho que definir as buscas aqui, o que não torna escalável.. 
	// Aqui entraria um factory method provavelmente (mas não vou fazer pq é só um mock)

	private List<PerfilPrivadoDTO> getByName(List<PerfilPrivadoDTO> listaAFiltrar, List<String> names) {
		List<PerfilPrivadoDTO> result = new ArrayList<PerfilPrivadoDTO>();
		for ( PerfilPrivadoDTO perfil : listaAFiltrar  ) {
			for ( String name : names ) {
				if ( perfil.getName() == name ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}
	
	private List<PerfilPrivadoDTO> getByCidade(List<PerfilPrivadoDTO> listaAFiltrar, List<String> cidades) {
		List<PerfilPrivadoDTO> result = new ArrayList<PerfilPrivadoDTO>();
		for ( PerfilPrivadoDTO perfil : listaAFiltrar  ) {
			for ( String cidade : cidades ) {
				if ( perfil.getAddress().getCity() == cidade ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}
	
	private List<PerfilPrivadoDTO> getByEstado(List<PerfilPrivadoDTO> listaAFiltrar, List<String> estados) {
		List<PerfilPrivadoDTO> result = new ArrayList<PerfilPrivadoDTO>();
		for ( PerfilPrivadoDTO perfil : listaAFiltrar  ) {
			for ( String estado : estados ) {
				if ( perfil.getAddress().getState() == estado ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}

	
	private List<PerfilPrivadoDTO> getByArea(List<PerfilPrivadoDTO> listaAFiltrar, List<String> areas) {
		List<PerfilPrivadoDTO> result = new ArrayList<PerfilPrivadoDTO>();
		for ( PerfilPrivadoDTO perfil : listaAFiltrar  ) {
			for ( String area : areas ) {
				if ( perfil.getAreas().contains(area) ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}
	
	// se fosse usar um factory seria assim:
//	private List<PerfilPrivadoDTO> getBy(PerfilGetByMethod getter) {
//		return getter.get();
//	}
	
}
