package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.DTO.CampanhaVacinacaoDTO;
import br.com.uniceplac.appvacina.DTO.PacienteDTO;
import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findAll());
    }

    @Operation(summary = "Busca a campanha o paciente presente no banco representado pelo ID passado no end-point", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dado de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametro inválidado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping(value = "/{idPaciente}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idPaciente") String idPaciente) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findById(idPaciente));
    }

    @Operation(summary = "Salva um novo paciente de acordo com os parâmetros do objeto recebido", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campanha salva com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados ds requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidado"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os dados"),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> savePaciente(@RequestBody PacienteModel pacienteModel) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.savePaciente(pacienteModel));
    }

    @Operation(summary = "Atualiza um paciente existente no banco", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campanha atualizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados ds requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidado"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar os dados"),
    })
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

        return ResponseEntity.status(HttpStatus.OK).body("Paciente Atualizado: " + new PacienteDTO(pacientePut));
    }

    @Operation(summary = "Delete o paciente que representa o idPaciente passado no end-point da requisição", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente salva com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados ds requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidado"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os dados"),
    })
    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Object> deleteCampanha(@PathVariable(value = "idPaciente") String idPaciente) {
        Optional<PacienteModel> pacienteModelOptional = pacienteService.findByIdPrivate(idPaciente);

        if (pacienteModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente com o Id especificado não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.deletePaciente(pacienteModelOptional.get()));
    }

}
