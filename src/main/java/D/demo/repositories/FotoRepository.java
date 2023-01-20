package D.demo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import D.demo.models.Foto;

public interface FotoRepository extends JpaRepository<Foto, UUID>{

}
