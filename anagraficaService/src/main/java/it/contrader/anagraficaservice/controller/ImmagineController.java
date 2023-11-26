package it.contrader.anagraficaservice.controller;

import it.contrader.anagraficaservice.dto.AnagraficaDTO;
import it.contrader.anagraficaservice.dto.ImmagineDTO;
import it.contrader.anagraficaservice.mapper.AnagraficaMapper;
import it.contrader.anagraficaservice.mapper.ImmagineMapper;
import it.contrader.anagraficaservice.model.Immagine;
import it.contrader.anagraficaservice.repository.ImmagineRepository;
import it.contrader.anagraficaservice.service.AnagraficaService;
import it.contrader.anagraficaservice.service.ImmagineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/immagineuser")
public class ImmagineController {
    @Autowired
    private ImmagineService service;

    @Autowired
    private ImmagineRepository repo;

    @Autowired
    private ImmagineMapper mapper;

    @Autowired
    private AnagraficaService aservice;

    @Autowired
    private AnagraficaMapper amapper;

    @GetMapping("/getimg")
    public ImmagineDTO read (@RequestParam Long id) { return service.read(id);}

    @DeleteMapping("/delimg")
    public void delete(@RequestParam Long id) {service.delete(id);}

    @PutMapping("/insertimg")
    public ImmagineDTO insert(@RequestPart MultipartFile picBytes, @RequestParam Long iduser) throws IOException {


        ImmagineDTO immagineDTO = new ImmagineDTO();
        immagineDTO.setAnagrafica(aservice.findByIduser(iduser));
        immagineDTO.setPicByte(picBytes.getBytes());

        AnagraficaDTO a=immagineDTO.getAnagrafica();


        List<Immagine> findAll= repo.findAllByAnagrafica(amapper.toAnagrafica(a));

        if(!findAll.isEmpty()) return mapper.toImmagineDTO(service.uploadImmagine(immagineDTO));

        else return service.save(immagineDTO);
    }
    @GetMapping("/getimgByAnag")
    public ImmagineDTO readAnag (@RequestParam Long id) { return service.readAnag(id);}


    @PutMapping("/updateimg")
    public ImmagineDTO update(@RequestPart MultipartFile picBytes, @RequestParam Long id) throws IOException{
        ImmagineDTO immagineDTO = new ImmagineDTO();
        immagineDTO.setPicByte(picBytes.getBytes());
        immagineDTO.setId(id);
        return service.save(immagineDTO);
    }


}
