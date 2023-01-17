package D.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import D.demo.models.Localidade;
import D.demo.repositories.LocalidadeRepository;

@Service
public class LocalidadeService {

    @Autowired
    LocalidadeRepository localidadeRepository;

    public List<Localidade> findAll() {

        return localidadeRepository.findAll();
    }

    public Localidade save(Localidade localidade) {

        return localidadeRepository.save(localidade);
    }

    public Optional<Localidade> findById(UUID id) {

        return localidadeRepository.findById(id);
    }
    
}
