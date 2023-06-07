package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.dto.UsuarioDto;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "usuario")
@AllArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;

    @Operation(summary = "Buscar todas os usuarios presentes no banco", method = "GET")
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @Operation(summary = "Busca o usuario presente no banco representado pelo ID passado no end-point", method = "GET")
    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idUsuario") Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(idUsuario));
    }

    @Operation(summary = "Salva um novo usuario de acordo com os parâmetros do objeto recebido", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveUsuario(usuarioModel));
    }

    @Operation(summary = "Atualiza um usuario existente no banco", method = "PUT")
    @PutMapping(value = "/{idUsuario}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "idUsuario") Long idUsuario, @RequestBody UsuarioModel usuarioModel) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findByIdPrivate(idUsuario);

        if (usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com o Id especificado não foi encontrado");
        }

        UsuarioModel usuarioPut = usuarioModelOptional.get();
        usuarioPut.setNome(usuarioModel.getUsername());
        usuarioPut.setEmail(usuarioModel.getEmail());
        usuarioPut.setSenha(usuarioModel.getSenha());
        usuarioPut.setTipo_usuario(usuarioModel.getTipo_usuario());

        usuarioService.saveUsuario(usuarioPut);

        return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(usuarioPut));
    }

    @Operation(summary = "Delete o usuario que representa o idUsuario passado no end-point da requisição", method = "DELETE")
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.deleteUsuarioById(idUsuario));
    }

}
