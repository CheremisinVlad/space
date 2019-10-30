package com.own.space.repository.dataJpa;

import com.own.space.domain.AbstractBaseBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CrudBlockRepository<T extends AbstractBaseBlock> extends JpaRepository<T,Integer> {

    Optional<T> getById(int id);

    @Query("SELECT d FROM  #{#entityName} d WHERE d.userId=:userId")
    List<T> getAll(@Param("userId")int userId);

    @Query("SELECT d FROM #{#entityName} d WHERE d.parentId=:parentId")
    List<T> getAllParent(@Param("parentId")int parentId);

    @Query("DELETE FROM #{#entityName} d WHERE d.id=:id")
    @Modifying
    int delete(@Param("id")int id);

    @Query("SELECT d FROM #{#entityName} d WHERE d.userId=:userId AND d.parentId=0")
    List<T> getAllMain(@Param("userId")int userId);
}
