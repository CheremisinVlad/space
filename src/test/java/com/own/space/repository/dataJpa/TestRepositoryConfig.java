package com.own.space.repository.dataJpa;

import com.own.space.domain.Directory;
import com.own.space.domain.Record;
import com.own.space.domain.Url;
import com.own.space.repository.BlockRepository;
import com.own.space.repository.impls.DirectoryRepositoryImpl;
import com.own.space.repository.impls.RecordRepositoryImpl;
import com.own.space.repository.impls.UrlRepositoryImpl;
import com.own.space.util.aspects.RepositoryExceptionInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@TestConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TestRepositoryConfig {


    @Bean
    public BlockRepository<Record> recordRepository(CrudRecordRepository crud) {
        return new RecordRepositoryImpl(crud);
    }

    @Bean
    public RepositoryExceptionInterceptor repositoryExceptionInterceptor() {
        return new RepositoryExceptionInterceptor();
    }

    @Bean
    public BlockRepository<Directory> urlRepository(CrudDirectoryRepository crud) {
        return new DirectoryRepositoryImpl(crud);
    }

    @Bean
    public BlockRepository<Url> directoryRepository(CrudUrlRepository crud){
        return new UrlRepositoryImpl(crud);
    }

}
