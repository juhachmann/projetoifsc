package com.github.juhachmann.estagios.trash;

import static com.github.juhachmann.estagios.trash.MockVagasDatabase.vagasCollection;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.juhachmann.estagios.apirest.exceptions.ResourceNotFoundException;
import com.github.juhachmann.estagios.apirest.resources.vagas.VagaDTO;

/**
 * Temporary mock to simulate a repository 
 * 
 */
@Repository
public class MockVagaRepository {
	
	public List<VagaDTO> getAll() {		
		return vagasCollection;
	}
	
	// Isso não tá nada versátil, mas é assim que vai ficar por enquanto
	// Fere o princípio Open Closed
	// Pq toda vez que precisar colocar um novo parâmetro de busca, vai ter que mudar aqui
	// Como é só um mock e na vida real usa SQL para busca, aí não precisa se estressar
	public List<VagaDTO> getBy() {
		// Aqui no caso vai receber os queryParams
		List<VagaDTO> result;
		
		result = getAll();		
		
		return result;
	}

	
	public VagaDTO getById(Long id) {
		for ( VagaDTO vaga : this.getAll()  ) {
			if ( vaga.getKey() == id ) {
				return vaga;
			}
		}
		throw new ResourceNotFoundException();		
	}
			

	public VagaDTO create(VagaDTO submitted) {
		submitted.setKey( MockVagasDatabase.incrementAndGet() );
		vagasCollection.add(submitted);
		return submitted;
	}

	
	public VagaDTO update(long id, VagaDTO newObj) {
	
		VagaDTO toUpdate = getById(id);
	
		int index = vagasCollection.indexOf(toUpdate);
		
		// Atualiza o resource com novos dados de update, mantendo o id
		toUpdate = newObj;
		toUpdate.setKey(id);
		// Atualiza na Collection
		vagasCollection.set(index, toUpdate);
		
		return getById(id);
		
	}

	public void delete(long id) {
		VagaDTO resource = getById(id);
		vagasCollection.remove(resource);
	}
	
	public void deleteAll() {
		vagasCollection.clear();
	}
	

}
