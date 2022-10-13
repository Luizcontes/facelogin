package D.demo.service;

import java.util.List;
import java.util.Optional;

import D.demo.model.Passeio;

public interface PasseioService {
    
    void savePasseio(Passeio passeio);
    List<Passeio> getAllPasseios();
    Optional<Passeio> getPasseioById(Long id);
}
