package it.contrader.anagraficaservice.mapper;

import it.contrader.anagraficaservice.dto.NegozioDTO;
import it.contrader.anagraficaservice.model.Negozio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NegozioMapper {

    Negozio toNegozio(NegozioDTO negozioDTO);

    NegozioDTO toNegozioDTO(Negozio negozio);


}
