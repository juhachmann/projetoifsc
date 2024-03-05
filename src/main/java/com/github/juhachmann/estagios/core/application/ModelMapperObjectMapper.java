package com.github.juhachmann.estagios.core.application;

import org.modelmapper.ModelMapper;

public class ModelMapperObjectMapper implements IObjectMapper {

    ModelMapper mapper = new ModelMapper();

    @Override
    public <D> D map(Object source, Class<D> destination) {
        return mapper.map(source, destination);
    }

}
