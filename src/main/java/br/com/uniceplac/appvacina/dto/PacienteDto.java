package br.com.uniceplac.appvacina.dto;

import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.models.enums.Genero;
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
public class PacienteDto implements Serializable {

    @Column(name = "nome_paciente")
    private String nome;

    @Column(name = "genero")
    private Genero genero;

    @Column(name = "idade")
    private int idade;

    public PacienteDto(PacienteModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
