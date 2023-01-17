package D.demo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import D.demo.models.Foto;
import D.demo.services.FotoService;

@RestController
@RequestMapping(value = "/images")
public class FotoController {

    @Autowired
    FotoService fotoService;

    @GetMapping
    public List<Foto> getFoto() {
        return fotoService.getAll();
    }

    @PostMapping(value = "/{UUID}")
    public String saveImage(
        @RequestParam("images") MultipartFile[] images, 
        @PathVariable("UUID") String passeioId) throws IOException {          

        return fotoService.save(images, passeioId);
    }
    
}
