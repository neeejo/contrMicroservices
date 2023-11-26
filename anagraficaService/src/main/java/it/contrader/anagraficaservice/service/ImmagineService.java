package it.contrader.anagraficaservice.service;

import it.contrader.anagraficaservice.dto.ImmagineDTO;
import it.contrader.anagraficaservice.mapper.AnagraficaMapper;
import it.contrader.anagraficaservice.mapper.ImmagineMapper;
import it.contrader.anagraficaservice.model.Anagrafica;
import it.contrader.anagraficaservice.model.Immagine;
import it.contrader.anagraficaservice.repository.ImmagineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImmagineService {

    @Autowired
    private ImmagineRepository repository;

    @Autowired
    private ImmagineMapper mapper;
    @Autowired
    private AnagraficaMapper amapper;

    @Autowired
    private AnagraficaService anagraficaService;

    public ImmagineDTO read(Long id) {
        Optional<Immagine> immagine = repository.findById(id);
        if (immagine.isPresent()){
            return mapper.toImmagineDTO(immagine.orElseThrow(() -> new RuntimeException("")));
        } else {
            return new ImmagineDTO();
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ImmagineDTO save(ImmagineDTO immagineDTO) {
        return mapper.toImmagineDTO(repository.save(mapper.toImmagine(immagineDTO)));
    }

    public ImmagineDTO findByAnagrafica(Long id){
        return mapper.toImmagineDTO(repository.findByAnagraficaId(id));
    }
    public ImmagineDTO readAnag(Long id) {
        Immagine immagine = repository.findByAnagrafica(amapper.toAnagrafica(anagraficaService.readAnagrafica(id)));
        if (immagine!=null){
            return mapper.toImmagineDTO(immagine);
        } else {
            return new ImmagineDTO();
        }
    }
    public Immagine uploadImmagine(ImmagineDTO immagineDTO) throws IOException {
        // Cerca l'immagine esistente per l'anagrafica specificata
        Anagrafica anagrafica = amapper.toAnagrafica(immagineDTO.getAnagrafica());
        Immagine existingImmagine = repository.findByAnagrafica(anagrafica);

        if (existingImmagine != null) {
            // Se esiste già un'immagine, aggiorna i dati
            existingImmagine.setPicByte(immagineDTO.getPicByte());
            return repository.save(existingImmagine);
        } else {
            // Se non esiste, crea una nuova immagine
            Immagine newImmagine = new Immagine();
            newImmagine.setPicByte(immagineDTO.getPicByte());
            newImmagine.setAnagrafica(anagrafica);
            return repository.save(newImmagine);
        }
    }




//    public Immagine uploadImmagine(ImmagineDTO immagineDTO) throws IOException {
//
//        Immagine immagine = new Immagine();
//        immagine.setId(immagineDTO.getId());
//        immagine.setPicByte(immagineDTO.getPicByte());
//        immagine.setAnagrafica(amapper.toAnagrafica(immagineDTO.getAnagrafica()));
//        repository.save(immagine);
//
//        return immagine;
//
//    }

}
