package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.repository.VacinasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VacinaTeste {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    VacinasRepository vacinasRepository;

    @Test
    void vacinasTestFindAll() throws Exception {
        VacinasModel vacinas = new VacinasModel("Gripe", "Pfizer");
        Mockito.when(vacinasRepository.findAll()).thenReturn(List.of(vacinas));
        mockMvc.perform(get("/api/vacinas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{nome: 'Gripe', lote: 'Pfizer'}]"));
    }

    @Test
    void vacinasTestFindById() throws Exception {
        // Criação de um vacinas de exemplo
        VacinasModel vacinas = new VacinasModel("Gripe", "Pfizer");

        vacinas.setId(1L);

        // Configuração do comportamento do mock do repository
        Mockito.when(vacinasRepository.findById(1L)).thenReturn(Optional.of(vacinas));

        // Realiza a requisição GET para /api/vacinas/{id} com o ID do vacinas
        mockMvc.perform(get("/api/vacinas/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gripe"))
                .andExpect(jsonPath("$.lote").value("Pfizer"));
    }

    @Test
    void saveVacinaTest() throws Exception {
        VacinasModel vacinas = new VacinasModel("Gripe", "Pfizer");
        Mockito.when(vacinasRepository.save(Mockito.any())).thenReturn(vacinas);
        mockMvc.perform(post("/api/vacinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Gripe\", \"lote\": \"Pfizer\"}")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gripe"))
                .andExpect(jsonPath("$.lote").value("Pfizer"))
                .andExpect(content().json("{nome: 'Gripe', lote: 'Pfizer'}"));
    }

    @Test
    void vacinaTestDeleteById() throws Exception {
        // Criação do vacina de exemplo
        VacinasModel vacinas = new VacinasModel("Gripe", "Pfizer");

        vacinas.setId(1L);

        // Configuração do comportamento do mock do repository
        Mockito.when(vacinasRepository.findById(1L)).thenReturn(Optional.of(vacinas));

        // Realiza a requisição DELETE para /api/vacinas/{id} com o ID da vacina
        mockMvc.perform(delete("/api/vacinas/{id}",1L))
                .andDo(print())
                .andExpect(status().isOk());

        // Verifica se o método deleteById do repository foi chamado com o ID correto
        Mockito.verify(vacinasRepository, Mockito.times(1)).deleteById(1L);
    }

}
