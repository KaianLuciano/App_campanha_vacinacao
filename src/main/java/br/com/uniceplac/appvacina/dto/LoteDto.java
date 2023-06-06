package br.com.uniceplac.appvacina.dto;

import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteDto implements Serializable {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFabricacao;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataValidade;
    private String composicao;
    private String fabricante;
    private String informacoesArmazenamento;
    private String registroSanitario;
    private String informacoesControleQualidade;
    private List<VacinasModel> vacinas;

    public LoteDto(LoteModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
