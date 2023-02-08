package com.entertours.passeio.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entertours.passeio.models.Categoria;
import com.entertours.passeio.services.CategoriaService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> findAllCategoria() {

        return categoriaService.findAll();
    }

    @GetMapping("/icon/{UUID}")
    public ResponseEntity<?> getIcon(@PathVariable String UUID) {

        return categoriaService.getIconImg(UUID);
    }

    @PostMapping
    public Categoria saveCategoria(
        @RequestParam("categoria") String catName,    
        @RequestParam("image") MultipartFile icon) throws IOException{
        
        return categoriaService.save(icon, catName);
    }
}
