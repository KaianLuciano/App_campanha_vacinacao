package br.com.uniceplac.appvacina.DTO;

import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import br.com.uniceplac.appvacina.models.PacienteModel;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaVacinacaoDTO implements Serializable {

    private String nome;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.ssXXX")
    private Date data;

    public CampanhaVacinacaoDTO(CampanhaVacinacaoModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
