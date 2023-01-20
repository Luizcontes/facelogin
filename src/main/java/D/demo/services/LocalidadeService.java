package D.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import D.demo.models.Localidade;
import D.demo.repositories.LocalidadeRepository;

@Service
public class LocalidadeService {

    @Autowired
    LocalidadeRepository localidadeRepository;

    public List<Localidade> findAll() {

        return localidadeRepository.findAll();
    }

    public Optional<Localidade> findById(UUID id) {

        return localidadeRepository.findById(id);
    }

    public Localidade save(Localidade localidade) {

        return localidadeRepository.save(localidade);
    }

    public Localidade save(MultipartFile image, String localName) throws IOException {

        Localidade localidade = new Localidade();

        System.out.println(image.getOriginalFilename());

        localidade.setLocalImage(image.getBytes());
        localidade.setImgName(image.getOriginalFilename());
        localidade.setLocalidade(localName);

        return localidadeRepository.save(localidade);
    }

    public ResponseEntity<?> getFotoByUUID(String uuid) {

        Optional<Localidade> opt = localidadeRepository.findById(UUID.fromString(uuid));

        if (opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Localidade localidade = opt.get();

        // return new ByteArrayResource(opt.get().getImage());
        return ResponseEntity.ok()
                .contentLength(localidade.getLocalImage().length)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(localidade.getImgName())
                                .build().toString())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.ACCEPT_RANGES, "byte")
                .header(HttpHeaders.CONNECTION, "keep-alive")
                .body(localidade.getLocalImage());
    }

}
