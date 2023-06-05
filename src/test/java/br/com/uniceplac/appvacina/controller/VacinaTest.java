package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.repository.VacinasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@SpringBootTest
@AutoConfigureMockMvc
class VacinaTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    VacinasRepository vacinasRepository;

    @Test
    void vacinasTestFindAll() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = "31/05/2023";
        Date data = dateFormat.parse(dataFormatada);

        LoteModel lote = new LoteModel(null, data, data,
                "fermento", "pfizer",
                "escuro", "daora", "top");

        VacinasModel vacinas = new VacinasModel("Gripe", lote);

        Mockito.when(vacinasRepository.findAll()).thenReturn(List.of(vacinas));
        mockMvc.perform(get("/api/vacinas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Gripe"))
                .andExpect(jsonPath("$[0].dataFabricacao").value("Wed May 31 00:00:00 BRT 2023"))
                .andExpect(jsonPath("$[0].lote[0].dataValidade").value("Wed May 31 00:00:00 BRT 2023"))
                .andExpect(jsonPath("$[0].lote[0].composicao").value("fermento"))
                .andExpect(jsonPath("$[0].lote[0].fabricante").value("pfizer"))
                .andExpect(jsonPath("$[0].lote[0].informacoesArmazenamento").value("escuro"))
                .andExpect(jsonPath("$[0].lote[0].registroSanitario").value("daora"))
                .andExpect(jsonPath("$[0].lote[0].informacoesControleQualidade").value("top"));

    }*/

    /*
    @Test
    void vacinasTestFindById() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = "31/05/2023";
        Date data = dateFormat.parse(dataFormatada);

        VacinasModel vacinas = new VacinasModel("Gripe", new LoteModel(1L, data, data,
                "fermento", "pfizer",
                "escuro", "daora", "top"));

        vacinas.setId(1L);

        // Configuração do comportamento do mock do repository
        Mockito.when(vacinasRepository.findById(1L)).thenReturn(Optional.of(vacinas));

        // Realiza a requisição GET para /api/vacinas/{id} com o ID do vacinas
        mockMvc.perform(get("/api/vacinas/{id}", 1L)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gripe"));
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
    void atualizarVacinaTest() throws Exception {
        Long idVacina = 1L;
        VacinasModel vacina = new VacinasModel("Gripe", "Pfizer");
        vacina.setId(idVacina);
        Mockito.when(vacinasRepository.findById(idVacina)).thenReturn(Optional.of(vacina));
        Mockito.when(vacinasRepository.save(Mockito.any())).thenReturn(vacina);

        String requestBody = "{\"nome\": \"Gripe\", \"lote\": \"Pfizer\"}";

        mockMvc.perform(put("/api/vacinas/{idVacina}", idVacina)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gripe"))
                .andExpect(jsonPath("$.lote").value("Pfizer"))
                .andExpect(content().json(requestBody));
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

}*/
