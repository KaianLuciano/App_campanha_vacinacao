package br.com.uniceplac.appvacina.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_vacinas")
@Builder
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VacinasModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JsonIgnore
    private LoteModel lote;

    public VacinasModel(String nome, LoteModel lote) {
        this.nome = nome;
        this.lote = lote;
    }
}
