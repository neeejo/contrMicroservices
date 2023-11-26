package it.contrader.prodottoService.repository;


import it.contrader.prodottoService.model.OrdineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineItemsRepository extends JpaRepository<OrdineItems, Long> {


    List<OrdineItems> findAllByUtenteAndOrdineIsNull(Long idAnagrafica);

    List<OrdineItems> findAllByUtenteAndOrdineIdordine(Long idAnagrafica, Long idordine);

    OrdineItems findByidordineItems(Long id);
}
