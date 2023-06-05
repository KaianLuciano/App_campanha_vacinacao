package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.DTO.LoteDTO;
import br.com.uniceplac.appvacina.DTO.VacinasDTO;
import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.repository.LoteRepository;
import br.com.uniceplac.appvacina.repository.VacinasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoteService {

    final LoteRepository loteRepository;

    public List<LoteDTO> findAll() {
        List<LoteModel> loteModels = loteRepository.findAll();
        return loteModels.stream().map(x -> new LoteDTO(x)).toList();
    }

    public LoteDTO findById(Long idLote) {
        return new LoteDTO(loteRepository.findById(idLote).get());
    }

    public Optional<LoteModel> findByIdPrivate(Long idLote) {
        return loteRepository.findById(idLote);
    }

    public LoteDTO saveVacina(LoteModel loteModel) {
        loteRepository.save(loteModel);
        return new LoteDTO(loteModel);
    }

    public LoteDTO deleteVacina(Long idLote) {
        LoteModel loteModel = loteRepository.findById(idLote).get();
        loteRepository.deleteById(idLote);
        return new LoteDTO(loteModel);
    }

}
