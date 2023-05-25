package br.com.uniceplac.appvacina.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_campanha-de-vacinacao")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CampanhaVacinacaoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data")
    private Date data;
}
