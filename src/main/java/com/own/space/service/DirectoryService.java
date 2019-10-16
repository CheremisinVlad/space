package com.own.space.service;

import com.own.space.domain.Directory;

import java.util.List;

public interface DirectoryService {

    Directory create(Directory directory);

    Directory update(Directory directory);

    boolean delete(int id);

    List<Directory> getAll(int userId);

    List<Directory> getAllForParent(int parentId);

    List<Directory> getAllForMainWindow(int userId);

}
