package com.entertours.passeio.repositories;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.entertours.passeio.models.Summary;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {
    
    List<T> findAll();

    Summary findFirstById(String uuid);
}
