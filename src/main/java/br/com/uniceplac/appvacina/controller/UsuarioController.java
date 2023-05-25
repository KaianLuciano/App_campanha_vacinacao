package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.DTO.PacienteDTO;
import br.com.uniceplac.appvacina.DTO.UsuarioDTO;
import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
@Tag(name = "usuario")
@AllArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;

    @Operation(summary = "Buscar todas os usuarios presentes no banco", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @Operation(summary = "Busca o usuario presente no banco representado pelo ID passado no end-point", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dado de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametro inválidado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idUsuario") Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(idUsuario));
    }

    @Operation(summary = "Salva um novo usuario de acordo com os parâmetros do objeto recebido", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campanha salva com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados ds requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidado"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os dados"),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveUsuario(usuarioModel));
    }

    @Operation(summary = "Atualiza um usuario existente no banco", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados ds requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidado"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar os dados"),
    })
    @PutMapping(value = "/{idUsuario}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "idUsuario") Long idUsuario, @RequestBody UsuarioModel usuarioModel) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findByIdPrivate(idUsuario);

        if (usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com o Id especificado não foi encontrado");
        }

        UsuarioModel usuarioPut = usuarioModelOptional.get();
        usuarioPut.setUsername(usuarioModel.getUsername());
        usuarioPut.setEmail(usuarioModel.getEmail());
        usuarioPut.setSenha(usuarioModel.getSenha());
        usuarioPut.setTipo_usuario(usuarioModel.getTipo_usuario());

        usuarioService.saveUsuario(usuarioPut);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario Atualizado: " + new UsuarioDTO(usuarioPut));
    }

    @Operation(summary = "Delete o usuario que representa o idUsuario passado no end-point da requisição", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario salva com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados ds requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidado"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os dados"),
    })
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findByIdPrivate(idUsuario);

        if (usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com o Id especificado não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.deleteUsuario(usuarioModelOptional.get()));
    }

}
