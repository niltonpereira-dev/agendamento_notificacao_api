package com.niltonpereira_dev.agendamento_notificacao_api.business;

import com.niltonpereira_dev.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.niltonpereira_dev.agendamento_notificacao_api.business.service.AgendamentoService;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.in.AgendamentoDTO;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.out.AgendamentoDTOOut;
import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private IAgendamentoMapper agendamentoMapper;

    private AgendamentoDTO agendamentoDTO;
    private AgendamentoDTOOut agendamentoDTOOut;
    private Agendamento agendamentoEntity;

    @BeforeEach
    void setUp() {

        agendamentoEntity = new Agendamento(
                1L,
                "email@email.com",
                "55887996578",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                LocalDateTime.now(),
                null,
                "Favor retornar a loja com urgência",
                StatusNotificacaoEnum.AGENDADO
        );

        agendamentoDTO = new AgendamentoDTO(
                "email@email.com",
                "55887996578",
                "Favor retornar a loja com urgência",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1)
        );

        agendamentoDTOOut = new AgendamentoDTOOut(
                1L,
                "email@email.com",
                "55887996578",
                "Favor retornar a loja com urgência",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                StatusNotificacaoEnum.AGENDADO
        );
    }

    @Test
    void deveGravarAgendamentoComSucesso() {

        when(agendamentoMapper.paraEntity(agendamentoDTO))
                .thenReturn(agendamentoEntity);

        when(agendamentoRepository.save(any(Agendamento.class)))
                .thenReturn(agendamentoEntity);

        when(agendamentoMapper.paraOut(agendamentoEntity))
                .thenReturn(agendamentoDTOOut);

        AgendamentoDTOOut resultado =
                agendamentoService.gravarAgendamento(agendamentoDTO);

        verify(agendamentoMapper, times(1))
                .paraEntity(agendamentoDTO);

        verify(agendamentoRepository, times(1))
                .save(any(Agendamento.class));

        verify(agendamentoMapper, times(1))
                .paraOut(agendamentoEntity);


        assertThat(resultado)
                .usingRecursiveComparison()
                .isEqualTo(agendamentoDTOOut);
    }
}