package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.DTO.CampanhaVacinacaoDTO;
import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import br.com.uniceplac.appvacina.repository.CampanhaVacinacaoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CampanhaVacinacaoService {

    final CampanhaVacinacaoRepository vacinacaoRepository ;

    public List<CampanhaVacinacaoDTO> findAll() {
        List<CampanhaVacinacaoModel> vacinacaoModels = vacinacaoRepository.findAll();
        return vacinacaoModels.stream().map(x -> new CampanhaVacinacaoDTO(x)).toList();
    }

    public CampanhaVacinacaoDTO findById(Long idCampanha) {
        return new CampanhaVacinacaoDTO(vacinacaoRepository.findById(idCampanha).get());
    }

    public Optional<CampanhaVacinacaoModel> findByIdPrivate(Long idCampanha) {
        return vacinacaoRepository.findById(idCampanha);
    }

    public CampanhaVacinacaoDTO saveCampanha(CampanhaVacinacaoModel vacinacaoModel) {
        vacinacaoRepository.save(vacinacaoModel);
        return new CampanhaVacinacaoDTO(vacinacaoModel);
    }

    public CampanhaVacinacaoDTO deleteCampanha(CampanhaVacinacaoModel vacinacaoModel) {
            vacinacaoRepository.delete(vacinacaoModel);
            return new CampanhaVacinacaoDTO(vacinacaoModel);
    }

}
