package it.contrader.prodottoService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineDTO {

    private Long utente;

    private LocalDateTime data;

    private Double totale;

    private Long idordine;

    public OrdineDTO(Long id, LocalDateTime now, Double sum) {
        this.utente=id;
        this.data=now;
        this.totale=sum;
    }



}