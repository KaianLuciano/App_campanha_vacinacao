package br.com.uniceplac.appvacina.models;

import br.com.uniceplac.appvacina.models.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Entity
@Table(name = "tb_pacientes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PacienteModel implements Serializable {

    @Id
    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "nome_paciente")
    private String nome;

    @Column(name = "genero")
    private Genero genero;

    @Column(name = "idade")
    private int idade;

}
