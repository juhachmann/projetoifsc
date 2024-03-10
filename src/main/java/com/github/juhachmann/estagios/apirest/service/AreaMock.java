package com.github.juhachmann.estagios.apirest.service;

import com.github.juhachmann.estagios.apirest.dto.AreaDTO;

import java.util.ArrayList;
import java.util.List;

public class AreaMock {

    public static AreaDTO getOne() {
        return AreaMock.getList().get(0);
    }

    public static List<AreaDTO> getList() {
        List<AreaDTO> list = new ArrayList<>();
        list.add(new AreaDTO("1", "3","Engenharia Elétrica"));
        list.add(new AreaDTO("1", "4","Educação"));
        list.add(new AreaDTO("2", "4","Letras"));
        list.add(new AreaDTO("3", "3","Tecnologia da Informação"));
        list.add(new AreaDTO("5", "4","Ciências Humanas"));
        return list;
    }

}
