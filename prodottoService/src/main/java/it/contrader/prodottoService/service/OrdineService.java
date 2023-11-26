package it.contrader.prodottoService.service;

import it.contrader.prodottoService.dto.AnagraficaDTO;
import it.contrader.prodottoService.dto.ProdottoDTO;

import it.contrader.prodottoService.mapper.ProdottoMapper;
import it.contrader.prodottoService.model.Ordine;
import it.contrader.prodottoService.model.OrdineItems;
import it.contrader.prodottoService.model.Prodotto;
import it.contrader.prodottoService.repository.OrdineItemsRepository;
import it.contrader.prodottoService.service.OrdineItemsService;
import it.contrader.prodottoService.dto.OrdineItemsDTO;
import it.contrader.prodottoService.dto.OrdineDTO;
import it.contrader.prodottoService.mapper.OrdineMapper;
import it.contrader.prodottoService.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private OrdineMapper mapper;

    @Autowired
    private ProdottoMapper prodottoMapper;

    @Autowired
    private OrdineItemsService ordineItemsService;

    @Autowired
    private OrdineItemsRepository ordineItemsRepository;


    @Autowired
    private ProdottoService prodottoService;

    public OrdineDTO save(OrdineDTO ordineDTO){
        return mapper.toOrdineDTO(ordineRepository.save(mapper.toOrdine(ordineDTO)));
    }

    public OrdineDTO readOrdine(Long idordine){
        return mapper.toOrdineDTO(ordineRepository.findById(idordine).orElseThrow(()->new RuntimeException("errore lettura ordine")));
    }

    public void deleteOrdine(Long idordine){
        ordineRepository.deleteById(idordine);
    }

    public List<OrdineDTO> findAllByUtente(Long idAnagrafica) {
        return mapper.tDTOList(ordineRepository.findAllByUtente(idAnagrafica));
    }

    public OrdineDTO acquistaCarrello(Long idAnagrafica) {
        List<OrdineItemsDTO> prodottiCarrello = ordineItemsService.findAllByAnagrafica(idAnagrafica);



        Double sum = 0.0;
        for (OrdineItemsDTO p : prodottiCarrello) {
            sum += p.getPrezzo() * p.getQuantita();
        }
        OrdineDTO insertedOrdine = this.save(new OrdineDTO(idAnagrafica, LocalDateTime.now(), sum));
        for (OrdineItemsDTO p : prodottiCarrello) {
            p.setOrdine(mapper.toOrdine(insertedOrdine));
            ordineItemsService.save(p);
        }

        return insertedOrdine;
    }

    public OrdineDTO acquistaProdotto(Long idProdotto, Long idAnagrafica) {


        ProdottoDTO pDTO= prodottoService.findById(idProdotto);

        System.out.println(pDTO);
        Double prezzoProdotto= pDTO.getPrezzo();

        OrdineDTO ordine=this.save(new OrdineDTO(idAnagrafica, LocalDateTime.now(), prezzoProdotto));
        Prodotto p=prodottoMapper.toProdotto(pDTO);
        Ordine o=mapper.toOrdine(ordine);
        OrdineItemsDTO oiDTO=ordineItemsService.save(new OrdineItemsDTO(o, p, idAnagrafica, 1, prezzoProdotto, LocalDateTime.now()));

        return ordine;
    }

}