package br.com.uniceplac.appvacina.dto;

import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaVacinacaoDto implements Serializable {

    private String nome;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.ssXXX")
    private Date data;

    public CampanhaVacinacaoDto(CampanhaVacinacaoModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
