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

import D.demo.models.Categoria;
import D.demo.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    public Optional<Categoria> findById(UUID id) {

        return categoriaRepository.findById(id);
    }

    public Categoria save(MultipartFile icon, String catName) throws IOException {

        Categoria categoria = new Categoria();

        System.out.println(icon.getOriginalFilename());

        categoria.setCatName(catName);
        categoria.setIconName(icon.getOriginalFilename());
        categoria.setIcon(icon.getBytes());


        return categoriaRepository.save(categoria);
    }

    public Categoria save(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    public ResponseEntity<?> getIconImg(String uuid) {

        Optional<Categoria> opt = categoriaRepository.findById(UUID.fromString(uuid));

        if(opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Categoria categoria = opt.get();

        // return new ByteArrayResource(opt.get().getImage());
        return ResponseEntity.ok()
            .contentLength(categoria.getIcon().length)
            .header(HttpHeaders.CONTENT_DISPOSITION, 
                        ContentDisposition.attachment()
                            .filename(categoria.getIconName())
                            .build().toString())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
            .header(HttpHeaders.ACCEPT_RANGES, "byte")
            .header(HttpHeaders.CONNECTION, "keep-alive")
            .body(categoria.getIcon());
    }
}
