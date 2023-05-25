package br.com.uniceplac.appvacina.repository;

import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaVacinacaoRepository extends JpaRepository<CampanhaVacinacaoModel, Long> {
}
