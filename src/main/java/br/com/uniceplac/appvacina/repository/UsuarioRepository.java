package br.com.uniceplac.appvacina.repository;

import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    UsuarioModel findByEmail(String username);
}
