package com.own.space.service;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.domain.Directory;

import java.util.List;

public interface BlockService<T extends AbstractBaseBlock> {

    T create(T block);

    T update(T block);

    boolean delete(int id);

    List<T> getAll(int userId);

    List<T> getAllForParent(int parentId);

    List<T> getAllForMainWindow(int userId);
}
