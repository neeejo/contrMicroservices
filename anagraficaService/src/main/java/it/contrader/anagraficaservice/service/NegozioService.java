package it.contrader.anagraficaservice.service;

import it.contrader.anagraficaservice.dto.NegozioDTO;
import it.contrader.anagraficaservice.mapper.NegozioMapper;
import it.contrader.anagraficaservice.model.Negozio;
import it.contrader.anagraficaservice.repository.NegozioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.contrader.anagraficaservice.mapper.AnagraficaMapper;

import java.util.List;

@Service
public class NegozioService {

    @Autowired
    private NegozioRepository repository;

    @Autowired
    private NegozioMapper mapper;

    @Autowired
    private AnagraficaMapper amapper;




    public List<Negozio> findByProprietario(Long idanagrafica) {
        return repository.findByProprietarioId(idanagrafica);

    }

    public List<Long> findIdnegozioByProprietarioId(Long idanagrafica){
        return repository.findIdnegozioByProprietarioId(idanagrafica);
    }

    public NegozioDTO registerNegozio(NegozioDTO negozioDTO) {
        Negozio negozio = new Negozio();
        negozio.setNome(negozioDTO.getNome());
        negozio.setCitta(negozioDTO.getCitta());
        negozio.setDescrizione(negozioDTO.getDescrizione());
        negozio.setIndirizzo(negozioDTO.getIndirizzo());
        negozio.setProvincia(negozioDTO.getProvincia());
        negozio.setIdnegozio(negozioDTO.getIdnegozio());
        negozio.setProprietario(amapper.toAnagrafica(negozioDTO.getProprietario()));
        return mapper.toNegozioDTO(repository.save(negozio));
    }

    public NegozioDTO updateNegozio(NegozioDTO negozioDTO){
        return mapper.toNegozioDTO(repository.save(mapper.toNegozio(negozioDTO)));
    }

    public void deleteNegozio(Long idnegozio){
       repository.deleteById(idnegozio);
    }

    public NegozioDTO readNegozio(Long idnegozio){
        return mapper.toNegozioDTO(repository.findById(idnegozio).orElseThrow(()->new RuntimeException("errore lettura negozio")));
    }
}
