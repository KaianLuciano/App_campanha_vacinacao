package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.models.enums.Genero;
import br.com.uniceplac.appvacina.models.enums.TipoUsuario;
import br.com.uniceplac.appvacina.repository.PacienteRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PacienteTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PacienteRepository pacienteRepository;

    @Test
    void pacienteTestFindAll() throws Exception {
        PacienteModel paciente = new PacienteModel("123456789", "Kaian Luciano", Genero.HOMEM, 12);
        Mockito.when(pacienteRepository.findAll()).thenReturn(List.of(paciente));
        mockMvc.perform(get("/api/pacientes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{nome: 'Kaian Luciano', genero: 'HOMEM', idade: 12}]"));
    }

    @Test
    void pacienteTestFindById() throws Exception {
        // Criação de um paciente de exemplo
        PacienteModel paciente = new PacienteModel("123456789", "Kaian Luciano", Genero.HOMEM, 12);

        // Configuração do comportamento do mock do repository
        Mockito.when(pacienteRepository.findById(paciente.getCpf())).thenReturn(Optional.of(paciente));

        // Realiza a requisição GET para /api/pacientes/{id} com o ID do paciente
        mockMvc.perform(get("/api/pacientes/{id}", paciente.getCpf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Kaian Luciano"))
                .andExpect(jsonPath("$.genero").value("HOMEM"))
                .andExpect(jsonPath("idade").value(12));
    }

    @Test
    void savePacienteTest() throws Exception {
        PacienteModel paciente = new PacienteModel("123456789", "Kaian Luciano", Genero.HOMEM, 12);
        Mockito.when(pacienteRepository.save(Mockito.any())).thenReturn(paciente);
        mockMvc.perform(post("/api/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cpf\": \"123456789\", \"nome\": \"Kaian Luciano\", \"genero\": \"HOMEM\", \"idade\": \"12\"}")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Kaian Luciano"))
                .andExpect(jsonPath("$.genero").value("HOMEM"))
                .andExpect(jsonPath("$.idade").value(12))
                .andExpect(content().json("{nome: 'Kaian Luciano', genero: 'HOMEM', idade: 12}"));
    }

    @Test
    void pacienteTestDeleteById() throws Exception {
        // Criação do paciente de exemplo
        PacienteModel paciente = new PacienteModel("123456789", "Kaian Luciano", Genero.HOMEM, 12);

        // Configuração do comportamento do mock do repository
        Mockito.when(pacienteRepository.findById(paciente.getCpf())).thenReturn(Optional.of(paciente));

        // Realiza a requisição DELETE para /api/pacientes/{id} com o ID do usuário
        mockMvc.perform(delete("/api/pacientes/{id}", paciente.getCpf()))
                .andDo(print())
                .andExpect(status().isOk());

        // Verifica se o método deleteById do repository foi chamado com o ID correto
        Mockito.verify(pacienteRepository, Mockito.times(1)).deleteById(paciente.getCpf());
    }

}
