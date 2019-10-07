package com.own.space.repository.dataJpa;

import com.own.space.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface CrudUserRepository extends JpaRepository<User,Integer> {

    @Override
    User save(User user);

    @Override
    Optional<User> findById(Integer id);

    @Override
    List<User> findAll(Sort sort);

    User findByName(String name);

    User getByEmail(String email);
}
