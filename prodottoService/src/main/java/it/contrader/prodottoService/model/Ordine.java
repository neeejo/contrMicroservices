package it.contrader.prodottoService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "ordine")
public class Ordine {


    @Column
    private Long utente;

    @Column(name="data")
    private LocalDateTime data;

    @Column(name="totale")
    private Double totale;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idordine")
    private Long idordine;



}