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

    private CrudUserRepository repository;

    public UserRepositoryImpl(CrudUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_BY_NAME);
    }

    @Override
    public User getByUsername(String name) {
        return repository.findByName(name);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }
}
