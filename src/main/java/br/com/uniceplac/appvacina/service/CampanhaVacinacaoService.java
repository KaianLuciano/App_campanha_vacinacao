package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.dto.CampanhaVacinacaoDto;
import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import br.com.uniceplac.appvacina.repository.CampanhaVacinacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CampanhaVacinacaoService {

    final CampanhaVacinacaoRepository vacinacaoRepository ;

    public List<CampanhaVacinacaoDto> findAll() {
        List<CampanhaVacinacaoModel> vacinacaoModels = vacinacaoRepository.findAll();
        return vacinacaoModels.stream().map(x -> new CampanhaVacinacaoDto(x)).toList();
    }

    public CampanhaVacinacaoDto findById(Long idCampanha) {
        return new CampanhaVacinacaoDto(vacinacaoRepository.findById(idCampanha).get());
    }

    public Optional<CampanhaVacinacaoModel> findByIdPrivate(Long idCampanha) {
        return vacinacaoRepository.findById(idCampanha);
    }

    public CampanhaVacinacaoDto saveCampanha(CampanhaVacinacaoModel vacinacaoModel) {
        vacinacaoRepository.save(vacinacaoModel);
        return new CampanhaVacinacaoDto(vacinacaoModel);
    }

    public CampanhaVacinacaoDto deleteCampanhaById(Long idCampanha) {
            CampanhaVacinacaoModel campanhaModel = vacinacaoRepository.findById(idCampanha).get();
            vacinacaoRepository.deleteById(idCampanha);
            return new CampanhaVacinacaoDto(campanhaModel);
    }

}
