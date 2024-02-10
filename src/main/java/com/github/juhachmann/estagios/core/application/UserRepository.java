package com.github.juhachmann.estagios.core.application;

import com.github.juhachmann.estagios.core.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Long> {



}
