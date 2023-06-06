package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.dto.PacienteDto;
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

    public List<PacienteDto> findAll() {
        List<PacienteModel> pacienteModels = pacienteRepository.findAll();
        return pacienteModels.stream().map(x -> new PacienteDto(x)).toList();
    }

    public PacienteDto findById(String idPaciente) {
        return new PacienteDto(pacienteRepository.findById(idPaciente).get());
    }

    public Optional<PacienteModel> findByIdPrivate(String idPaciente) {
        return pacienteRepository.findById(idPaciente);
    }

    public PacienteDto savePaciente(PacienteModel pacienteModel) {
        pacienteRepository.save(pacienteModel);
        return new PacienteDto(pacienteModel);
    }

    public PacienteDto deletePaciente(String cpf) {
        PacienteModel paciente = pacienteRepository.findById(cpf).get();
        pacienteRepository.deleteById(cpf);
        return new PacienteDto(paciente);
    }

}
