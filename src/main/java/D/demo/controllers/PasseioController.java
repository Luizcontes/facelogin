package D.demo.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import D.demo.models.Passeio;
import D.demo.models.PasseioDTO;
import D.demo.repository.PasseioRepository;
import D.demo.service.PasseioService;

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
    public @ResponseBody ResponseEntity<?> createEmpoyee(@RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("local") String local,
            final @RequestParam("image") MultipartFile file,
            Model model,
            HttpServletRequest request) {

        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence\" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName,
                        HttpStatus.BAD_REQUEST);
            }
            String[] names = name.split(",");

            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            Passeio passeio = new Passeio();
            passeio.setNome(names[0]);
            passeio.setImage(imageData);
            passeio.setValor(price);
            passeio.setLocal(local);
            passeioRepository.save(passeio);

            return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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