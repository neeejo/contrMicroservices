package it.contrader.anagraficaservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Negozio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnegozio;

    @Column(unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name="proprietario", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Anagrafica proprietario;

    private String descrizione;

    private String indirizzo;

    private String citta;

    private String provincia;

}
