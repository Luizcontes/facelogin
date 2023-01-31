package com.entertours.passeio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entertours.passeio.models.Summary;
import com.entertours.passeio.repositories.SummaryRepository;

@RestController
@RequestMapping("/fotoslist")
// @CrossOrigin(origins = "*")
public class SummaryController {
    
    @Autowired
    SummaryRepository summaryRepositorio;

    @GetMapping("/{UUID}")
    public Summary getByTour(@PathVariable("UUID") String uuid) {

        Summary summary = summaryRepositorio.findFirstById(uuid);

        return summary;
    }
}
