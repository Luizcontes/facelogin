package D.demo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import D.demo.models.Passeio;

public interface PasseioRepository extends JpaRepository<Passeio, UUID>{
 
}
