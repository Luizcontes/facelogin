package com.entertours.passeio.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entertours.passeio.services.FotoService;

@RestController
@RequestMapping(value = "/images")
public class FotoController {

    @Autowired
    FotoService fotoService;

    @GetMapping(value = "/{UUID}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getFotosByUUID(@PathVariable("UUID") String uuid) {

        return fotoService.getFotoByUUID(uuid);
    }

    @PostMapping(value = "/{UUID}")
    public List<String> saveImage(
            @RequestParam("images") MultipartFile[] images,
            @PathVariable("UUID") String passeioId) throws IOException {

        return fotoService.save(images, passeioId);
    }

    // @GetMapping(value = "/teste/{UUID}")
    // public ResponseEntity<?> teste(@PathVariable("UUID") String uuid) throws
    // IOException{

    // ByteArrayResource fotoResource = getFotosByUUID(uuid);

    // HttpHeaders header = new HttpHeaders();
    // // header.setContentType(MediaType.IMAGE_JPEG);
    // header.set("Content-Disposition", "attachment; filename=teste.jpg");

    // // MultiValueMap header = new LinkedMultiValueMap<String, String>();
    // // header.add("accept-ranges", "bytes");

    // byte[] foto = fotoResource.getByteArray();

    // return ResponseEntity.ok()
    // .contentLength(fotoResource.contentLength())
    // .header(HttpHeaders.CONTENT_DISPOSITION,
    // ContentDisposition.attachment()
    // .filename("Teste.jpg")
    // .build().toString())
    // .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
    // .body(foto);
    // }

    @GetMapping("/teste")
    public String teste(RequestEntity<?> request, MultipartFile[] images) {

        System.out.println(request.getBody());

        // filename.substring(filename.lastIndexOf(".") + 1);

        // multipartFile.getOriginalFilename().split("\\.")[1]

        for (MultipartFile image : images) {
            
            System.out.println(image.getContentType());
        }

        return "ok";
    }
}
