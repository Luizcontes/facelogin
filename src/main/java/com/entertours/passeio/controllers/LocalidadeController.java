package com.entertours.passeio.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entertours.passeio.models.Localidade;
import com.entertours.passeio.services.LocalidadeService;

@RestController
@RequestMapping(value = "/localidade")
public class LocalidadeController {

    @Autowired
    LocalidadeService localidadeService;
    
    @GetMapping
    public ResponseEntity<?> findAllLocalidade() {

        return localidadeService.findAll();
    }

    @GetMapping("/image/{UUID}") 
    ResponseEntity<?> getLocalImage(@PathVariable String UUID) {

        return localidadeService.getFotoByUUID(UUID);
    }

    @PostMapping
    public Localidade saveLocalidade(
        @RequestParam("localidade") String localidade,
        @RequestParam("imagem") MultipartFile imagem)  throws IOException {

        return localidadeService.save(imagem, localidade);
    }
    
}
