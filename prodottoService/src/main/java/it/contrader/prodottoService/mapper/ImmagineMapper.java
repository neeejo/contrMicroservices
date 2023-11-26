package it.contrader.prodottoService.mapper;


import it.contrader.prodottoService.dto.ImmagineDTO;
import it.contrader.prodottoService.model.Immagine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImmagineMapper {

    Immagine toImmagine(ImmagineDTO immagineDTO);

    ImmagineDTO toImmagineDTO(Immagine immagine);

}
