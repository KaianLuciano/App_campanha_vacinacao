package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PacienteService {

    final PacienteRepository pacienteRepository;
}
