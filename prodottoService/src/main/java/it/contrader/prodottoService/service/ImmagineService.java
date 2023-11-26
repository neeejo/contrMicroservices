package it.contrader.prodottoService.service;

import it.contrader.prodottoService.mapper.ImmagineMapper;
import it.contrader.prodottoService.mapper.ProdottoMapper;
import it.contrader.prodottoService.repository.ImmagineRepository;
import it.contrader.prodottoService.model.Immagine;
import it.contrader.prodottoService.dto.ImmagineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImmagineService{
    @Autowired
    private ImmagineRepository immagineRepository;

    @Autowired
    private ImmagineMapper immagineMapper;

    @Autowired
    private ProdottoMapper prodottoMapper;

    public Immagine uploadImmagine(ImmagineDTO immagineDTO) throws IOException {

        Immagine immagine = new Immagine();
        immagine.setPicByte(immagineDTO.getPicBytes());
        immagine.setProdotto(prodottoMapper.toProdotto(immagineDTO.getIdprodotto()));
        immagineRepository.save(immagine);
        return immagine;

    }

    public Immagine update(ImmagineDTO immagineDTO){
        Immagine immagine = new Immagine();
        immagine.setId(immagineDTO.getIdimmagine());
        immagine.setPicByte(immagineDTO.getPicBytes());
        immagine.setProdotto(prodottoMapper.toProdotto(immagineDTO.getIdprodotto()));
        return immagineRepository.save(immagine);
    }

    public Optional<Immagine> findByIdimmagine(long id){
        return immagineRepository.findById(id);
    }


    public List<Immagine> getImmagineProdotto(Long id) {
        return immagineRepository.findAllByProdottoIdprodotto(id);
    }



    public void delete(Long id){
        immagineRepository.deleteById(id);
    }
}
