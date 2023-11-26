package it.contrader.anagraficaservice.mapper;

import it.contrader.anagraficaservice.dto.ImmagineDTO;
import it.contrader.anagraficaservice.model.Immagine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImmagineMapper {
    Immagine toImmagine(ImmagineDTO immagineDTO);

    ImmagineDTO toImmagineDTO(Immagine immagine);
}
