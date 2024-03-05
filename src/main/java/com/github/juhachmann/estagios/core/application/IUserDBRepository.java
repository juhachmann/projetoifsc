package com.github.juhachmann.estagios.core.application;


public interface IUserDBRepository {

    IUserDB findByUsername(String username);

    IUserDB save(IUserDB user);

    void delete(IUserDB authUser);

    IUserDB findById(String targetUserId);


}
