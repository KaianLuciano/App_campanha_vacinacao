package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.service.LoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lotes")
@Tag(name = "lotes")
@AllArgsConstructor
public class LoteController {

    final LoteService loteService;

    @Operation(summary = "Buscar todas os lotes presentes no banco", method = "GET")
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(loteService.findAll());
    }

    @Operation(summary = "Busca o lote presente no banco representado pelo idLote passado no end-point", method = "GET")
    @GetMapping(value = "/{idLote}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idLote") Long idLote) {
        return ResponseEntity.status(HttpStatus.OK).body(loteService.findById(idLote));
    }

    @Operation(summary = "Salva um novo lote de acordo com os parâmetros do objeto recebido", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody LoteModel loteModel) {
        return ResponseEntity.status(HttpStatus.OK).body(loteService.saveLote(loteModel));
    }

    @Operation(summary = "Deleta o lote que representa o idLote passado no end-point da requisição", method = "DELETE")
    @DeleteMapping("/{idLote}")
    public ResponseEntity<Object> delete(@PathVariable(value = "idLote") Long idLote) {
        return ResponseEntity.status(HttpStatus.OK).body(loteService.deleteLote(idLote));
    }

}
