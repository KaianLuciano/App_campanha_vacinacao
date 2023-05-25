package br.com.uniceplac.appvacina.repository;

import br.com.uniceplac.appvacina.models.VacinasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacinasRepository extends JpaRepository<VacinasModel, Long> {
}
