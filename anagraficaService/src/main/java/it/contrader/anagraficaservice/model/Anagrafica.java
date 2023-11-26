package it.contrader.anagraficaservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Anagrafica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private Long id;

    //@OneToOne
    @Column(unique = true)
    private Long iduser;

    @Column (name="nome")
    private String nome;

    @Column (name="cognome")
    private String cognome;

    @Column(name="citta")
    private String citta;

    @Column (name="residenza")
    private String residenza;

    @Column (name="provincia")
    private String provincia;

    @Column (name="nazione")
    private String nazione;

    @Column(name="datanascita")
    private Date datanascita;

    @Column(name="genere")
    private Boolean genere;

    @Column(name="datacreazione")
    private Date datacreazione;


//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="immagine", referencedColumnName = "id")
//    private Immagine immagine;


}
