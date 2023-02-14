package com.entertours.passeio.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.entertours.passeio.models.Categoria;
import com.entertours.passeio.models.Foto;
import com.entertours.passeio.models.Localidade;
import com.entertours.passeio.models.Passeio;
import com.entertours.passeio.repositories.PasseioRepository;
import com.entertours.passeio.repositories.SummaryRepository;

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
/* 
    @Autowired
    CalendarFeignService calendarFeignService; */

    @Lazy
    @Autowired
    FotoService fotoService;

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(summaryRepositoriy.findAll()); 
    }

    public Optional<Passeio> getPasseio(String id) {

        return passeioRepository.findById(UUID.fromString(id));
    }

    public Passeio save(Passeio passeio) {
        return passeioRepository.save(passeio);
    }

    // public Passeio save(ObjectNode passeio) {

    // Optional<Categoria> optCategoria =
    // categoriaService.findById(UUID.fromString(passeio
    // .findValue("categoria").asText()));

    // if (optCategoria.isEmpty()) {
    // throw new NoSuchElementException("Record not found for categoria id in the
    // database");
    // }
    // Categoria categoria = optCategoria.get();

    // Optional<Localidade> optLocalidade =
    // localidadeService.findById(UUID.fromString(passeio
    // .findValue("local").asText()));

    // if (optLocalidade.isEmpty()) {
    // throw new NoSuchElementException("Record not found for localidade id in the
    // database");
    // }
    // Localidade localidade = optLocalidade.get();

    // Passeio novoPasseio = new Passeio();

    // novoPasseio.setNome(passeio.findValue("nome").asText());
    // novoPasseio.setValor(passeio.findValue("valor").asDouble());
    // novoPasseio.setDuracao(passeio.findValue("duracao").asInt());
    // novoPasseio.setDescricao(passeio.findValue("descricao").asText());

    // novoPasseio.setCategoria(categoria);
    // categoriaService.save(categoria);

    // novoPasseio.setLocal(localidade);
    // localidadeService.save(localidade);

    // return passeioRepository.save(novoPasseio);
    // }

    public Passeio save(String nome, String valor, String local, String duracao, String descricao, String categoria,
            MultipartFile[] images) throws Exception, IOException {

        Optional<Categoria> optCategoria = categoriaService.findById(categoria);

        if (optCategoria.isEmpty()) {
            throw new NoSuchElementException("Record not found for categoria id in the database");
        }
        
        Categoria catTemp = optCategoria.get();

        Optional<Localidade> optLocalidade = localidadeService.findById(local);

        if (optLocalidade.isEmpty()) {
            throw new NoSuchElementException("Record not found for localidade id in the database");
        }
        Localidade localTemp = optLocalidade.get();

        Passeio novoPasseio = new Passeio();

        novoPasseio.setNome(nome);
        novoPasseio.setValor(Double.parseDouble(valor));
        novoPasseio.setDuracao(Integer.parseInt(duracao));
        novoPasseio.setDescricao(descricao);

        novoPasseio.setCategoria(catTemp);
        categoriaService.save(catTemp);

        novoPasseio.setLocal(localTemp);
        localidadeService.save(localTemp);

        // try {
        //     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) images;

        //     Set set = multipartRequest.getFileMap().entrySet();
        //     Iterator i = set.iterator();
        //     while (i.hasNext()) {
        //         Map.Entry me = (Map.Entry) i.next();
        //         // String fileName = (String) me.getKey() + "_" + System.currentTimeMillis();
        //         MultipartFile multipartFile = (MultipartFile) me.getValue();
        //         System.out.println("Original fileName - " + multipartFile.getOriginalFilename());
        //         System.out.println(multipartFile.getSize());
        //         // System.out.println("fileName - " + fileName);
        //         // saveImage(fileName, multipartFile);
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        List<Foto> fotos = new ArrayList<>();
        Arrays.stream(images).forEach(i -> {
            Foto foto = new Foto();
            
            foto.setName(i.getOriginalFilename());
            try {
                foto.setImage(i.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            foto.setPasseio(novoPasseio); 

            fotos.add(foto);

        });
        System.out.println(fotos.size());
        
        Passeio res = passeioRepository.save(novoPasseio);

        fotoService.save(fotos);
        
        novoPasseio.setFoto(fotos);


        /* String mockBookingString = "{\"day\":[{\"week\":4,\"hours\":[\"10:00:00\",\"11:00:00\"]},{\"week\":6,\"hours\":[\"09:00:00\",\"10:30:00\"]}]}";

        ObjectNode bookingRules = new ObjectMapper().readValue(mockBookingString, ObjectNode.class);
        
        calendarFeignService.createPasseioRule(res.getId().toString(), bookingRules); */
        
        return res;
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
