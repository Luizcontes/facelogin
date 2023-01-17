package D.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import D.demo.models.Categoria;
import D.demo.services.CategoriaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> findAllCategoria() {

        return categoriaService.findAll();
    }

    @PostMapping
    public Categoria saveCategoria(@RequestBody Categoria categoria) {
        
        return categoriaService.save(categoria);
    }
    
    
}
