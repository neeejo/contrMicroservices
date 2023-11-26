package it.contrader.anagraficaservice.dto;

import it.contrader.anagraficaservice.model.Anagrafica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NegozioDTO {

    private Long idnegozio;
    private String nome;
    private AnagraficaDTO proprietario;
    private String descrizione;
    private String indirizzo;
    private String citta;
    private String provincia;


}