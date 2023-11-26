package it.contrader.prodottoService.mapper;

import it.contrader.prodottoService.dto.OrdineDTO;
import it.contrader.prodottoService.dto.ProdottoDTO;
import it.contrader.prodottoService.model.Ordine;
import it.contrader.prodottoService.model.Prodotto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdineMapper {

    Ordine toOrdine (OrdineDTO ordineDTO);

    OrdineDTO toOrdineDTO(Ordine ordine);
    List<OrdineDTO> tDTOList(List<Ordine> ordineList);

}
