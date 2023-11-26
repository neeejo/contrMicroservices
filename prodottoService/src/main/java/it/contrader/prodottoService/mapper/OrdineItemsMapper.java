package it.contrader.prodottoService.mapper;


import it.contrader.prodottoService.model.Ordine;
import org.mapstruct.Mapper;
import it.contrader.prodottoService.dto.OrdineItemsDTO;
import it.contrader.prodottoService.model.OrdineItems;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdineItemsMapper {

    OrdineItems toOrdineItems (OrdineItemsDTO ordineItemsDTO);

    OrdineItemsDTO toOrdineItemsDTO(OrdineItems ordineItems);

    List<OrdineItemsDTO> toDTOList(List<OrdineItems> ordineItemsList);

}
