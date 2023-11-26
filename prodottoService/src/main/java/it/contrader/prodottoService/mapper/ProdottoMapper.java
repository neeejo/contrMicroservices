package it.contrader.prodottoService.mapper;

import it.contrader.prodottoService.dto.ProdottoDTO;
import it.contrader.prodottoService.model.Prodotto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdottoMapper {

    Prodotto toProdotto(ProdottoDTO prodottoDTO);

    ProdottoDTO toProdottoDTO(Prodotto prodotto);

    List<ProdottoDTO> toProdottoDTOList(List<Prodotto> prodottoList);
}
