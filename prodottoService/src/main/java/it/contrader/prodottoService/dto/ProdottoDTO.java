package it.contrader.prodottoService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoDTO {

    private Long idprodotto;

    private String nome;

    private Long negozio;

    private double prezzo;

    private String descrizione;

    private String tipologia;

    private int quantita;

    private String codiceprodotto;
}