package D.demo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import D.demo.models.Localidade;

public interface LocalidadeRepository extends JpaRepository<Localidade, UUID> {
    
}
