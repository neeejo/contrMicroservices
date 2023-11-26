package it.contrader.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnagraficaDTO {

    private Long id;

    private Long iduser;

    private String nome;

    private String cognome;

    private String citta;

    private String residenza;

    private String provincia;

    private String nazione;

    private Date datanascita;

    private Boolean genere;

    private Date datacreazione;
}
