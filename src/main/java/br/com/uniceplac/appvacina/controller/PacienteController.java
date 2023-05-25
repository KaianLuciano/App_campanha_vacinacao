package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacientes")
@AllArgsConstructor
public class PacienteController {

    final PacienteService pacienteService;
}
