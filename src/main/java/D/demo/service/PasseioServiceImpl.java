package D.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import D.demo.model.Passeio;
import D.demo.repository.PasseioRepository;

@Service
@Transactional
public class PasseioServiceImpl implements PasseioService {

    @Autowired
    private PasseioRepository passeioRepository;

    @Override
    public void savePasseio(Passeio passeio) {
        passeioRepository.save(passeio);
    }

    @Override
    public List<Passeio> getAllPasseios() {
        return passeioRepository.findAll();
    }

    @Override
    public Optional<Passeio> getPasseioById(Long id) {
        return passeioRepository.findById(id);
    }

}
