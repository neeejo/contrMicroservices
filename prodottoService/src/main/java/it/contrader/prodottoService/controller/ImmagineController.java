package it.contrader.prodottoService.controller;


import it.contrader.prodottoService.dto.ImmagineDTO;
import it.contrader.prodottoService.model.Immagine;
import it.contrader.prodottoService.service.ImmagineService;
import it.contrader.prodottoService.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/immagine")
public class ImmagineController {

    @Autowired
    private ImmagineService service;

    @Autowired
    private ProdottoService prodottoService;

    @DeleteMapping("/deleteImmagineProdotto")
    public void deleteImg(@RequestParam("idimmagine") long idimmagine) {
        service.delete(idimmagine);
    }

    @PostMapping(value = "/uploadImmagineProdotto")
    public Immagine uploadImmagineProdotto(@RequestPart MultipartFile picBytes, @RequestParam Long idp) throws IOException {
        ImmagineDTO iDTO = new ImmagineDTO();
        iDTO.setIdprodotto(prodottoService.read(idp));
        iDTO.setPicBytes(picBytes.getBytes());
        return service.uploadImmagine(iDTO);
    }

    @PutMapping(value = "/updateImmagineProdotto")
    public Immagine updateImmagineProdotto(@RequestPart MultipartFile picBytes, @RequestParam Long idp, @RequestParam Long idimg) throws IOException {
        ImmagineDTO iDTO = new ImmagineDTO();
        iDTO.setIdimmagine(idimg);
        iDTO.setIdprodotto(prodottoService.read(idp));
        iDTO.setPicBytes(picBytes.getBytes());
        System.out.println(iDTO);
        return service.update(iDTO);
    }


    @GetMapping(value = "/getImmagineProdotto", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Immagine> getImmagineProdotto(@RequestParam Long idp){
        return service.getImmagineProdotto(idp);
    }

}
