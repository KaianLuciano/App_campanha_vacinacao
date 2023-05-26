package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.DTO.UsuarioDTO;
import br.com.uniceplac.appvacina.DTO.VacinasDTO;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.repository.VacinasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VacinasService {

    final VacinasRepository vacinasRepository;

    public List<VacinasDTO> findAll() {
        List<VacinasModel> vacinasModels = vacinasRepository.findAll();
        return vacinasModels.stream().map(x -> new VacinasDTO(x)).toList();
    }

    public VacinasDTO findById(Long idVacinas) {
        return new VacinasDTO(vacinasRepository.findById(idVacinas).get());
    }

    public Optional<VacinasModel> findByIdPrivate(Long idVacinas) {
        return vacinasRepository.findById(idVacinas);
    }

    public VacinasDTO saveVacina(VacinasModel vacinasModel) {
        vacinasRepository.save(vacinasModel);
        return new VacinasDTO(vacinasModel);
    }

    public VacinasDTO deleteVacina(Long idVacina) {
        VacinasModel vacina = vacinasRepository.findById(idVacina).get();
        vacinasRepository.deleteById(idVacina);
        return new VacinasDTO(vacina);
    }

}
