package com.own.space.repository.dataJpa;

import com.own.space.domain.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CrudDirectoryRepository extends JpaRepository<Directory,Integer> {

    Optional<Directory> getById(int id);

    @Query("SELECT d FROM Directory d WHERE d.userId=:userId")
    List<Directory> getAll(@Param("userId")int userId);

    Directory save(Directory directory);

    @Query("SELECT d FROM Directory d WHERE d.parentId=:parentId")
    List<Directory> getAllForParent(@Param("parentId")int parentId);

    @Query("DELETE FROM Directory d WHERE d.id=:id")
    @Modifying
    int delete(@Param("id")int id);

    @Query("SELECT d FROM Directory d WHERE d.userId=:userId AND d.isMain=:isMain")
    List<Directory> getAllForMainWindow(@Param("userId")int userId,@Param("isMain")boolean isMain);
}
