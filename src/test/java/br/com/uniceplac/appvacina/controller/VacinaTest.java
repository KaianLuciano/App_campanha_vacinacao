package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.repository.VacinasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
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

        LoteModel lote = LoteModel.builder()
                .identificacaoLote(1L)
                .dataFabricacao(data)
                .dataValidade(data)
                .composicao("fermento")
                .fabricante("pfizer")
                .informacoesArmazenamento("escuro")
                .informacoesControleQualidade("confere")
                .registroSanitario("legal")
                .build();

        VacinasModel vacinas = new VacinasModel("Gripe", lote);

        when(vacinasRepository.findAll()).thenReturn(List.of(vacinas));
        mockMvc.perform(get("/api/vacinas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Gripe"))
                .andExpect(jsonPath("$[0].lote.dataFabricacao").value("2023-05-31T03:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].lote.dataValidade").value("2023-05-31T03:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].lote.composicao").value("fermento"))
                .andExpect(jsonPath("$[0].lote.fabricante").value("pfizer"))
                .andExpect(jsonPath("$[0].lote.informacoesArmazenamento").value("escuro"))
                .andExpect(jsonPath("$[0].lote.registroSanitario").value("legal"))
                .andExpect(jsonPath("$[0].lote.informacoesControleQualidade").value("confere"));

    }


    @Test
    void vacinasTestFindById() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = "31/05/2023";
        Date data = dateFormat.parse(dataFormatada);

        LoteModel lote = LoteModel.builder()
                .identificacaoLote(1L)
                .dataFabricacao(data)
                .dataValidade(data)
                .composicao("fermento")
                .fabricante("pfizer")
                .informacoesArmazenamento("escuro")
                .informacoesControleQualidade("confere")
                .registroSanitario("legal")
                .build();

        VacinasModel vacinas = new VacinasModel(1L, "Gripe", lote);

        // Configuração do comportamento do mock do repository
        when(vacinasRepository.findById(1L)).thenReturn(Optional.of(vacinas));

        // Realiza a requisição GET para /api/vacinas/{id} com o ID do vacinas
        mockMvc.perform(get("/api/vacinas/{idVacina}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gripe"))
                .andExpect(jsonPath("$.lote.dataFabricacao").value("2023-05-31T03:00:00.000+00:00"))
                .andExpect(jsonPath("$.lote.dataValidade").value("2023-05-31T03:00:00.000+00:00"))
                .andExpect(jsonPath("$.lote.composicao").value("fermento"))
                .andExpect(jsonPath("$.lote.fabricante").value("pfizer"))
                .andExpect(jsonPath("$.lote.informacoesArmazenamento").value("escuro"))
                .andExpect(jsonPath("$.lote.registroSanitario").value("legal"))
                .andExpect(jsonPath("$.lote.informacoesControleQualidade").value("confere"));
    }

    /*
    @Test
    void saveVacinaTest() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String data = "31/05/2023";
        Date dateFormatada = formatter.parse(data);

        LoteModel lote = LoteModel.builder()
                .identificacaoLote(1L)
                .dataFabricacao(dateFormatada)
                .dataValidade(dateFormatada)
                .composicao("fermento")
                .fabricante("pfizer")
                .informacoesArmazenamento("escuro")
                .informacoesControleQualidade("confere")
                .registroSanitario("legal")
                .build();

        VacinasModel vacinas = new VacinasModel("Gripe", lote);

        when(vacinasRepository.save(Mockito.any())).thenReturn(vacinas);
        mockMvc.perform(post("/api/vacinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Gripe\", \"lote\": " +
                                "{\"identificacaoLote\": 1, \"dataFabricacao\": \"2023-05-31T03:00:00.000+00:00\"," +
                                " \"dataValidade\": \"2023-05-31T03:00:00.000+00:00\", \"composicao\": \"fermento\"," +
                                " \"fabricante\": \"pfizer\", \"informacoesArmazenamento\": \"escuro\"," +
                                " \"registroSanitario\": \"legal\", \"informacoesControleQualidade\": \"confere\"}}")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.nome").value("Gripe"))
                .andExpect(jsonPath("$.lote.dataFabricacao").value("2023-05-31T03:00:00.000+00:00"))
                .andExpect(jsonPath("$.lote.dataValidade").value("2023-05-31T03:00:00.000+00:00"))
                .andExpect(jsonPath("$.lote.composicao").value("fermento"))
                .andExpect(jsonPath("$.lote.fabricante").value("pfizer"))
                .andExpect(jsonPath("$.lote.informacoesArmazenamento").value("escuro"))
                .andExpect(jsonPath("$.lote.registroSanitario").value("legal"))
                .andExpect(jsonPath("$.lote.informacoesControleQualidade").value("confere"));

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
    */
}
