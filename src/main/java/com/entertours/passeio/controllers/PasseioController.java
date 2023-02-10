package com.entertours.passeio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entertours.passeio.models.Passeio;
import com.entertours.passeio.models.Summary;
import com.entertours.passeio.services.PasseioService;


import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/passeio")
public class PasseioController {

    @Autowired
    PasseioService passeioService;

    @GetMapping
    public ResponseEntity<?> getPasseios() {

        // int dataSize = 1024 * 1024;
        
        // System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / dataSize + "MB");
        // System.out.println(Runtime.getRuntime().freeMemory() / dataSize + "MB");
        // System.out.println(Runtime.getRuntime().totalMemory() / dataSize + "MB");
        // System.out.println(Runtime.getRuntime().maxMemory() / dataSize + "MB");

        return passeioService.getAll();
    }

    // @GetMapping("/{UUID}")
    // public Summary getByTour(@PathVariable("UUID") String uuid) {

    // return passeioService.getSummaryByTour(uuid);
    // }

    @GetMapping("/{UUID}")
    public Optional<Passeio> getPasseioById(@PathVariable("UUID") String id) {

        return passeioService.getPasseio(id);
    }

    // @PostMapping
    // public @ResponseBody ResponseEntity<Passeio> savePasseio(@RequestBody ObjectNode passeio) {

    //     Optional<ObjectNode> opt = Optional.of(passeio);

    //     return new ResponseEntity<Passeio>(passeioService.save(opt.get()), HttpStatus.OK);
    // }

    @PostMapping
    public Passeio save(
        @RequestParam String nome,
        @RequestParam String valor,
        @RequestParam String local,
        @RequestParam String duracao,
        @RequestParam String descricao,
        @RequestParam String categoria,
        MultipartFile[] images) throws Exception{

            
            return passeioService.save(nome, valor, local, duracao, descricao, categoria, images);
            
        }
}