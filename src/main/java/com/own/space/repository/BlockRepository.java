package com.own.space.repository;

import com.own.space.domain.AbstractBaseBlock;

import java.util.List;

public interface BlockRepository<T extends AbstractBaseBlock> {

    T getById(int id);

    T save(T block);

    boolean delete(int id);

    List<T> getAll(int userId);

    List<T> getAllForParent(int parentId);

    List<T> getAllForMainWindow(int userId);
}
