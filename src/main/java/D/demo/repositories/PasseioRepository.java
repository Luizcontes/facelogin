package D.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import D.demo.models.Passeio;
import D.demo.modelsDTO.PasseioDTO;

public interface PasseioRepository extends JpaRepository<Passeio, UUID>{
 
    @Query("SELECT new D.demo.modelsDTO.PasseioDTO(p.id, p.nome, p.local, p.valor) FROM Passeio p WHERE p.id = :id")
    PasseioDTO findByUUID(UUID id);

    // @Query("SELECT new D.demo.modelsDTO.PasseioDTO(p.id, p.nome, p.local, p.valor) FROM Passeio p")
    // List<PasseioDTO> getAllDTO();

    @Query("SELECT new D.demo.modelsDTO.PasseioDTO(p.id, p.nome, p.local, p.valor) FROM Passeio p")
    List<PasseioDTO> getAllDTO();

    // @Query("SELECT new D.demo.models.PasseioDTO(p.id, p.nome, p.local, p.valor) FROM Passeio p where p.local = :local")
    // List<PasseioDTO> findByLocal(String local);

}
