package it.contrader.anagraficaservice.repository;

import it.contrader.anagraficaservice.dto.ProfileDTO;
import it.contrader.anagraficaservice.model.Anagrafica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagraficaRepository extends JpaRepository<Anagrafica, Long> {

    Anagrafica findByIduser (Long id);

    Page<Anagrafica> findAllByIduserNotNull(Pageable pageable);






}
