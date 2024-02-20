package com.github.juhachmann.estagios.infrastructure.db;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDBRepository extends ListCrudRepository<UserDBEntity, Long> {

}
