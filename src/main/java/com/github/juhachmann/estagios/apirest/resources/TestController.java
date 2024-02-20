package com.github.juhachmann.estagios.apirest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.juhachmann.estagios.infrastructure.db.UserDBEntity;
import com.github.juhachmann.estagios.infrastructure.db.UserDBRepository;
import com.github.juhachmann.estagios.infrastructure.db.VagaDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/teste")
public class TestController {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	UserDBRepository repository;

	@Autowired
	VagaDBRepository vagaRepo;
	
	@GetMapping("")
	public String testComQueryParams(
			@RequestParam(value = "message", defaultValue = "Olá") String message,
			@RequestParam(value = "interlocutor", defaultValue = "Mundo") String interlocutor,
			@RequestParam(value = "signature", defaultValue="") String signature
	) {

		String greeting = "";
		
		return greeting.concat(message).concat(", " + interlocutor + "! \n").concat(signature) ;
	}

	@GetMapping("/ola")
	public String hello( ) {
		return "";
	}

	@GetMapping("/user")
	public String createUser() throws JsonProcessingException {

//		var user = UserDBEntity.createUser("Juliana", "35857234000134", false, "Apenas uma empresa de teste", "4899999999", "ju@juju.com", "Florianópolis", "Santa Catarina", "Brasil");
//
//		var saved = repository.save(user);
//
//		var vaga = new VagaDBEntity(saved, "Titulo da vaga", "Descrição da Vaga", "Nenhum", 900, 20, "Híbrido", "Rua sdsdsadasd", "Florianópolis", "Santa Catarina", "Brasil", "kjkhkhkj", "vaga@vaga.com", 0, LocalDateTime.now());
//
//		vagaRepo.save(vaga);
//
//		var savedVaga = vagaRepo.findById(1L);
//
//		// TODO : LEARN : Atenção a essa expressão usando Optional.map()
//        return savedVaga.map(vagaDBEntity -> vagaDBEntity.getOwner().getName()).orElse(null);

		var user = repository.findById(7L);

		List<UserDBEntity> receivers = new ArrayList<>();
		receivers.add(user.get());

		var savedVaga = vagaRepo.findById(1L).get();
		savedVaga.setReceivers(receivers);
//		System.out.println(
//				mapper.writeValueAsString(savedVaga)
//
//		);
		vagaRepo.save(savedVaga);



		System.out.println(
				user.map(userDBEntity -> userDBEntity.getOwnedVagas().get(0).getTitle()).orElse(null)
		);

		System.out.println(
				user.map(userDBEntity -> userDBEntity.getReceivedVagas().size()).orElse(null)
		);

		return null;
//		return user.map(userDBEntity -> userDBEntity.getOwnedVagas().get(0).getTitle()).orElse(null);

    }
	
}
