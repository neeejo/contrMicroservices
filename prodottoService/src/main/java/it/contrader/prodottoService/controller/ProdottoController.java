package it.contrader.prodottoService.controller;


import it.contrader.prodottoService.dto.ProdottoDTO;
import it.contrader.prodottoService.model.Prodotto;
import it.contrader.prodottoService.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {

    @Autowired
    private ProdottoService service;

    @PostMapping("/insert")
    public ProdottoDTO register(@RequestBody ProdottoDTO prodottoDTO) {
        return service.save(prodottoDTO);
    }

    @PutMapping("/update")
    public ProdottoDTO update(@RequestBody ProdottoDTO prodottoDTO) {
        return service.update(prodottoDTO);
    }
    @GetMapping("/read")
    public ProdottoDTO read(@RequestParam Long idprodotto){
        return service.read(idprodotto);
    }
    @GetMapping("/tipi")
    public List<String> types(){
        return service.findTypes();
    }



    @GetMapping("/getAllByTipologia")
    public List<ProdottoDTO> listByTipologia(@RequestParam @Valid String tipo, @RequestParam @Valid Long idp){
        return service.findAllByTipologia(tipo, idp);
    }


    @GetMapping("getAllPaginataByNegozio")
    public ResponseEntity<Page<Prodotto>> getall( @RequestParam("idnegozio") long idnegozio, @RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        return new ResponseEntity<>(service.getAllPaginataAdmin(PageRequest.of(pageNumber, pageSize), idnegozio), HttpStatus.OK);
    }

    @GetMapping("/getAllPaginata")
    public ResponseEntity<Page<Prodotto>> getall(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        return new ResponseEntity<>(service.getAllPaginata(PageRequest.of(pageNumber, pageSize)), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("idprodotto") long id) {
        service.delete(id);
    }


}
