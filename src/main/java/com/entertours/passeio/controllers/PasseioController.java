package com.entertours.passeio.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entertours.passeio.models.Passeio;
import com.entertours.passeio.services.PasseioService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/passeio")
public class PasseioController {

    final Logger logger = LoggerFactory.getLogger(PasseioController.class);

    @Autowired
    PasseioService passeioService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<?> getPasseios() {

        return passeioService.getAll();
    }

    @GetMapping("/{UUID}")
    public Optional<Passeio> getPasseioById(@PathVariable("UUID") String id) {

        return passeioService.getPasseio(id);
    }

    @PostMapping
    public Passeio save(
            @RequestParam String nome,
            @RequestParam String valor,
            @RequestParam String local,
            @RequestParam String duracao,
            @RequestParam String descricao,
            @RequestParam String categoria,
            MultipartFile[] images) throws Exception {

        return passeioService.save(nome, valor, local, duracao, descricao, categoria, images);
    }
}