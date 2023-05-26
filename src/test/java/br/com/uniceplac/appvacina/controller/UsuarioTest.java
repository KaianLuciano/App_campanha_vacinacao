package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.models.enums.TipoUsuario;
import br.com.uniceplac.appvacina.repository.UsuarioRepository;
import br.com.uniceplac.appvacina.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    void usuariosTestFindAll() throws Exception {
        UsuarioModel usuario = new UsuarioModel("Kaian", "kaian@gmail.com", "kaian1234", TipoUsuario.USUARIO);
        Mockito.when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        mockMvc.perform(get("/api/usuarios"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{username: 'Kaian', email: 'kaian@gmail.com'}]"));
    }

    @Test
    void usuariosTestFindById() throws Exception {
        // Criação de um usuário de exemplo
        UsuarioModel usuario = new UsuarioModel("Kaian", "kaian@gmail.com", "kaian1234", TipoUsuario.USUARIO);

        // Define o ID do usuário de exemplo
        usuario.setId(1L);

        // Configuração do comportamento do mock do repository
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Realiza a requisição GET para /api/usuarios/{id} com o ID do usuário
        mockMvc.perform(get("/api/usuarios/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Kaian"))
                .andExpect(jsonPath("$.email").value("kaian@gmail.com"));
    }

    @Test
    void saveUsuarioTest() throws Exception {
        UsuarioModel usuario = new UsuarioModel("Kaian", "kaian@gmail.com", "kaian1234", TipoUsuario.USUARIO);
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);
        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"Kaian\", \"email\": \"kaian@gmail.com\", \"senha\": \"kaian1234\", \"tipo_usuario\": \"USUARIO\"}")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Kaian"))
                .andExpect(jsonPath("$.email").value("kaian@gmail.com"))
                .andExpect(content().json("{username: 'Kaian', email: 'kaian@gmail.com'}"));
    }

    @Test
    void usuariosTestDeleteById() throws Exception {
        // Criação do usuário de exemplo
        UsuarioModel usuario = new UsuarioModel("Kaian", "kaian@gmail.com", "kaian1234", TipoUsuario.USUARIO);

        // Define o ID do usuário de exemplo
        usuario.setId(1L);

        // Configuração do comportamento do mock do repository
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Realiza a requisição DELETE para /api/usuarios/{id} com o ID do usuário
        mockMvc.perform(delete("/api/usuarios/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk());

        // Verifica se o método deleteById do repository foi chamado com o ID correto
        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(1L);
    }

}
