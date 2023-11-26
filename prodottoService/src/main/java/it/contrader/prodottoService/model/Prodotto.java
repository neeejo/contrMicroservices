package it.contrader.prodottoService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Entity
    @Table(name="prodotto")
    public class Prodotto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="idprodotto")
        private Long idprodotto;

        @Column(name="nome")
        private String nome;

        //@ManyToOne
        @JoinColumn(name="negozio", referencedColumnName = "idnegozio")
        private Long negozio;

        @Column(name="prezzo")
        private double prezzo;

        @Column(name="descrizione")
        private String descrizione;

        @Column(name="tipologia")
        private String tipologia;

        @Column(name="quantita")
        private int quantita;

        @Column(unique = true, name="codiceprodotto")
        private String codiceprodotto;
    }

