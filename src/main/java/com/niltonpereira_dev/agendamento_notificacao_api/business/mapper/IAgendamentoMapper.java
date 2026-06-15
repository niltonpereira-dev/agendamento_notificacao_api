package com.niltonpereira_dev.agendamento_notificacao_api.business.mapper;

import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.in.AgendamentoDTO;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.out.AgendamentoDTOOut;
import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoDTO agendamentoDTO);

    AgendamentoDTOOut ParaOut(Agendamento agendamento);
}
