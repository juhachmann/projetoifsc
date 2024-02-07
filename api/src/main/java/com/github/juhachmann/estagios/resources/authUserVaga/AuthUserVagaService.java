package com.github.juhachmann.estagios.resources.authUserVaga;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthUserVagaService {

	public AuthUserVagaDTO create(long ownerId, AuthUserVagaDTO vaga) {
		// TODO Auto-generated method stub
		return vaga;
	}

	public Page<AuthUserVagaDTO> getAllFromMe (long ownerId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public AuthUserVagaDTO getOne (Long userId, Long vagaId) {
		// TODO Auto-generated method stub
		return new AuthUserVagaDTO();
	}

	public AuthUserVagaDTO updateOne (Long userId, Long vagaId, AuthUserVagaDTO vaga) {
		// TODO Auto-generated method stub
		return vaga;
	}

	public void deleteOne(Long userId, Long vagaId) {
		// TODO Auto-generated method stub
	}
	
	
	public Page<AuthUserVagaDTO> getAllFor(Long userId, Long ies, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// Aqui são sempre as versões públicas dessas vagas
	
	public Page<AuthUserVagaDTO> getAllForMe (Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AuthUserVagaDTO getOnePublic(Long userId, Long vagaId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<AuthUserVagaDTO> getAllFrom(Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}



}
