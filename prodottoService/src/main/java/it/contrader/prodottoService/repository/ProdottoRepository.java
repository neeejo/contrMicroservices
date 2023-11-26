package it.contrader.prodottoService.repository;

import it.contrader.prodottoService.model.Prodotto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    Prodotto findByidprodotto(Long idprodotto);
    void deleteByIdprodotto(Long idprodotto);
    @Query("SELECT DISTINCT tipologia FROM Prodotto")
    List<String> findTypes();
    List<Prodotto> findAllByTipologiaIsLikeAndIdprodottoIsNotLike(String tipologia, Long idprodotto);
    Page<Prodotto> findAllByNegozio(long idnegozio, Pageable pageable);
}
