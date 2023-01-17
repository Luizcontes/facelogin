package D.demo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.node.ObjectNode;

import D.demo.models.Passeio;
import D.demo.modelsDTO.PasseioDTO;
import D.demo.repositories.PasseioRepository;
import D.demo.services.PasseioService;
import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/passeio")
@CrossOrigin(origins = "*")
public class PasseioController {

    @Value("/resources")
    private String uploadFolder;

    @Autowired
    PasseioRepository passeioRepository;

    @Autowired
    PasseioService passeioService;

    // @PostMapping("/oldsavepasseio")
    // public @ResponseBody ResponseEntity<?> create(@RequestParam("name") String
    // name,
    // @RequestParam("price") double price, @RequestParam("local") String local,
    // final @RequestParam("image") MultipartFile file, Model model,
    // HttpServletRequest request) {

    // return passeioService.creatTour(name, price, local, file, request);
    // }

    @GetMapping
    public List<Passeio> getPasseios() {
        return passeioService.getAll();
    }

    @GetMapping(value = "/all")
    public List<PasseioDTO> getPasseiosDTO() {
        return passeioService.getAllDTO();
    }

    @GetMapping(value = "/{UUID}") 
    public Optional<Passeio> getPasseioById(@RequestParam("UUID") String id){

        return passeioService.getPasseio(id);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Passeio> savePasseio(@RequestBody ObjectNode passeio) {

        Optional<ObjectNode> opt = Optional.of(passeio);

        return new ResponseEntity<Passeio>(passeioService.save(opt.get()), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    void returnImage(@PathVariable("id") UUID id,
            HttpServletResponse response,
            Optional<Passeio> passeio) throws ServletException, IOException {

        passeioService.getImage(id, response, passeio);
    }

    // @GetMapping("/local/{local}")
    // public ResponseEntity<List<PasseioDTO>> getByLocal(@PathVariable("local")
    // String local) {

    // return ResponseEntity.ok(passeioRepository.findByLocal(local));
    // }
}