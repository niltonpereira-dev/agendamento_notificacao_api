package com.niltonpereira_dev.agendamento_notificacao_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.niltonpereira_dev.agendamento_notificacao_api.business.service.AgendamentoService;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.in.AgendamentoDTO;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.out.AgendamentoDTOOut;
import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import jakarta.persistence.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.apache.coyote.http11.Constants.a;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AgendamentoControllerTest {

    @Mock
    private AgendamentoService agendamentoService;

    @InjectMocks
    private AgendamentoController agendamentoController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private AgendamentoDTO agendamentoDTO;
    private AgendamentoDTOOut agendamentoDTOOut;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(agendamentoController)
                .build();

        objectMapper.registerModule(new JavaTimeModule());

        agendamentoDTO = new AgendamentoDTO(
                "email@email.com",
                "90909999299",
                "Favor retornar a loja com urgencia!",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1)
        );

        agendamentoDTOOut = new AgendamentoDTOOut(
                1L,
                "email@email.com",
                "90909999299",
                "Favor retornar a loja com urgencia!",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                StatusNotificacaoEnum.AGENDADO
        );
    }

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {

        when(agendamentoService.gravarAgendamento(agendamentoDTO))
                .thenReturn(agendamentoDTOOut);

        mockMvc.perform(post("/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(agendamentoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.emailDestinatario")
                        .value("email@email.com"))
                .andExpect(jsonPath("$.telefoneDestinatario")
                        .value(agendamentoDTOOut.telefoneDestinatario()))
                .andExpect(jsonPath("$.mensagem")
                        .value(agendamentoDTOOut.mensagem()))
                .andExpect(jsonPath("$.dataHoraEnvio")
                        .value("15-06-2016 11:01:01"))
                .andExpect(jsonPath("$.statusNotificacao")
                        .value("AGENDADO"));

        verify(agendamentoService, times(1))
                .gravarAgendamento(agendamentoDTO);
    }
}