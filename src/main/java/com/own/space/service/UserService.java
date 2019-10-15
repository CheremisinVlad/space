package com.own.space.service;

import com.own.space.domain.User;
import com.own.space.util.exceptions.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

   User create(User user);

    User get(int userId) throws NotFoundException;

    List<User> getAll();

    User getByName(String name) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;
}
