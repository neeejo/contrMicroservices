package it.contrader.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDTO {
    private AnagraficaDTO anagrafica;
    private UserProfileDTO user;
}
