package br.com.uniceplac.appvacina.models;

import br.com.uniceplac.appvacina.models.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tb_usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "tipo_usuario")
    private TipoUsuario tipo_usuario;

}
