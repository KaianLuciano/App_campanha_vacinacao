package br.com.uniceplac.appvacina.DTO;

import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.models.enums.TipoUsuario;
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
public class UsuarioDTO implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    public UsuarioDTO(UsuarioModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
