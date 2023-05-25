package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.DTO.PacienteDTO;
import br.com.uniceplac.appvacina.DTO.UsuarioDTO;
import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idUsuario") Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(idUsuario));
    }
    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveUsuario(usuarioModel));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "idUsuario") Long idUsuario, @RequestBody UsuarioModel usuarioModel) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findByIdPrivate(idUsuario);

        if (usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente com o Id especificado não foi encontrado");
        }

        UsuarioModel usuarioPut = usuarioModelOptional.get();
        usuarioPut.setUsername(usuarioModel.getUsername());
        usuarioPut.setEmail(usuarioModel.getEmail());
        usuarioPut.setSenha(usuarioModel.getSenha());
        usuarioPut.setTipo_usuario(usuarioModel.getTipo_usuario());

        usuarioService.saveUsuario(usuarioPut);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario Atualizado: " + new UsuarioDTO(usuarioPut));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deleteCampanha(@PathVariable(value = "idUsuario") Long idUsuario) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findByIdPrivate(idUsuario);

        if (usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com o Id especificado não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.deleteUsuario(usuarioModelOptional.get()));
    }

}
