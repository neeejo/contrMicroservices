package it.contrader.anagraficaservice.controller;

import it.contrader.anagraficaservice.dto.AnagraficaDTO;
import it.contrader.anagraficaservice.dto.ProfileListDTO;
import it.contrader.anagraficaservice.mapper.AnagraficaMapper;
import it.contrader.anagraficaservice.model.Anagrafica;
import it.contrader.anagraficaservice.repository.AnagraficaRepository;
import it.contrader.anagraficaservice.service.AnagraficaService;
import it.contrader.anagraficaservice.service.NegozioService;
import it.contrader.anagraficaservice.dto.UserDTO;
import it.contrader.anagraficaservice.dto.ProfileDTO;
import it.contrader.anagraficaservice.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anag")
public class AnagraficaController {

    @Autowired
    private NegozioService nService;

    @Autowired
    private AnagraficaRepository repo;

    @Autowired
    private AnagraficaMapper mapper;

    @Autowired
    private AnagraficaService service;

    @PostMapping("/registerAnagrafica")
    public AnagraficaDTO register(@RequestBody AnagraficaDTO anagraficaDTO) {
        return service.save(anagraficaDTO);
    }

    @GetMapping ("/findByIduser")
    public AnagraficaDTO findByIduser(Long id) { return service.findByIduser(id); }

    @PutMapping("/updateAnagrafica")
    public AnagraficaDTO updateAnagrafica(@RequestBody AnagraficaDTO anagraficaDTO){
        return service.updateAnagrafica(anagraficaDTO);

    }

    @DeleteMapping("/deleteAnagrafica")
    public void deleteAnagrafica(@RequestParam Long idanagrafica) {
        service.deleteAnagrafica(idanagrafica);
    }

    @GetMapping("/readAnagrafica")
    public AnagraficaDTO readAnagrafica(@RequestParam Long idanagrafica){
        return service.readAnagrafica(idanagrafica);
    }

    @GetMapping("/getAllPaginata")
    public ResponseEntity<Page<Anagrafica>> getall(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber ) {
        return new ResponseEntity<>(service.getAllPaginata(PageRequest.of(pageNumber, pageSize)), HttpStatus.OK);
    }

    @PostMapping("/getAllProfiles")
    public ProfileListDTO profilesList(@RequestBody List<UserDTO> userDTOList) {
        return service.getAllProfile(userDTOList);
    }

    @PostMapping("/getProfile")
    ProfileDTO getProfile(@RequestBody UserDTO userProfileDTO){
        return service.getProfile(userProfileDTO);
    };

}
