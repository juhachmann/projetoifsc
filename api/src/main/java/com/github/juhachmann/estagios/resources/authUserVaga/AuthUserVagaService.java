package com.github.juhachmann.estagios.vagas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VagaPrivadaService {

	public VagaPrivadaDTO create(long ownerId, VagaPrivadaDTO vaga) {
		// TODO Auto-generated method stub
		return vaga;
	}

	public Page<VagaPrivadaDTO> getAllFromMe (long ownerId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public VagaPrivadaDTO getOne (Long userId, Long vagaId) {
		// TODO Auto-generated method stub
		return new VagaPrivadaDTO();
	}

	public VagaPrivadaDTO updateOne (Long userId, Long vagaId, VagaPrivadaDTO vaga) {
		// TODO Auto-generated method stub
		return vaga;
	}

	public void deleteOne(Long userId, Long vagaId) {
		// TODO Auto-generated method stub
	}
	
	
	public Page<VagaPrivadaDTO> getAllFor(Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// Aqui são sempre as versões públicas dessas vagas
	
	public Page<VagaPrivadaDTO> getAllForMe (Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public VagaPrivadaDTO getOnePublic(Long userId, Long vagaId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<VagaPrivadaDTO> getAllFrom(Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}



}
