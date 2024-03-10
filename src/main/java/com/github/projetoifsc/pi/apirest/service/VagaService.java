package com.github.projetoifsc.pi.apirest.service;

import com.github.projetoifsc.pi.apirest.controller.VagaController;
import com.github.projetoifsc.pi.apirest.dto.VagaPrivateProfileDTO;
import com.github.projetoifsc.pi.apirest.dto.VagaPublicProfileDTO;
import com.github.projetoifsc.pi.apirest.utils.mock.VagaMock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class VagaService {


    ModelMapper mapper = new ModelMapper();

    public ResponseEntity<VagaPrivateProfileDTO> create(VagaPrivateProfileDTO vaga) {
        vaga.setKey("123");
        vaga.setOwnerId("2");
        var mapped = mapper.map
                (vaga, VagaPrivateProfileDTO.class);
        mapped.add(
            linkTo(
            methodOn(VagaController.class)
                .getPublicProfile(vaga.getKey()))
                .withRel("perfilPublico"),
            linkTo(
            methodOn(VagaController.class)
                .getPrivateProfile(vaga.getKey()))
                .withSelfRel()
            );

        return new ResponseEntity<VagaPrivateProfileDTO>(
                mapped,
                HttpStatus.CREATED );
    }


    public ResponseEntity<Page<VagaPublicProfileDTO>> search(String titulo, String areas, String niveis, Double remuneracao, String periodos, String sort, String order, int page, int limit) {
        var vagas = VagaMock.getList();
        var vagasDto = vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPublicProfileDTO.class
        )).toList();

        vagasDto.forEach(vaga ->
            vaga.add(
                linkTo(
        methodOn(VagaController.class)
                        .getPrivateProfile(vaga.getKey()))
                        .withSelfRel(),
                linkTo(
        methodOn(VagaController.class)
                        .getPublicProfile(vaga.getKey()))
                        .withRel("perfilPublico")
        ));

        var pageImpl = new PageImpl<>(vagasDto);

        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }


    public ResponseEntity<VagaPrivateProfileDTO> update(Long vagaId, VagaPrivateProfileDTO vaga) {
        vaga.setKey("123");
        vaga.setOwnerId("2");
        var mapped = mapper.map(
                vaga, VagaPrivateProfileDTO.class
        );
        return new ResponseEntity<VagaPrivateProfileDTO> (
                mapped,
                HttpStatus.OK );
    }


    public ResponseEntity<VagaPrivateProfileDTO> delete(Long vagaId) {
        return new ResponseEntity<VagaPrivateProfileDTO> (
                HttpStatus.NO_CONTENT );
    }


    public ResponseEntity<VagaPublicProfileDTO> getPublicProfile(String vagaId) {
        var vaga = VagaMock.getOne();
        var mapped = mapper.map(
                vaga,
                VagaPublicProfileDTO.class
        );
        return new ResponseEntity<>(
                mapped,
                HttpStatus.OK
        );
    }


    public ResponseEntity<VagaPrivateProfileDTO> getPrivateProfile(String vagaId) {
        var vaga = VagaMock.getOne();
        var mapped = mapper.map(
                vaga,
                VagaPrivateProfileDTO.class
        );
        return new ResponseEntity<> (
                mapped,
                HttpStatus.OK );
    }


    public ResponseEntity<Page<VagaPublicProfileDTO>> getAllReceivedByUser(String id, Integer page, Integer limit) {
        var vagas = VagaMock.getList();
        var vagasDto = vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPublicProfileDTO.class
        )).toList();
        var pageImpl = new PageImpl<>(vagasDto);
        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }


    public ResponseEntity<Page<VagaPrivateProfileDTO>> getAllCreatedByUser(String id, Integer page, Integer limit) {
        var vagas = VagaMock.getList();
        var vagasDTO = vagas.stream().map(vaga -> mapper.map(
                vaga,
                VagaPrivateProfileDTO.class
        )).toList();
        var pageImpl = new PageImpl<>(vagasDTO);

        return new ResponseEntity<> (
                pageImpl,
                HttpStatus.OK );
    }


}
