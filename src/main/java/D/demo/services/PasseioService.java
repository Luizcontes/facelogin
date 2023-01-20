package D.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;

import D.demo.models.Categoria;
import D.demo.models.Localidade;
import D.demo.models.Passeio;
import D.demo.models.Summary;
import D.demo.repositories.PasseioRepository;
import D.demo.repositories.SummaryRepository;

@Service
public class PasseioService {

    @Autowired
    PasseioRepository passeioRepository;

    @Autowired
    SummaryRepository summaryRepositoriy;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    LocalidadeService localidadeService;

    public List<Summary> getAll() {
        return summaryRepositoriy.findAll();
    }

    // public Summary getSummaryByTour(@PathVariable("UUID") String uuid) {

    // return summaryRepositoriy.findFirstById(uuid);
    // }

    public Optional<Passeio> getPasseio(String id) {

        return passeioRepository.findById(UUID.fromString(id));
    }

    public Passeio save(Passeio passeio) {
        return passeioRepository.save(passeio);
    }

    public Passeio save(ObjectNode passeio) {

        Optional<Categoria> optCategoria = categoriaService.findById(UUID.fromString(passeio
                .findValue("categoria").asText()));

        if (optCategoria.isEmpty()) {
            throw new NoSuchElementException("Record not found for categoria id in the database");
        }
        Categoria categoria = optCategoria.get();

        Optional<Localidade> optLocalidade = localidadeService.findById(UUID.fromString(passeio
                .findValue("local").asText()));

        if (optLocalidade.isEmpty()) {
            throw new NoSuchElementException("Record not found for localidade id in the database");
        }
        Localidade localidade = optLocalidade.get();

        Passeio novoPasseio = new Passeio();

        novoPasseio.setNome(passeio.findValue("nome").asText());
        novoPasseio.setValor(passeio.findValue("valor").asDouble());
        novoPasseio.setDuracao(passeio.findValue("duracao").asInt());
        novoPasseio.setDescricao(passeio.findValue("descricao").asText());

        novoPasseio.setCategoria(categoria);
        categoriaService.save(categoria);

        novoPasseio.setLocal(localidade);
        localidadeService.save(localidade);

        return passeioRepository.save(novoPasseio);
    }

    // public @ResponseBody ResponseEntity<?> creatTour(String name,
    // double price, String local, MultipartFile file, HttpServletRequest request) {

    // try {
    // byte[] imageData = file.getBytes();
    // Passeio passeio = new Passeio();
    // passeio.setNome(name);
    // // passeio.setImage(imageData);
    // passeio.setValor(price);
    // // passeio.setLocal(local);
    // passeioRepository.save(passeio);

    // return new ResponseEntity<>(HttpStatus.OK);
    // } catch (Exception e) {
    // e.printStackTrace();

    // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }
    // }

    // @ResponseBody
    // public void getImage(UUID id,
    // HttpServletResponse response,
    // Optional<Passeio> passeio) throws ServletException, IOException {

    // passeio = passeioRepository.findById(id);
    // response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
    // // response.getOutputStream().write(passeio.get().getImage());
    // response.getOutputStream().close();
    // }
}
