package br.com.uniceplac.appvacina.security.user;

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
public class LoginDto implements Serializable {
    private String email;
    private String senha;
}
