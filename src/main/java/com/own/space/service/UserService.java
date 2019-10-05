package com.own.space.service;

import com.own.space.domain.User;
import com.own.space.util.exceptions.NotFoundException;

import java.util.List;

public interface UserService {

   User create(User user);

    User get(int userId) throws NotFoundException;

    List<User> getAll();
}
