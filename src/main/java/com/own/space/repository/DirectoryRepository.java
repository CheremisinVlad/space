package com.own.space.repository;

import com.own.space.domain.Directory;

import java.util.List;

public interface DirectoryRepository {

    Directory getById(int id);

    Directory save(Directory directory);
    
    boolean delete(int directoryId);

    List<Directory> getAll(int userId);

    List<Directory> getAllForParent(int parentId);
    
    List<Directory> getAllForMainWindow(int userId);

    

}
