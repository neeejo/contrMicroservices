package it.contrader.anagraficaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDTO {
    private AnagraficaDTO anagrafica;
    private UserDTO user;
}
