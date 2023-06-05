package br.com.uniceplac.appvacina.models;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "tb_lotes")
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class LoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long identificacaoLote;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFabricacao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataValidade;

    private String composicao;

    private String fabricante;

    private String informacoesArmazenamento;

    private String registroSanitario;

    private String informacoesControleQualidade;

    @ManyToOne
    @JoinColumn(name = "lote")
    private VacinasModel vacinas;

}
