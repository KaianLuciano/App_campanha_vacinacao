package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.models.CampanhaVacinacaoModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.repository.CampanhaVacinacaoRepository;
import br.com.uniceplac.appvacina.repository.VacinasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CampanhaVacinaTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CampanhaVacinacaoRepository campanhaVacinacaoRepository;

    @Test
    void capanhaTestFindAll() throws Exception {
        CampanhaVacinacaoModel campanha = new CampanhaVacinacaoModel("Sarampo", null);
        campanha.setId(1L);

        Mockito.when(campanhaVacinacaoRepository.findAll()).thenReturn(List.of(campanha));
        mockMvc.perform(get("/api/campanhas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{nome: 'Sarampo', data: null}]"));
    }

    @Test
    void campanhaTestFindById() throws Exception {
        // Criação de um vacinas de exemplo
        CampanhaVacinacaoModel campanha = new CampanhaVacinacaoModel("Sarampo", null);
        campanha.setId(1L);

        // Configuração do comportamento do mock do repository
        Mockito.when(campanhaVacinacaoRepository.findById(1L)).thenReturn(Optional.of(campanha));

        // Realiza a requisição GET para /api/campanhas/{id} com o ID das campanhas
        mockMvc.perform(get("/api/campanhas/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Sarampo"));
    }

    @Test
    void saveCampanhaTest() throws Exception {
        CampanhaVacinacaoModel campanha = new CampanhaVacinacaoModel("Sarampo", null);
        Mockito.when(campanhaVacinacaoRepository.save(Mockito.any())).thenReturn(campanha);
        mockMvc.perform(post("/api/campanhas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Sarampo\", \"data\": null}")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Sarampo"))
                .andExpect(content().json("{nome: 'Sarampo', data: null}"));
    }

    @Test
    void campanhaTestDeleteById() throws Exception {
        // Criação da campanha de exemplo
        CampanhaVacinacaoModel campanha = new CampanhaVacinacaoModel("Sarampo", null);
        campanha.setId(1L);

        // Configuração do comportamento do mock do repository
        Mockito.when(campanhaVacinacaoRepository.findById(1L)).thenReturn(Optional.of(campanha));

        // Realiza a requisição DELETE para /api/vacinas/{id} com o ID da vacina
        mockMvc.perform(delete("/api/campanhas/{id}",1L))
                .andDo(print())
                .andExpect(status().isOk());

        // Verifica se o método deleteById do repository foi chamado com o ID correto
        Mockito.verify(campanhaVacinacaoRepository, Mockito.times(1)).deleteById(1L);
    }

}
