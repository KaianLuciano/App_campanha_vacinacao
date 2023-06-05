package br.com.uniceplac.appvacina.DTO;

import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class VacinasDTO implements Serializable {

    @Column(name = "nome")
    private String nome;

    @Column(name = "lote")
    private LoteModel lote;

    public VacinasDTO(VacinasModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
