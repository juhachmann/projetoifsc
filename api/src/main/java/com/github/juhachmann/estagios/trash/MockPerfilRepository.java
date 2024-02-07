package com.github.juhachmann.estagios.trash;


import static com.github.juhachmann.estagios.trash.MockDatabasePerfil.perfilCollection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.juhachmann.estagios.exceptions.ResourceNotFoundException;
import com.github.juhachmann.estagios.resources.authUserPerfil.AuthUserPerfilDTO;

/**
 * Temporary class to mock a repository of perfilDTOs
 */

@Service
public class MockPerfilRepository {

//	private MockDatabasePerfil db = new MockDatabasePerfil();
	
	public List<AuthUserPerfilDTO> getAll() {		
		return perfilCollection;
	}
	
	// Isso não tá nada versátil, mas é assim que vai ficar por enquanto
	// Fere o princípio Open Closed
	// Pq toda vez que precisar colocar um novo parâmetro de busca, vai ter que mudar aqui
	// Como é só um mock e na vida real usa SQL para busca, aí não precisa se estressar
	public List<AuthUserPerfilDTO> getBy(List<String> nomes, List<String> cidades, List<String> estados, List<String> areas) {
		// Aqui no caso vai receber os queryParams
		List<AuthUserPerfilDTO> result;
		
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

	
	public AuthUserPerfilDTO getById(Long id) {
		for ( AuthUserPerfilDTO perfil : this.getAll()  ) {
			if ( perfil.getKey() == id ) {
				return perfil;
			}
		}
		throw new ResourceNotFoundException();		
	}
			

	public AuthUserPerfilDTO create(AuthUserPerfilDTO submitted) {
		System.out.println("Printing from repository: " + submitted.toString());
		System.out.println(perfilCollection.toString());
		//AuthUserPerfilDTO treated = submitted;
		submitted.setKey( MockDatabasePerfil.incrementAndGet() );
		perfilCollection.add(submitted);
		return submitted;
	}

	
	public AuthUserPerfilDTO update(long id, AuthUserPerfilDTO newObj) {
	
		AuthUserPerfilDTO toUpdate = getById(id);
	
		int index = perfilCollection.indexOf(toUpdate);
		
		// Atualiza o resource com novos dados de update, mantendo o id
		toUpdate = newObj;
		toUpdate.setKey(id);
		// Atualiza na Collection
		perfilCollection.set(index, toUpdate);
		
		return getById(id);
		
	}

	public void delete(long id) {
		AuthUserPerfilDTO resource = getById(id);
		perfilCollection.remove(resource);
	}
	
	public void deleteAll() {
		perfilCollection.clear();
	}
	
	
	// Buscas para instituições será por: nome, cidade, estado e área
	// Como não é com sql, tenho que definir as buscas aqui, o que não torna escalável.. 
	// Aqui entraria um factory method provavelmente (mas não vou fazer pq é só um mock)

	private List<AuthUserPerfilDTO> getByName(List<AuthUserPerfilDTO> listaAFiltrar, List<String> names) {
		List<AuthUserPerfilDTO> result = new ArrayList<AuthUserPerfilDTO>();
		for ( AuthUserPerfilDTO perfil : listaAFiltrar  ) {
			for ( String name : names ) {
				if ( perfil.getName() == name ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}
	
	private List<AuthUserPerfilDTO> getByCidade(List<AuthUserPerfilDTO> listaAFiltrar, List<String> cidades) {
		List<AuthUserPerfilDTO> result = new ArrayList<AuthUserPerfilDTO>();
		for ( AuthUserPerfilDTO perfil : listaAFiltrar  ) {
			for ( String cidade : cidades ) {
				if ( perfil.getAddress().getCity() == cidade ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}
	
	private List<AuthUserPerfilDTO> getByEstado(List<AuthUserPerfilDTO> listaAFiltrar, List<String> estados) {
		List<AuthUserPerfilDTO> result = new ArrayList<AuthUserPerfilDTO>();
		for ( AuthUserPerfilDTO perfil : listaAFiltrar  ) {
			for ( String estado : estados ) {
				if ( perfil.getAddress().getState() == estado ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}

	
	private List<AuthUserPerfilDTO> getByArea(List<AuthUserPerfilDTO> listaAFiltrar, List<String> areas) {
		List<AuthUserPerfilDTO> result = new ArrayList<AuthUserPerfilDTO>();
		for ( AuthUserPerfilDTO perfil : listaAFiltrar  ) {
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
//	private List<AuthUserPerfilDTO> getBy(PerfilGetByMethod getter) {
//		return getter.get();
//	}
	
}
