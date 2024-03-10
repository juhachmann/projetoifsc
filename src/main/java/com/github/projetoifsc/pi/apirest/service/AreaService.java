package com.github.projetoifsc.pi.apirest.service;

import com.github.projetoifsc.pi.apirest.dto.AreaDTO;
import com.github.projetoifsc.pi.apirest.utils.mock.AreaMock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
    public ResponseEntity<Page<AreaDTO>> getAll() {
        return new ResponseEntity<>(
                new PageImpl<>(AreaMock.getList()),
                HttpStatus.OK
        );
    }
}
