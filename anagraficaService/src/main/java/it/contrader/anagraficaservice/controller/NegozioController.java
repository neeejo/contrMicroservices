package it.contrader.anagraficaservice.controller;

import it.contrader.anagraficaservice.dto.NegozioDTO;
import it.contrader.anagraficaservice.model.Negozio;
import it.contrader.anagraficaservice.service.NegozioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neg")
public class NegozioController {


    @Autowired
    private NegozioService nService;


    @GetMapping("/nByProprietario")
    public List<Negozio> nByProprietario (@RequestParam  Long id) {
        return nService.findByProprietario(id);
    }

    @GetMapping("/nIdByProprietario")
    public List<Long> nIdByProprietario(@RequestParam Long idanagrafica) {return nService.findIdnegozioByProprietarioId(idanagrafica);}

    @PostMapping("/registerNegozio")
    public NegozioDTO registerNegozio(@RequestBody NegozioDTO negozioDTO) {
        return nService.registerNegozio(negozioDTO);
    }

    @PutMapping("/updateNegozio")
    public NegozioDTO updateNegozio(@RequestBody NegozioDTO negozioDTO){
        return nService.updateNegozio(negozioDTO);
    }

    @DeleteMapping("/deleteNegozio")
    public void deleteNegozio(@RequestParam Long idnegozio) {
        nService.deleteNegozio(idnegozio);
    }
    @GetMapping("/readNegozio")
    public NegozioDTO readNegozio(@RequestParam Long idnegozio){
        return nService.readNegozio(idnegozio);
    }
}
