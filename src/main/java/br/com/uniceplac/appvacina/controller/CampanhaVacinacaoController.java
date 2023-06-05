package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.DTO.CampanhaVacinacaoDTO;
import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import br.com.uniceplac.appvacina.service.CampanhaVacinacaoService;
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
@RequestMapping(value = "/api/campanhas", produces = {"application/json"})
@Tag(name = "campanha-vacinação")
@AllArgsConstructor
public class CampanhaVacinacaoController {

    final CampanhaVacinacaoService vacinacaoService;

    @Operation(summary = "Buscar todas as campanhas presentes no banco", method = "GET")
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vacinacaoService.findAll());
    }

    @Operation(summary = "Busca a campanha de vacinação presente no banco representada pelo ID passado no end-point", method = "GET")
    @GetMapping(value = "/{idCampanha}")
    public ResponseEntity<Object> findById(@PathVariable (value = "idCampanha") Long idCampanha) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinacaoService.findById(idCampanha));
    }

    @Operation(summary = "Salva uma nova campanha de acordo com os parâmetros do objeto recebido", method = "POST")
    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveCampanha(@RequestBody CampanhaVacinacaoModel vacinacaoModel) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinacaoService.saveCampanha(vacinacaoModel));
    }

    @Operation(summary = "Atualiza uma campanha existente no banco", method = "PUT")
    @PutMapping(value = "/{idCampanha}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCampanha(@PathVariable(value = "idCampanha") Long idCampanha, @RequestBody CampanhaVacinacaoModel vacinacaoModel) {
        Optional<CampanhaVacinacaoModel> vacinacaoModelOptional = vacinacaoService.findByIdPrivate(idCampanha);

        if (vacinacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha com o Id especificado não foi encontrado");
        }

        CampanhaVacinacaoModel vacinacaoPut = vacinacaoModelOptional.get();
        vacinacaoPut.setNome(vacinacaoModel.getNome());
        vacinacaoPut.setData(vacinacaoModel.getData());

        vacinacaoService.saveCampanha(vacinacaoPut);

        return ResponseEntity.status(HttpStatus.OK).body(new CampanhaVacinacaoDTO(vacinacaoPut));
    }


    @Operation(summary = "Delete a campanha que representa o idCampanha passado no end-point da requisição", method = "DELETE")
    @DeleteMapping(value = "/{idCampanha}")
    public ResponseEntity<Object> deleteCampanha(@PathVariable(value = "idCampanha") Long idCampanha) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinacaoService.deleteCampanhaById(idCampanha));
    }


}
