package it.contrader.anagraficaservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Immagine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50000000)
    private byte[] picByte;

    @OneToOne
    @JoinColumn(name="idanagrafica" , referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Anagrafica anagrafica;


}
