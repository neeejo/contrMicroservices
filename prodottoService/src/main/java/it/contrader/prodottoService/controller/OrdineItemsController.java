package it.contrader.prodottoService.controller;

import it.contrader.prodottoService.dto.OrdineItemsDTO;
import it.contrader.prodottoService.service.OrdineItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordineitems")
public class OrdineItemsController {

    @Autowired
    OrdineItemsService service;

    @PostMapping("/registerOrdineItems")
    public OrdineItemsDTO register(@RequestBody OrdineItemsDTO ordineItemsDTO) {
        return service.save(ordineItemsDTO);
    }


    @PutMapping("/updateOrdineItems")
    public OrdineItemsDTO updateOrdineItems(@RequestBody OrdineItemsDTO ordineItemsDTO) {
        return service.save(ordineItemsDTO);
    }

    @DeleteMapping("/deleteOrdineItems")
    public void deleteOrdineItems(@RequestParam Long idOrdineItems) {
        service.deleteOrdineItems(idOrdineItems);
    }

    @GetMapping("/readOrdineItems")
    public OrdineItemsDTO readOrdineItems(@RequestParam Long idOrdineItems) {
        return service.readOrdineItems(idOrdineItems);
    }

    @GetMapping("/pendingList")
    public List<OrdineItemsDTO> pendingList(@RequestParam  Long idanagrafica){
        return service.findAllByAnagrafica(idanagrafica);
    }

    @GetMapping("/dettaglioList")
    public List<OrdineItemsDTO> dettaglioList(@RequestParam Long idanagrafica, @RequestParam  Long idordine) {
        return service.findAllByAnagraficaUtenteIdAnagraficaAndIdordine(idanagrafica, idordine);
    }
    @PostMapping("/inviaCarrello")
    public OrdineItemsDTO inviaCarrello (@RequestBody OrdineItemsDTO dto) {
        return service.inviaCarrello(dto);
    }

}
