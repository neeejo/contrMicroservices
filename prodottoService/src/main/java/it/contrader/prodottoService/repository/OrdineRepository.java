package it.contrader.prodottoService.repository;

import it.contrader.prodottoService.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
//    Optional<Ordine> findByIdordine(Long idordine);

    List<Ordine> findAllByUtente(Long utente);
}
