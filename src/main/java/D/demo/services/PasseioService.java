package D.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import D.demo.models.Passeio;
import D.demo.models.PasseioDTO;
import D.demo.repositories.PasseioRepository;

@Service
public class PasseioService {

    @Autowired
    PasseioRepository passeioRepository;

    public @ResponseBody ResponseEntity<?> creatTour(String name,
            double price, String local, MultipartFile file, HttpServletRequest request) {

        try {
            byte[] imageData = file.getBytes();
            Passeio passeio = new Passeio();
            passeio.setNome(name);
            passeio.setImage(imageData);
            passeio.setValor(price);
            passeio.setLocal(local);
            passeioRepository.save(passeio);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<PasseioDTO> getAllUserInfo() {
        return passeioRepository.getAllUsersInfo();
    }

    @ResponseBody
    public void getImage(UUID id,
            HttpServletResponse response,
            Optional<Passeio> passeio) throws ServletException, IOException {

        passeio = passeioRepository.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(passeio.get().getImage());
        response.getOutputStream().close();
    }

}
