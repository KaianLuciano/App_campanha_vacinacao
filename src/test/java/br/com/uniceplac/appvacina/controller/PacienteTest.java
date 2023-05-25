package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.models.PacienteModel;
import br.com.uniceplac.appvacina.models.enums.Genero;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PacienteTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void pacienteTestGetAll() throws Exception {
        mockMvc.perform(get("/api/pacientes"))
                .andExpect(status().isOk());
    }

    @Test
    public void pacienteTesteSave () throws Exception {

        PacienteModel paciente = new PacienteModel("123456789", "Kaian", Genero.HOMEM, 12);

        mockMvc.perform(post("/api/pacientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isOk());
    }

    @Test
    public void pacienteTestePut () throws Exception {
        PacienteModel paciente = new PacienteModel("123456789", "Kaian", Genero.HOMEM, 12);

        mockMvc.perform(put("/api/pacientes/123456789")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isOk());
    }

    @Test
    public void pacienteTestDelete () throws Exception {
        mockMvc.perform(delete("/api/pacientes/123456789"))
                .andExpect(status().isOk());
    }


}
