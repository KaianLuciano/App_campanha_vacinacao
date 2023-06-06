package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.dto.LoteDto;
import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.repository.LoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoteService {

    final LoteRepository loteRepository;

    public List<LoteDto> findAll() {
        List<LoteModel> loteModels = loteRepository.findAll();
        return loteModels.stream().map(x -> new LoteDto(x)).toList();
    }

    public LoteDto findById(Long idLote) {
        return new LoteDto(loteRepository.findById(idLote).get());
    }

    public Optional<LoteModel> findByIdPrivate(Long idLote) {
        return loteRepository.findById(idLote);
    }

    public LoteDto saveLote(LoteModel loteModel) {
        loteRepository.save(loteModel);
        return new LoteDto(loteModel);
    }

    public LoteDto deleteLote(Long idLote) {
        LoteModel loteModel = loteRepository.findById(idLote).get();
        loteRepository.deleteById(idLote);
        return new LoteDto(loteModel);
    }

}
