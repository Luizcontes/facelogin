package D.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import D.demo.models.Foto;
import D.demo.models.Passeio;
import D.demo.repositories.FotoRepository;

@Service
public class FotoService {
    
    @Autowired
    FotoRepository fotoRepository;

    @Autowired
    PasseioService passeioService;

    public List<Foto> getAll() {
        return fotoRepository.findAll();
    }

    public String save(MultipartFile[] images, String passeioId) throws IOException{

        Optional<Passeio> passeio = passeioService.getPasseio(passeioId);

        if (passeio.isEmpty()) {
            throw new NoSuchElementException("Record not found for passeio id in the database");
        }

        for (MultipartFile image : images) {
            Foto foto = new Foto(image.getBytes(), passeio.get());
            fotoRepository.save(foto);
        }

        passeioService.save(passeio.get());
        
        return "blablbla";
    }

    // @ResponseBody
    // public void getImage(UUID id,
    //         HttpServletResponse response,
    //         Optional<Passeio> passeio) throws ServletException, IOException {

    //     passeio = fotoRepository.findById(id);
    //     response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
    //     // response.getOutputStream().write(passeio.get().getImage());
    //     response.getOutputStream().close();
    // }
    
}
