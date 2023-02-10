package com.entertours.passeio.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

import com.entertours.passeio.models.Foto;
import com.entertours.passeio.models.Passeio;
import com.entertours.passeio.repositories.FotoRepository;

@Service
public class FotoService {
    
    @Autowired
    FotoRepository fotoRepository;

    @Autowired
    PasseioService passeioService;

    public ResponseEntity<?> getFotoByUUID(String uuid) {

        Optional<Foto> opt = fotoRepository.findById(UUID.fromString(uuid));

        if(opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Foto foto = opt.get();

        // return new ByteArrayResource(opt.get().getImage());
        return ResponseEntity.ok()
            .contentLength(foto.getImage().length)
            .header(HttpHeaders.CONTENT_DISPOSITION, 
                        ContentDisposition.attachment()
                            .filename(foto.getName())
                            .build().toString())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
            .header(HttpHeaders.ACCEPT_RANGES, "byte")
            .header(HttpHeaders.CONNECTION, "keep-alive")
            .body(foto.getImage());
    }

    public Foto save(Foto foto) {

        return fotoRepository.save(foto);
    }

    public List<Foto> save(List<Foto> fotos) {

        return fotoRepository.saveAll(fotos);
    }

    public List<String> save(MultipartFile[] images, String passeioId) throws IOException{

        Optional<Passeio> passeio = passeioService.getPasseio(passeioId);
        List<String> imagesName = new ArrayList<>();

        if (passeio.isEmpty()) {
            throw new NoSuchElementException("Record not found for passeio id in the database");
        }

        for (MultipartFile image : images) {
            Foto foto = new Foto();

            imagesName.add(image.getOriginalFilename());

            System.out.println(image.getContentType());

            foto.setName(image.getOriginalFilename());
            foto.setImage(image.getBytes());
            foto.setPasseio(passeio.get());
            fotoRepository.save(foto);
        }

        passeioService.save(passeio.get());
        
        return imagesName;
    }
}
