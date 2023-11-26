package it.contrader.prodottoService.repository;

import it.contrader.prodottoService.model.Immagine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ImmagineRepository extends JpaRepository<Immagine, Long> {

    public List<Immagine> findAllByProdottoIdprodotto(Long id);

}
