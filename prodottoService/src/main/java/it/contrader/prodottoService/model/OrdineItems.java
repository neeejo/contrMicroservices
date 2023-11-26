package it.contrader.prodottoService.model;

import it.contrader.prodottoService.model.Ordine;
import it.contrader.prodottoService.model.Prodotto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "ordine_items")
public class OrdineItems {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idordine_items")
    private Long idordineItems;

    @ManyToOne
    @JoinColumn(name="ordine", referencedColumnName = "idordine")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name="prodotto", referencedColumnName = "idprodotto")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Prodotto prodotto;

    @Column
    private Long utente;

    @Column(name="quantita")
    private Double quantita;

    @Column(name="prezzo")
    private Double prezzo;

    @Column(name="datetime")
    private LocalDateTime datetime;







}