package com.own.space.repository;

import com.own.space.domain.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    User get(int id);

    List<User> getAll();
}
