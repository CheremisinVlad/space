package com.own.space.service;

import com.own.space.domain.User;
import com.own.space.repository.UserRepository;
import com.own.space.util.exceptions.NotFoundException;
import com.own.space.web.payload.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.own.space.util.Validation.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) {
        checkNew(user);
        return repository.save(user);
    }

    @Override
    public User get(int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(userId),userId);
    }

    @Override
    public List<User> getAll() {
        return  repository.getAll();
    }
}
