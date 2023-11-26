package it.contrader.prodottoService.dto;

import it.contrader.prodottoService.model.Ordine;
import it.contrader.prodottoService.model.Prodotto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdineItemsDTO {


    private Long idordineItems;

    private Ordine ordine;

    private Prodotto prodotto;

    private Long utente;

    private double quantita;

    private double prezzo;

    private LocalDateTime datetime;

    public OrdineItemsDTO(Ordine ordine, Prodotto prodotto, Long utente, double quantita, double prezzo, LocalDateTime datetime) {
        this.ordine = ordine;
        this.prodotto = prodotto;
        this.utente = utente;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.datetime = datetime;
    }
}