package com.github.juhachmann.estagios.perfil;


import static com.github.juhachmann.estagios.perfil.MockDatabasePerfil.perfilCollection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.github.juhachmann.estagios.commom.LocalizacaoDTO;
import com.github.juhachmann.estagios.exceptions.ResourceNotFoundException;

@Service
public class MockPerfilRepository {

	private MockDatabasePerfil db = new MockDatabasePerfil();
	
	public List<PerfilDTO> getAll() {		
		return perfilCollection;
	}
	
	// Isso não tá nada versátil, mas é assim que vai ficar por enquanto
	// Fere o princípio Open Closed
	// Pq toda vez que precisar colocar um novo parâmetro de busca, vai ter que mudar aqui
	// Como é só um mock e na vida real usa SQL para busca, aí não precisa se estressar
	public List<PerfilDTO> getBy(List<String> nomes, List<String> cidades, List<String> estados, List<String> areas) {
		// Aqui no caso vai receber os queryParams
		List<PerfilDTO> result;
		
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

	
	public PerfilDTO getById(Long id) {
		for ( PerfilDTO perfil : this.getAll()  ) {
			if ( perfil.getKey() == id ) {
				return perfil;
			}
		}
		throw new ResourceNotFoundException();		
	}
			

	public PerfilDTO create(PerfilDTO submitted) {
		System.out.println("Printing from repository: " + submitted.toString());
		System.out.println(perfilCollection.toString());
		//PerfilDTO treated = submitted;
		submitted.setKey( db.incrementAndGet() );
		perfilCollection.add(submitted);
		return submitted;
	}

	
	public PerfilDTO update(long id, PerfilDTO newObj) {
	
		PerfilDTO toUpdate = getById(id);
	
		int index = perfilCollection.indexOf(toUpdate);
		
		// Atualiza o resource com novos dados de update, mantendo o id
		toUpdate = newObj;
		toUpdate.setKey(id);
		// Atualiza na Collection
		perfilCollection.set(index, toUpdate);
		
		return getById(id);
		
	}

	public void delete(long id) {
		PerfilDTO resource = getById(id);
		perfilCollection.remove(resource);
	}
	
	public void deleteAll() {
		perfilCollection.clear();
	}
	
	
	// Buscas para instituições será por: nome, cidade, estado e área
	// Como não é com sql, tenho que definir as buscas aqui, o que não torna escalável.. 
	// Aqui entraria um factory method provavelmente (mas não vou fazer pq é só um mock)

	private List<PerfilDTO> getByName(List<PerfilDTO> listaAFiltrar, List<String> names) {
		List<PerfilDTO> result = new ArrayList<PerfilDTO>();
		for ( PerfilDTO perfil : listaAFiltrar  ) {
			for ( String name : names ) {
				if ( perfil.getName() == name ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}
	
	private List<PerfilDTO> getByCidade(List<PerfilDTO> listaAFiltrar, List<String> cidades) {
		List<PerfilDTO> result = new ArrayList<PerfilDTO>();
		for ( PerfilDTO perfil : listaAFiltrar  ) {
			for ( String cidade : cidades ) {
				if ( perfil.getAddress().getCity() == cidade ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}
	
	private List<PerfilDTO> getByEstado(List<PerfilDTO> listaAFiltrar, List<String> estados) {
		List<PerfilDTO> result = new ArrayList<PerfilDTO>();
		for ( PerfilDTO perfil : listaAFiltrar  ) {
			for ( String estado : estados ) {
				if ( perfil.getAddress().getState() == estado ) {
					result.add(perfil);
					break;
				}
			}
		}
		return result;
	}

	
	private List<PerfilDTO> getByArea(List<PerfilDTO> listaAFiltrar, List<String> areas) {
		List<PerfilDTO> result = new ArrayList<PerfilDTO>();
		for ( PerfilDTO perfil : listaAFiltrar  ) {
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
//	private List<PerfilDTO> getBy(PerfilGetByMethod getter) {
//		return getter.get();
//	}
	
}
