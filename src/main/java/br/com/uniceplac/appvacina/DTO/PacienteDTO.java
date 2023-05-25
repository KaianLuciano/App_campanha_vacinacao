package br.com.uniceplac.appvacina.DTO;

import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.models.enums.Genero;
import jakarta.persistence.Column;
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
public class PacienteDTO implements Serializable {

    @Column(name = "nome_paciente")
    private String nome;

    @Column(name = "genero")
    private Genero genero;

    @Column(name = "idade")
    private int idade;

    public PacienteDTO(PacienteModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
