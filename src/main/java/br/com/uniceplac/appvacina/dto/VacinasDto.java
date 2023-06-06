package br.com.uniceplac.appvacina.dto;

import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacinasDto implements Serializable {

    @Column(name = "nome")
    private String nome;

    @Column(name = "lote")
    private LoteModel lote;

    public VacinasDto(VacinasModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
