package com.own.space.service;

import com.own.space.domain.User;
import com.own.space.repository.UserRepository;
import com.own.space.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.own.space.util.Validation.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public User create(User user) {
        checkNew(user);
        return repository.save(user);
    }

    @Override
    public User get(int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.getById(userId),userId);
    }

    @Override
    public List<User> getAll() {
        return  repository.getAll();
    }
}
