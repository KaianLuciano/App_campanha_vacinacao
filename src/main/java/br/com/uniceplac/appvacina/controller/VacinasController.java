package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.dto.VacinasDto;
import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.service.VacinasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vacinas")
@Tag(name = "vacina")
@AllArgsConstructor
public class VacinasController {

    final VacinasService vacinasService;

    @Operation(summary = "Buscar todas as vacinas presentes no banco", method = "GET")
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vacinasService.findAll());
    }

    @Operation(summary = "Busca a vacina presente no banco representado pelo IdVacina passado no end-point", method = "GET")
    @GetMapping(value = "/{idVacina}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idVacina") Long idVacina) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinasService.findById(idVacina));
    }

    @Operation(summary = "Salva uma nova vacina de acordo com os parâmetros do objeto recebido, cria um novo lote e associa-o ao mesmo (Temporario para testes)", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveVacina(@RequestBody VacinasModel vacinasModel) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinasService.saveVacina(vacinasModel));
    }

    @Operation(summary = "Atualiza uma vacina existente no banco", method = "PUT")
    @PutMapping(value = "/{idVacina}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateVacina(@PathVariable(value = "idVacina") Long idVacina, @RequestBody VacinasModel vacinasModel) {
        Optional<VacinasModel> vacinasModelOptional = vacinasService.findByIdPrivate(idVacina);

        if (vacinasModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacina com o Id especificado não foi encontrado");
        }

        VacinasModel vacinaPut = vacinasModelOptional.get();
        vacinaPut.setLote(vacinasModel.getLote());
        vacinaPut.setNome(vacinasModel.getNome());

        vacinasService.saveVacina(vacinaPut);

        return ResponseEntity.status(HttpStatus.OK).body(new VacinasDto(vacinaPut));
    }

    @Operation(summary = "Deleta a vacina que representa o idVacina passado no end-point da requisição", method = "DELETE")
    @DeleteMapping("/{idVacina}")
    public ResponseEntity<Object> deleteVacina(@PathVariable(value = "idVacina") Long idVacina) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinasService.deleteVacina(idVacina));
    }

}
