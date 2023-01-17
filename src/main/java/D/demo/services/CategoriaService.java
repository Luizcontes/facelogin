package D.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import D.demo.models.Categoria;
import D.demo.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(UUID id) {

        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }
}
