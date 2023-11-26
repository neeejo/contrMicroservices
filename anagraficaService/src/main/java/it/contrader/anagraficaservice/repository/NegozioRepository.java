package it.contrader.anagraficaservice.repository;

import it.contrader.anagraficaservice.dto.NegozioDTO;
import it.contrader.anagraficaservice.model.Negozio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NegozioRepository extends JpaRepository<Negozio, Long> {


    public List<Negozio> findByProprietarioId(Long idanagrafica);

    public List<Long> findIdnegozioByProprietarioId(Long idanagrafica);
}
