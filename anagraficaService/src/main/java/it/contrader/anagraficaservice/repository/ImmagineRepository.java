package it.contrader.anagraficaservice.repository;

import it.contrader.anagraficaservice.dto.AnagraficaDTO;
import it.contrader.anagraficaservice.model.Anagrafica;
import it.contrader.anagraficaservice.model.Immagine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImmagineRepository extends JpaRepository<Immagine, Long> {

    Immagine findByAnagraficaId(Long id);
    //Optional<Immagine> findByAnagrafica (Anagrafica anagrafica);
    List<Immagine> findAllByAnagrafica(Anagrafica a);

  Immagine findByAnagrafica(Anagrafica anagrafica);
}
