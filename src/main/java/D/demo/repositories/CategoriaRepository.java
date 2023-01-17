package D.demo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import D.demo.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    
}
