package D.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import D.demo.models.Localidade;
import D.demo.services.LocalidadeService;

@RestController
@RequestMapping(value = "/localidade")
public class LocalidadeController {

    @Autowired
    LocalidadeService localidadeService;
    
    @GetMapping
    public List<Localidade> findAllLocalidade() {

        return localidadeService.findAll();
    }

    @PostMapping
    public Localidade saveLocalidade(@RequestBody Localidade localidade) {

        return localidadeService.save(localidade);
    }
    
}
