package com.own.space.repository.dataJpa;

import com.own.space.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudUrlRepository extends JpaRepository<Url,Integer> {
}
