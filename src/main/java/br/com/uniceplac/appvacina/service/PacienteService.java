package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.DTO.CampanhaVacinacaoDTO;
import br.com.uniceplac.appvacina.DTO.PacienteDTO;
import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PacienteService {

    final PacienteRepository pacienteRepository;

    public List<PacienteDTO> findAll() {
        List<PacienteModel> pacienteModels = pacienteRepository.findAll();
        return pacienteModels.stream().map(x -> new PacienteDTO(x)).toList();
    }

    public PacienteDTO findById(String idPaciente) {
        return new PacienteDTO(pacienteRepository.findById(idPaciente).get());
    }

    public Optional<PacienteModel> findByIdPrivate(String idPaciente) {
        return pacienteRepository.findById(idPaciente);
    }

    public PacienteDTO savePaciente(PacienteModel pacienteModel) {
        pacienteRepository.save(pacienteModel);
        return new PacienteDTO(pacienteModel);
    }

    public PacienteDTO deletePaciente(PacienteModel pacienteModel) {
        pacienteRepository.delete(pacienteModel);
        return new PacienteDTO(pacienteModel);
    }
}
