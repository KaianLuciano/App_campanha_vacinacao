package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.DTO.CampanhaVacinacaoDTO;
import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import br.com.uniceplac.appvacina.service.CampanhaVacinacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/campanhas")
@AllArgsConstructor
public class CampanhaVacinacaoController {

    final CampanhaVacinacaoService vacinacaoService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vacinacaoService.findAll());
    }

    @GetMapping("/{idCampanha}")
    public ResponseEntity<Object> findById(@PathVariable (value = "idCampanha") Long idCampanha) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinacaoService.findById(idCampanha));
    }
    @PostMapping
    public ResponseEntity<Object> saveCampanha(@RequestBody CampanhaVacinacaoModel vacinacaoModel) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinacaoService.saveCampanha(vacinacaoModel));
    }

    @PutMapping("/{idCampanha}")
    public ResponseEntity<Object> updateCampanha(@PathVariable(value = "idCampanha") Long idCampanha, @RequestBody CampanhaVacinacaoModel vacinacaoModel) {
        Optional<CampanhaVacinacaoModel> vacinacaoModelOptional = vacinacaoService.findByIdPrivate(idCampanha);

        if (vacinacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha com o Id especificado não foi encontrado");
        }

        CampanhaVacinacaoModel vacinacaoPut = vacinacaoModelOptional.get();
        vacinacaoPut.setNome(vacinacaoModel.getNome());
        vacinacaoPut.setData(vacinacaoModel.getData());

        vacinacaoService.saveCampanha(vacinacaoPut);

        return ResponseEntity.status(HttpStatus.OK).body("Campanha Atualizada: " + new CampanhaVacinacaoDTO(vacinacaoPut));
    }

    @DeleteMapping("/{idCampanha}")
    public ResponseEntity<Object> deleteCampanha(@PathVariable(value = "idCampanha") Long idCampanha) {
        Optional<CampanhaVacinacaoModel> vacinacaoModelOptional = vacinacaoService.findByIdPrivate(idCampanha);

        if (vacinacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha com o Id especificado não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(vacinacaoService.deleteCampanha(vacinacaoModelOptional.get()));
    }


}
