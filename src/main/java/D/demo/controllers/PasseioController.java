package D.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import D.demo.models.Passeio;
import D.demo.models.Summary;
import D.demo.services.PasseioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/passeio")
@CrossOrigin(origins = "*")
public class PasseioController {

    @Autowired
    PasseioService passeioService;

    @GetMapping
    public List<Summary> getPasseios() {
        return passeioService.getAll();
    }

    // @GetMapping("/{UUID}")
    // public Summary getByTour(@PathVariable("UUID") String uuid) {

    //     return passeioService.getSummaryByTour(uuid);
    // }

    @GetMapping("/{UUID}")
    public Optional<Passeio> getPasseioById(@PathVariable("UUID") String id) {

        return passeioService.getPasseio(id);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Passeio> savePasseio(@RequestBody ObjectNode passeio) {

        Optional<ObjectNode> opt = Optional.of(passeio);

        return new ResponseEntity<Passeio>(passeioService.save(opt.get()), HttpStatus.OK);
    }
}