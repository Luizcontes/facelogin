package D.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import D.demo.models.Passeio;
import D.demo.models.PasseioDTO;

public interface PasseioRepository extends JpaRepository<Passeio, UUID>{
 
    @Query("SELECT new D.demo.models.PasseioDTO(p.id, p.nome, p.local, p.valor) FROM Passeio p")
    List<PasseioDTO> getAllUsersInfo();

}
