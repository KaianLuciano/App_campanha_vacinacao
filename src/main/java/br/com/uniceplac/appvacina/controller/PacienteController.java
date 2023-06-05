package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.DTO.PacienteDTO;
import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "paciente")
@AllArgsConstructor
public class PacienteController {

    final PacienteService pacienteService;


    @Operation(summary = "Buscar todas os pacientes presentes no banco", method = "GET")
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findAll());
    }

    @Operation(summary = "Busca o paciente presente no banco representado pelo ID passado no end-point", method = "GET")
    @GetMapping(value = "/{idPaciente}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idPaciente") String idPaciente) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findById(idPaciente));
    }

    @Operation(summary = "Salva um novo paciente de acordo com os parâmetros do objeto recebido", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> savePaciente(@RequestBody PacienteModel pacienteModel) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.savePaciente(pacienteModel));
    }

    @Operation(summary = "Atualiza um paciente existente no banco", method = "PUT")
    @PutMapping(value = "/{idPaciente}", consumes = MediaType.APPLICATION_JSON_VALUE)
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

        return ResponseEntity.status(HttpStatus.OK).body(new PacienteDTO(pacientePut));
    }

    @Operation(summary = "Delete o paciente que representa o idPaciente passado no end-point da requisição", method = "DELETE")
    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Object> deleteCampanhaById(@PathVariable(value = "idPaciente") String idPaciente) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.deletePaciente(idPaciente));
    }

}
