package D.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import D.demo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
    Produto findById(long id);
}
