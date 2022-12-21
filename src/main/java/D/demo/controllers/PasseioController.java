package D.demo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import D.demo.models.Passeio;
import D.demo.models.PasseioDTO;
import D.demo.repositories.PasseioRepository;
import D.demo.services.PasseioService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins = "*")
public class PasseioController {

    @Value("/resources")
    private String uploadFolder;

    @Autowired
    PasseioRepository passeioRepository;

    @Autowired
    PasseioService passeioService;

    @PostMapping(value = "/save")
    public @ResponseBody ResponseEntity<?> create(@RequestParam("name") String name,
            @RequestParam("price") double price, @RequestParam("local") String local,
            final @RequestParam("image") MultipartFile file, Model model, HttpServletRequest request) {

        return passeioService.creatTour(name, price, local, file, request);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    void returnImage(@PathVariable("id") UUID id,
            HttpServletResponse response,
            Optional<Passeio> passeio) throws ServletException, IOException {

        passeioService.getImage(id, response, passeio);
    }

    @GetMapping("/get")
    List<PasseioDTO> getAllInfo() {
        return passeioService.getAllUserInfo();
    }
}