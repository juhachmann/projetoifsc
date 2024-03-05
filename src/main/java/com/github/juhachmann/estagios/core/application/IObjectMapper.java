package com.github.juhachmann.estagios.core.application;

public interface IObjectMapper {

    <D> D map(Object source, Class<D> destination);

}
