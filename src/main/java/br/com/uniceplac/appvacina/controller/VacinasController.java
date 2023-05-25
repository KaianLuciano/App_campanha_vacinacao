package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.DTO.UsuarioDTO;
import br.com.uniceplac.appvacina.DTO.VacinasDTO;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.service.VacinasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vacinas")
@AllArgsConstructor
public class VacinasController {

    final VacinasService vacinasService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vacinasService.findAll());
    }

    @GetMapping("/{idVacina}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idVacina") Long idVacina) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinasService.findById(idVacina));
    }
    @PostMapping
    public ResponseEntity<Object> saveVacina(@RequestBody VacinasModel vacinasModel) {
        return ResponseEntity.status(HttpStatus.OK).body(vacinasService.saveVacina(vacinasModel));
    }

    @PutMapping("/{idVacina}")
    public ResponseEntity<Object> updateVacina(@PathVariable(value = "idVacina") Long idVacina, @RequestBody VacinasModel vacinasModel) {
        Optional<VacinasModel> vacinasModelOptional = vacinasService.findByIdPrivate(idVacina);

        if (vacinasModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacina com o Id especificado não foi encontrado");
        }

        VacinasModel vacinaPut = vacinasModelOptional.get();
        vacinaPut.setLote(vacinasModel.getLote());
        vacinaPut.setNome(vacinasModel.getNome());

        vacinasService.saveVacina(vacinaPut);

        return ResponseEntity.status(HttpStatus.OK).body("Vacina Atualizada: " + new VacinasDTO(vacinaPut));
    }

    @DeleteMapping("/{idVacina}")
    public ResponseEntity<Object> deleteVacina(@PathVariable(value = "idVacina") Long idVacina) {
        Optional<VacinasModel> vacinasModelOptional = vacinasService.findByIdPrivate(idVacina);

        if (vacinasModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacina com o Id especificado não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(vacinasService.deleteVacina(vacinasModelOptional.get()));
    }

}
