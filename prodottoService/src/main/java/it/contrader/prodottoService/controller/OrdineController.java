package it.contrader.prodottoService.controller;


import it.contrader.prodottoService.dto.OrdineDTO;
import it.contrader.prodottoService.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordine")
public class OrdineController {

    @Autowired
    OrdineService service;


    @PostMapping("/registerOrdine")
    public OrdineDTO register(@RequestBody OrdineDTO ordineDTO) {
        return service.save(ordineDTO);
    }

    @PutMapping("/updateOrdine")
    public OrdineDTO updateOrdine(@RequestBody OrdineDTO ordineDTO) {
        return service.save(ordineDTO);
    }

    @DeleteMapping("/deleteOrdine")
    public void deleteOrdine(@RequestParam Long idanagrafica) {
        service.deleteOrdine(idanagrafica);
    }

    @GetMapping("/readOrdine")
    public OrdineDTO readOrdine(@RequestParam Long idanagrafica) {
        return service.readOrdine(idanagrafica);

    }
    @GetMapping("/listaOrdini")
    public List<OrdineDTO> listaOrdini(@RequestParam  Long idAnagrafica){
        return service.findAllByUtente(idAnagrafica);
    }
    @PostMapping("/acquistaCarrello")
    public OrdineDTO acquistaCarrello(@RequestParam Long idAnagrafica){
        return service.acquistaCarrello(idAnagrafica);
    }

    @PostMapping("/acquistaProdottoSingolo")
    public OrdineDTO acquistaProdottoSingolo(@RequestParam  Long idAnagrafica, @RequestParam  Long idProdotto){
         return service.acquistaProdotto(idProdotto,idAnagrafica);
    }
}