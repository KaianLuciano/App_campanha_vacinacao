package br.com.uniceplac.appvacina.dto;

import br.com.uniceplac.appvacina.models.UsuarioModel;
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
public class UsuarioDto implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    public UsuarioDto(UsuarioModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
