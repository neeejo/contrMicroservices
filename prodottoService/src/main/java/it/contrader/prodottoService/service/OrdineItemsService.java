package it.contrader.prodottoService.service;

import it.contrader.prodottoService.dto.OrdineDTO;
import it.contrader.prodottoService.dto.OrdineItemsDTO;
import it.contrader.prodottoService.mapper.OrdineItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.contrader.prodottoService.repository.OrdineItemsRepository;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrdineItemsService {


    @Autowired
    OrdineItemsRepository repo;

    @Autowired
    OrdineItemsMapper mapper;

    public OrdineItemsDTO save(OrdineItemsDTO ordineItemsDTO){
        return mapper.toOrdineItemsDTO(repo.save(mapper.toOrdineItems(ordineItemsDTO)));
    }

    public OrdineItemsDTO readOrdineItems(Long idordineitems){
        return mapper.toOrdineItemsDTO(repo.findById(idordineitems).orElseThrow(()->new RuntimeException("errore lettura ordine")));
    }

    public void deleteOrdineItems(Long idordineitems){
        repo.deleteById(idordineitems);
    }

    public List<OrdineItemsDTO> findAllByAnagrafica(Long idanagrafica) {
        return mapper.toDTOList(repo.findAllByUtenteAndOrdineIsNull(idanagrafica));
    }

    public List<OrdineItemsDTO> findAllByAnagraficaUtenteIdAnagraficaAndIdordine(Long idanagrafica, Long idordine) {
        return mapper.toDTOList(repo.findAllByUtenteAndOrdineIdordine(idanagrafica, idordine));
    }

    public OrdineItemsDTO inviaCarrello(OrdineItemsDTO dto){
        dto.setDatetime(LocalDateTime.now());
//        if(dto.getQuantita()==0) dto.setQuantita(1);
        repo.save(mapper.toOrdineItems(dto));
        return dto;
    }
}