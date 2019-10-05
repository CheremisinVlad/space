package com.own.space.repository.dataJpa;

import com.own.space.domain.User;
import com.own.space.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    public static final Sort SORT_BY_NAME = Sort.by("name");

    @Autowired
    private CrudUserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_BY_NAME);
    }
}
