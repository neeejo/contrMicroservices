package it.contrader.prodottoService.service;

import it.contrader.prodottoService.dto.ProdottoDTO;
import it.contrader.prodottoService.mapper.ProdottoMapper;
import it.contrader.prodottoService.model.Prodotto;
import it.contrader.prodottoService.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoMapper mapper;
    @Autowired
    private ProdottoRepository repository;

    public ProdottoDTO findById(Long idProdotto) {
        return mapper.toProdottoDTO(repository.findByidprodotto(idProdotto));
    }

    public void delete(long id){ repository.deleteByIdprodotto(id);}
    public ProdottoDTO save(ProdottoDTO prodottoDTO) {
        return mapper.toProdottoDTO(repository.save(mapper.toProdotto(prodottoDTO)));}
    public ProdottoDTO update(ProdottoDTO prodottoDTO){
        return mapper.toProdottoDTO(repository.save(mapper.toProdotto(prodottoDTO)));}
    public ProdottoDTO read(Long idprodotto){
        return mapper.toProdottoDTO(repository.findById(idprodotto).orElseThrow(()->new RuntimeException("errore lettura prodotto")));}
     public List<String> findTypes(){ return repository.findTypes();}
    public Page<Prodotto> getAllPaginataAdmin(Pageable pageable, long idnegozio) { return repository.findAllByNegozio(idnegozio,pageable); }
    public Page<Prodotto> getAllPaginata(Pageable pageable) { return repository.findAll(pageable); }

   public List<ProdottoDTO> findAllByTipologia(String tipo, Long idp){ return mapper.toProdottoDTOList(repository.findAllByTipologiaIsLikeAndIdprodottoIsNotLike(tipo, idp));}



}
