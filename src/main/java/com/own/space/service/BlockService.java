package com.own.space.service;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.domain.Directory;

import java.util.List;

public interface BlockService<T extends AbstractBaseBlock> {

    T get(int id);

    T save(T block);

    T update(T block);

    boolean delete(int id);

    List<T> getAll(int userId);

    List<T> getAllParent(int parentId);

    List<T> getAllMain(int userId);
}
