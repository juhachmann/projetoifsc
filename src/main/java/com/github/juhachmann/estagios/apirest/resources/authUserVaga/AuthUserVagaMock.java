package com.github.juhachmann.estagios.apirest.resources.authUserVaga;

import java.util.ArrayList;
import java.util.List;

public class AuthUserVagaMock {

	public static AuthUserVagaDTO generateResource() {
		AuthUserVagaDTO vaga = new AuthUserVagaDTO();
		vaga.setTitle("Título da Vaga");
		vaga.setDescription("Descrição da vaga");
		vaga.getRequirements().add("Requisito valido");
		vaga.getPeriods().add("matutino");
		vaga.setWorkloadInHours(20);
		vaga.setPayment(900);
		vaga.getLevels().add("tecnico");
		vaga.getIes().add(1L);
		vaga.getAreas().add("Enfermagem");
		vaga.getCourses().add("Enfermagem");
		vaga.getExternalLinks().add("Valid Link");
		vaga.setExpiringInDays(20);
		vaga.setRenovateInDays(180);
		return vaga;
	}

	public static AuthUserVagaDTO generateInvalidResource() {
		AuthUserVagaDTO vaga = new AuthUserVagaDTO();
		vaga.setTitle("    ");
		vaga.setDescription("    ");
		vaga.getRequirements().add("   ");
		vaga.getPeriods().add("   ");
		vaga.setWorkloadInHours(-1);
		vaga.setPayment(-1);
		vaga.getLevels().add("   ");
		vaga.getIes().add(null);
		vaga.getAreas().add(null);
		vaga.getCourses().add("   ");
		vaga.getExternalLinks().add("   ");
		vaga.setExpiringInDays(-1);
		vaga.setRenovateInDays(-1);
		return vaga;
	}

	public static List<AuthUserVagaDTO> generateBundle(int numberOfItems) {
		List<AuthUserVagaDTO> lista = new ArrayList<>();
		for(int i = 0; i < numberOfItems; i++) {
			lista.add(generateResource());
		}
		return lista;
	}

}
