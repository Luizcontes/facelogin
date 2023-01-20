package D.demo.repositories;

import java.util.List;
import java.util.UUID;

import D.demo.models.Summary;

public interface SummaryRepository extends ReadOnlyRepository<Summary, UUID>{
    
    List<Summary> findAll();

    Summary findFirstById(String uuid);
}
