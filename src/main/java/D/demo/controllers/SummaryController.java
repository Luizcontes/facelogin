package D.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import D.demo.models.Summary;
import D.demo.repositories.SummaryRepository;

@RestController
@RequestMapping("/fotoslist")
public class SummaryController {
    
    @Autowired
    SummaryRepository summaryRepositorio;

    @GetMapping("/{UUID}")
    public Summary getByTour(@PathVariable("UUID") String uuid) {

        Summary summary = summaryRepositorio.findFirstById(uuid);

        return summary;
    }
}
