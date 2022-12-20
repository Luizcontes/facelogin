package D.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import D.demo.models.Passeio;
import D.demo.models.PasseioDTO;
import D.demo.repository.PasseioRepository;

@Service
public class PasseioService {

    @Autowired
    PasseioRepository passeioRepository;

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
