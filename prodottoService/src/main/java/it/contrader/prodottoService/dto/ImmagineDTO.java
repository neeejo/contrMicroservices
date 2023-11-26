package it.contrader.prodottoService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ImmagineDTO {

    private long idimmagine;

    private byte[] picBytes;

    private ProdottoDTO idprodotto;

}
