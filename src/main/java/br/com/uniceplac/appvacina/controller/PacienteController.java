package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.DTO.CampanhaVacinacaoDTO;
import br.com.uniceplac.appvacina.DTO.PacienteDTO;
import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@AllArgsConstructor
public class PacienteController {

    final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findAll());
    }

    @GetMapping("/{idPaciente}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idPaciente") String idPaciente) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findById(idPaciente));
    }
    @PostMapping
    public ResponseEntity<Object> savePaciente(@RequestBody PacienteModel pacienteModel) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.savePaciente(pacienteModel));
    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<Object> updatePaciente(@PathVariable(value = "idPaciente") String idPaciente, @RequestBody PacienteModel pacienteModel) {
        Optional<PacienteModel> pacienteModelOptional = pacienteService.findByIdPrivate(idPaciente);

        if (pacienteModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente com o Id especificado não foi encontrado");
        }

        PacienteModel pacientePut = pacienteModelOptional.get();
        pacientePut.setNome(pacienteModel.getNome());
        pacientePut.setIdade(pacienteModel.getIdade());
        pacientePut.setGenero(pacienteModel.getGenero());

        pacienteService.savePaciente(pacientePut);

        return ResponseEntity.status(HttpStatus.OK).body("Paciente Atualizado: " + new PacienteDTO(pacientePut));
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Object> deleteCampanha(@PathVariable(value = "idPaciente") String idPaciente) {
        Optional<PacienteModel> pacienteModelOptional = pacienteService.findByIdPrivate(idPaciente);

        if (pacienteModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente com o Id especificado não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.deletePaciente(pacienteModelOptional.get()));
    }

}
