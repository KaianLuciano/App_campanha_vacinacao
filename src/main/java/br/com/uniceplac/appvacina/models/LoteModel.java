package br.com.uniceplac.appvacina.models;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_lotes")
@Builder
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

    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL)
    @Hidden
    private List<VacinasModel> vacinas;
}
