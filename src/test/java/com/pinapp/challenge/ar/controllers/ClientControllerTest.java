package com.pinapp.challenge.ar.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pinapp.challenge.ar.dtos.ClientDto;
import com.pinapp.challenge.ar.dtos.ClientStatsDto;
import com.pinapp.challenge.ar.services.ClientService;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testCreateClient() throws Exception {

        ClientDto clientDto = new ClientDto("John", "Doe", 30, LocalDate.of(1992, 5, 10));

        doNothing().when(clientService).createClient(clientDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/creacliente")
                        .content(asJsonString(clientDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(clientService, times(1)).createClient(any());
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testGetClientStats() throws Exception {

        ClientStatsDto clientStatsDto = new ClientStatsDto(35.2, 5.6);

        when(clientService.getClientStats()).thenReturn(clientStatsDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/kpideclientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageAge").value(35.2))
                .andExpect(jsonPath("$.standardDeviation").value(5.6));

        // Verificación
        verify(clientService, times(1)).getClientStats();
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testGetClientListWithEstimatedDeathDate() throws Exception {

        ClientDto client1 = new ClientDto("John", "Doe", 30, LocalDate.of(1992, 5, 10));
        ClientDto client2 = new ClientDto("Jane", "Smith", 25, LocalDate.of(1997, 8, 20));

        int average = 27;
        client1.setEstimatedDeathDate(LocalDate.of(1992 + average, 5, 10));
        client2.setEstimatedDeathDate(LocalDate.of(1997 + average, 8, 20));

        List<ClientDto> clientList = Arrays.asList(client1, client2);

        when(clientService.getClientListWithEstimatedDeathDate()).thenReturn(clientList);

        mockMvc.perform(MockMvcRequestBuilders.get("/listclientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].age").value(30))
                .andExpect(jsonPath("$[0].dateOfBirth").value("1992-05-10"))
                .andExpect(jsonPath("$[0].estimatedDeathDate").exists())
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"))
                .andExpect(jsonPath("$[1].age").value(25))
                .andExpect(jsonPath("$[1].dateOfBirth").value("1997-08-20"))
                .andExpect(jsonPath("$[1].estimatedDeathDate").exists());

        verify(clientService, times(1)).getClientListWithEstimatedDeathDate();
        verifyNoMoreInteractions(clientService);
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Registrar el módulo JavaTimeModule
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Deshabilitar la serialización de fechas como marcas de tiempo
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

