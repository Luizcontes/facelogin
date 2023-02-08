package com.entertours.passeio.repositories;

import java.util.List;
import java.util.UUID;

import com.entertours.passeio.models.Summary;

public interface SummaryRepository extends ReadOnlyRepository<Summary, UUID>{
    
    List<Summary> findAll();

    Summary findFirstById(UUID uuid);
}
