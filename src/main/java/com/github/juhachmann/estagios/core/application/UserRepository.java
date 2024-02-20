package com.github.juhachmann.estagios.core.application;

import com.github.juhachmann.estagios.core.entities.User;

public interface UserRepository {
    User save(User user);

    User findByUsername(String username);

    void delete(User user);

    User findById(Long id);
}
