package it.contrader.anagraficaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImmagineDTO {
    private Long id;
    private byte[] picByte;
    private AnagraficaDTO anagrafica;
}
