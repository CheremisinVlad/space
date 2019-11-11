package com.own.space.repository.impls;

import com.own.space.domain.User;
import com.own.space.repository.UserRepository;
import com.own.space.repository.dataJpa.CrudUserRepository;
import com.own.space.util.encription.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public static final Sort SORT_BY_NAME = Sort.by("name");

    private CrudUserRepository repository;

    private PasswordEncryptor encryptor;

    public UserRepositoryImpl(CrudUserRepository repository, PasswordEncryptor encryptor) {
        this.repository = repository;
        this.encryptor = encryptor;
    }

    @Override
    public User save(User user) {

        String password = encryptor.encrypt(user.getPassword());
        user.setPassword(password);
        LOG.debug("user repository save {}",user);
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
